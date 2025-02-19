package com.apexsoft.javaweb.listener;

import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MyServletRequestAttributeListener implements ServletRequestAttributeListener {


    //request域中添加数据时执行
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("request域中---添加了数据");
    }

    //request域中移除数据时执行
    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("request域中---移除了数据");
    }

    //修改request域中已有的数据时执行
    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("request域中---替换了了数据");
    }
}