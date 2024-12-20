package com.jin.app;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    /*private static final long serialVersionUID = 1L;*/

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.处理请求和响应的中文乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //2.接受请求参数username和password

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //3.使用JDBC技术从数据库的users表中查询出用户名为：username,密码为：password的一条用户信息
        String ur1= "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
        String user ="root";
        String pwd = "123456";
        Users users = null;
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        //3.1加载mysql5.7版本的驱动类
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //3.2获取mysql数据库：sanguo的连接对象
            Connection conn = DriverManager.getConnection(ur1,user,pwd);
            //3.3获取发送和执行sql语句的对象
            Statement stmt = conn.createStatement();
            //3.4定义sql：根据用户名和密码查询一条users表中的信息
            String sql = "select * from users where username='"+username+"' and password='"+password+"'";
            //3.5执行sql语句
            ResultSet resultSet= stmt.executeQuery(sql);
            //3.6处理结果resultSet
            if (resultSet.next()) {
                //登陆成功
                System.out.println("鸡哥战纪游戏启动了，全军还有30秒到达战场。");

            }else{
                //登录失败
                //响应一个错误信息：用户名和密码错误到页面
                response.getWriter().println("用户名或密码错误，请重新输入！");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


       /* // 获取请求参数中的用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 这里可以添加验证逻辑，例如检查数据库中的用户名和密码是否匹配
        if ("admin".equals(username) && "password".equals(password)) {
            response.getWriter().println("登录成功！");
        } else {
            response.getWriter().println("用户名或密码错误！");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 如果需要支持GET请求，可以在这里处理
        response.getWriter().println("不支持GET请求，请使用POST方法进行登录。");*/
    }
}
