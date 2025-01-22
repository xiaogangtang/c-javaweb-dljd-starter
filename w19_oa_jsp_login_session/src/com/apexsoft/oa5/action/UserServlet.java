package com.apexsoft.oa5.action;

import com.apexsoft.oa5.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Servlet负责业务的处理
// JSP负责页面的展示。
@WebServlet({
        "/user/login",
        "/user/exit"
})
public class UserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if("/user/login".equals(servletPath)){
            doLogin(request, response);
        }else if("/user/exit".equals(servletPath)){
            doExit(request, response);
        }
    }


    private void doExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取session
        HttpSession session = request.getSession(false);
        if(session != null){
            //手动销毁session
            session.invalidate();
            response.sendRedirect(request.getContextPath());
        }
    }
    protected void doLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean success = false;
        // 你要做一件什么事儿？验证用户名和密码是否正确。
        // 获取用户名和密码
        // 前端你是这样提交的：username=admin&password=123
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 连接数据库验证用户名和密码
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from t_user where username = ? and password = ?";
            // 编译SQL
            ps = conn.prepareStatement(sql);
            // 给?传值
            ps.setString(1, username);
            ps.setString(2, password);
            // 执行SQL
            rs = ps.executeQuery();
            // 这个结果集当中最多只有一条数据。
            if (rs.next()) { // 不需要while循环
                // 登录成功
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        // 登录成功/失败
        if (success) {
            // 获取session对象(这里的要求是：必须获取到session，没有session也要新建一个session对象。)
            HttpSession session = request.getSession();//session一定不是null
            session.setAttribute("username", username);

            // 成功，跳转到用户列表页面
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            // 失败，跳转到失败页面
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}























