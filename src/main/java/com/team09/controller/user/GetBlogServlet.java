package com.team09.controller.user;

import com.team09.bean.Blog;
import com.team09.bean.Comment;
import com.team09.bean.User;
import com.team09.service.BlogService;
import com.team09.service.CommentService;
import com.team09.service.UserService;
import com.team09.service.impl.BlogServiceImpl;
import com.team09.service.impl.CommentServiceImpl;
import com.team09.service.impl.UserServiceImpl;
import com.team09.util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据id获取博客
 * 博客详细信息存于request的blog
 * 博客具体内容存于request的blogContxt
 * 获取成功后跳转至具体博客显示页面
 * 获取失败跳转至原来位置
 *
 * 同时获取评论
 */

@WebServlet("/user/lookBlog")
public class GetBlogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取需要详细浏览的帖子id
        String blogId = request.getParameter("blogId");

        //根据此id获取该帖子的详细信息
        BlogService blogService = BlogServiceImpl.getInstance();
        Blog blog = blogService.getBlogById(blogId);
        if (blog != null) {
            //博客的浏览量增加
            blog.setClicks(blog.getClicks()+1);
            blogService.updateBlogs(blog);


            //将博客详细信息返回 使用request
            request.setAttribute("blog", blog);
            request.setAttribute("content", FileUtil.readContent(blog.getContext()));


            //返回博客作者信息
            UserService userService = UserServiceImpl.getInstance();
            User user = userService.getUserById(blog.getUserId());
            user.setImgUrl(FileUtil.getImg(user.getImgUrl()));
            request.setAttribute("blogUser",user);


            //进入service层根据博客id获取用户评论
            CommentService commentService = CommentServiceImpl.getInstance();
            List<Comment> comment = commentService.getCommentsByBlogId(blogId);

            //进入service层获取用户信息
            Map<Comment,User> map = new HashMap<Comment,User>();
            for(Comment c: comment){
                User user1 = userService.getUserById(c.getUserId());
                user1.setImgUrl(FileUtil.getImg(user1.getImgUrl()));
                map.put(c,user1);
            }

            //将博客评论和对应用户信息返回 使用request
            request.setAttribute("blogComment",map);



            //TODO 跳转至显示详细帖子内容页面
            request.getRequestDispatcher("/view/viewBlog.jsp").forward(request, response);
        } else {
            //TODO 跳转至原本位置
            response.sendRedirect(request.getContextPath() + "/user/listBlog");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
