package com.apexsoft.javaweb.listener;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MyServletContextAttributeListener implements ServletContextAttributeListener {

    //向servletContext域中添加了数据 时执行
    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        System.out.println("向servletContext域中添加了数据");
    }

    //向servletContext域中移除了数据 时执行
    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        System.out.println("向servletContext域中移除了数据");
    }

    //方法会在 ServletContext#setAttribute(String, Object) 方法被调用，且该 key 已经存在 时触发。
    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        System.out.println("向servletContext域中修改了数据");
    }
}
