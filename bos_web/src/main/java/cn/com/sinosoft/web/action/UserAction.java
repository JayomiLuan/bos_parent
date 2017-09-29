package cn.com.sinosoft.web.action;

import cn.com.sinosoft.domain.User;
import cn.com.sinosoft.service.IUserService;
import cn.com.sinosoft.utils.CommonUtils;
import cn.com.sinosoft.utils.MD5Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
    @Resource
    private IUserService userService;

    private String checkcode;
    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
    HttpSession session = ServletActionContext.getRequest().getSession();
    public String login(){
        String validates = (String) session.getAttribute("key");
        if(StringUtils.isNotBlank(checkcode) && checkcode.equals(validates)){
            //验证码校验成功
            User user = userService.findUsernameAndPassword(model.getUsername(), MD5Utils.md5(model.getPassword()));
            if(null != user){
                //查询到用户,登录成功,放到session中,跳转主界面
                session.setAttribute("loginUser",user);
                return "home";
            }else{
                //查询不到登陆失败
                this.addActionError("用户名或密码错误!");
                return "login";
            }
        }else{
            this.addActionError("校验码错误!");
            return "login";
        }
    }

    //登出,将用户的session移除
    public String logout(){
        session.removeAttribute("loginUser");
        Object loginUser = session.getAttribute("loginUser");
        System.out.println(loginUser);
        return "login";
    }

    //修改密码
    public String editPassword(){
        System.out.println(model.getPassword());
        String[] pwd = model.getPassword().split("-");
        model.setPassword(pwd[1].toString());
        String flag = "1";
        //旧密码与新密码相等
        String password1 = CommonUtils.getLoginUser().getPassword();//session中的用户的密码,相当于数据库中的旧密码
        String oldPassword = MD5Utils.md5(pwd[0].toString());//从前台获取的密码,并md5加密
        if(!oldPassword.equals(password1)){
            flag = "2";
        }else{
            try{
                userService.editPassword(model);
            }catch(Exception e){
                flag = "0";
                e.printStackTrace();
            }

        }
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().print(flag);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return NONE;
    }
}
