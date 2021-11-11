package com.example.demo.controller;

import com.example.demo.bean.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;

/**
 * session 处理的基类
 */
public abstract class BaseController {

    @Autowired
    HttpServletRequest request;

    private String key_user = "user";
    public final static int SESSION_TIME = 1000 * 60 * 60;

    //添加session信息(一般来说,登录时可以用到)
    public void saveSessionUser(SysUser user) {
        this.request.getSession().setAttribute(key_user, user);
        this.request.getSession().setMaxInactiveInterval(SESSION_TIME);
    }

    //获取session信息
    public SysUser getSessionUser() {

        return (SysUser) this.request.getSession().getAttribute(key_user);
    }

    //删除session信息
    public void removeSession() {
        this.request.getSession().removeAttribute(key_user);
    }
    
    
    //获取sessionid
    public String getSessionId() {

        return  this.request.getSession().getId();
    }

    //获取userId
    public Integer getSessionUserid() {
    	SysUser user = (SysUser) this.request.getSession().getAttribute(key_user);
    	if(user ==null) {
    		return null;
    	}
        return user.getId();
    }

}
