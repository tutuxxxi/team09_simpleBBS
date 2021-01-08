package com.team09.controller.user;
/**
 *
 */

import com.team09.bean.User;
import com.team09.service.UserService;
import com.team09.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author team09
 * 用户注册servlet
 *  需要参数 username passwd validCode
 *  可选参数 imgUrl profile
 */
@WebServlet("/user/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO jsp页面提交数据时统一数据名称
        //获取表单数据
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");
        String verCode = request.getParameter("validCode");
        String imgUrl = request.getParameter("imgUrl");
        String profile = request.getParameter("profile");

        //结果
        boolean result = false;
        //TODO 默认跳转链接为注册界面
        String url = "  ";

        //1.判断数据数据格式是否正确
        //传入的imgUrl和profile可以为空 存入数据库时存入null

        //2.判断验证码是否正确(从session中获取验证码)
        HttpSession session = request.getSession();
        String rightCode = (String) session.getAttribute("validCode");
        session.removeAttribute("validCode");

        if (rightCode.equals(verCode)) {
            //3.调用service层 将数据存入数据库

            UserService userService = UserServiceImpl.getInstance();
            result = userService.addUser(new User(username, passwd, imgUrl, profile));

            //TODO 注册成功后跳转至登录界面
            if (result == true) {
                url = "   ";
            }
        }
        //4.根据结果重定向至注册界面/登录界面
        response.sendRedirect(url);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
