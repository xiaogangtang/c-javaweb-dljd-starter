package com.apexsoft.oa4.action;


import com.apexsoft.oa4.bean.Dept;
import com.apexsoft.oa4.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 模板类
@WebServlet({
        "/dept/list",
        "/dept/save",
        "/dept/detail",
        "/dept/delete",
        "/dept/modify"
})
// 模糊匹配
// 只要请求路径是以"/dept"开始的，都走这个Servlet。
//@WebServlet("/dept/*")
public class DeptServlet extends HttpServlet {

    // 模板方法
    // 重写service方法（并没有重写doGet或者doPost）

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取servlet path
        String servletPath = request.getServletPath();
        if("/dept/list".equals(servletPath)){
            doList(request, response);
        } else if("/dept/save".equals(servletPath)){
            doSave(request, response);
        }  else if("/dept/detail".equals(servletPath)){
            doDetail(request, response);
        } else if("/dept/delete".equals(servletPath)){
            doDel(request, response);
        } else if("/dept/modify".equals(servletPath)){
            doModify(request, response);
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取应用的根路径
        String contextPath = request.getContextPath();

        // 设置响应的内容类型以及字符集。防止中文乱码问题。
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 连接数据库，查询所有的部门
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Dept> list = new ArrayList<>();
        try {
            // 获取连接
            conn = DBUtil.getConnection();
            // 获取预编译的数据库操作对象
            String sql = "select deptno as a,dname,loc from dept";
            ps = conn.prepareStatement(sql);
            // 执行SQL语句
            rs = ps.executeQuery();
            // 处理结果集
            int i = 0;
            while(rs.next()){
                String deptno = rs.getString("a");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                Dept dept = new Dept();
                dept.setDeptno(deptno);
                dept.setDname(dname);
                dept.setLoc(loc);
                list.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            DBUtil.close(conn, ps, rs);
        }
        request.setAttribute("deptList", list);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    private void doSave(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取部门的信息
        // 注意乱码问题（Tomcat10不会出现这个问题）
        request.setCharacterEncoding("UTF-8");
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        // 连接数据库执行insert语句
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into dept(deptno, dname, loc) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            ps.setString(2, dname);
            ps.setString(3, loc);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }

        if (count == 1) {
            // 保存成功跳转到列表页面
            // 转发是一次请求。
            //request.getRequestDispatcher("/dept/list").forward(request, response);

            // 这里最好使用重定向（浏览器会发一次全新的请求。）
            // 浏览器在地址栏上发送请求，这个请求是get请求。
            response.sendRedirect(request.getContextPath() + "/dept/list");

        }else{
            // 保存失败跳转到错误页面
            //request.getRequestDispatcher("/error.html").forward(request, response);

            // 这里也建议使用重定向。
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Dept dept = new Dept();

        // 获取部门编号
        // /oa/dept/detail?dno=30
        // 虽然是提交的30，但是服务器获取的是"30"这个字符串。
        String deptno = request.getParameter("dno");

        // 连接数据库，根据部门编号查询部门信息。
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select dname,loc from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            rs = ps.executeQuery();
            // 这个结果集一定只有一条记录。
            if (rs.next()){
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                dept.setDeptno(deptno);
                dept.setDname(dname);
                dept.setLoc(loc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        request.setAttribute("dept", dept);
        String jspPage = request.getParameter("flag") + ".jsp";
        request.getRequestDispatcher("/"+jspPage).forward(request,response);
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 根据部门编号，删除部门。
        // 获取部门编号
        String deptno = request.getParameter("deptno");
        // 连接数据库删除数据
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            // 开启事务（自动提交机制关闭）
            conn.setAutoCommit(false);
            String sql = "delete from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            // 返回值是：影响了数据库表当中多少条记录。
            count = ps.executeUpdate();
            // 事务提交
            conn.commit();
        } catch (SQLException e) {
            // 遇到异常要回滚
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }

        // 判断删除成功了还是失败了。
        if (count == 1) {
            //删除成功
            //仍然跳转到部门列表页面
            //部门列表页面的显示需要执行另一个Servlet。怎么办？转发。
            //request.getRequestDispatcher("/dept/list").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }else{
            // 删除失败
            //request.getRequestDispatcher("/error.html").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 解决请求体的中文乱码问题。
        request.setCharacterEncoding("UTF-8");

        // 获取表单中的数据
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");
        // 连接数据库执行更新语句
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "update dept set dname = ?, loc = ? where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dname);
            ps.setString(2, loc);
            ps.setString(3, deptno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }

        if (count == 1) {
            // 更新成功
            // 跳转到部门列表页面（部门列表页面是通过Java程序动态生成的，所以还需要再次执行另一个Servlet）
            //request.getRequestDispatcher("/dept/list").forward(request, response);

            response.sendRedirect(request.getContextPath() + "/dept/list");
        }else{
            // 更新失败
            //request.getRequestDispatcher("/error.html").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }
}
