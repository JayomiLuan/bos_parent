package cn.com.sinosoft.web.action;

import cn.com.sinosoft.domain.User;
import cn.com.sinosoft.service.IUserService;
import cn.com.sinosoft.utils.MD5Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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

}
