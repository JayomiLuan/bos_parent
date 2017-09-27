package cn.com.sinosoft.web.interceptor;

import cn.com.sinosoft.utils.CommonUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        //判断当前用户是否登陆
        if(null == CommonUtils.getLoginUser()){
            //未登录,返回登录页面
            return "login";
        }
        return actionInvocation.invoke();
    }
}
