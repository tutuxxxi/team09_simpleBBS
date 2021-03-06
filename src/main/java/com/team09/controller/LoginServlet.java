package com.team09.controller;

import com.team09.bean.Admin;
import com.team09.bean.User;
import com.team09.service.AdminService;
import com.team09.service.UserService;
import com.team09.service.impl.AdminServiceImpl;
import com.team09.service.impl.UserServiceImpl;
import com.team09.util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author team09
 *  参数 username password validCode
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getInstance();
    private AdminService adminService = AdminServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String validCode = req.getParameter("validCode");

        String realCode = (String) session.getAttribute("validCode");
        session.removeAttribute("validCode");

        if(validCode == null || !validCode.toLowerCase().equals(realCode)){
            //验证码有误
            session.setAttribute("msg", "验证码错误");

            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }


        Object userInfo = userService.getUserByName(username);
        if(userInfo == null){
            userInfo = adminService.getAdminByName(username);
        }

        if(userInfo == null){
            //账号不存在
            session.setAttribute("msg", "该账号不存在，检查后重试");

            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }



        if(userInfo instanceof User){
            if(((User) userInfo).getPassword().equals(password)){
                //登陆成功
                session.setAttribute("userInfo", userInfo);
                ((User) userInfo).setImgUrl(FileUtil.getImg(((User) userInfo).getImgUrl()));
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
                return;
            }
        }else{
            if(((Admin) userInfo).getPassword().equals(password)){
                //登陆成功
                session.setAttribute("adminInfo", userInfo);

                resp.sendRedirect(req.getContextPath() + "/view/admin/userList.jsp");
                return;
            }
        }

        //登陆失败
        session.setAttribute("msg", "账号密码不匹配，请重试");
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}
