package com.lrm.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @ClassName MyHttpSessionListener
 * @Description: 监听器
 * @Author: bughua
 * @CreateDate: 2019/10/21 14:23
 */
public class MyHttpSessionListener implements HttpSessionListener {

    public static int online = 0;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("创建session");
        online ++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("销毁session");
        online --;
    }
}
