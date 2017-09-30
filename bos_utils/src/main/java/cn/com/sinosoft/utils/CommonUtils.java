package cn.com.sinosoft.utils;

import cn.com.sinosoft.domain.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class CommonUtils {
    public static HttpSession getSession(){
        return ServletActionContext.getRequest().getSession();
    }
    public static User getLoginUser(){
        return (User) getSession().getAttribute("loginUser");
    }
}
