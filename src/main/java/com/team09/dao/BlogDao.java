package com.team09.dao;


import com.team09.bean.Blog;
import com.team09.bean.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author team09
 */
public interface BlogDao {

    /**
     * 根据id获取博客信息
     * @param id
     * @return
     * @throws SQLException
     */
    public Blog getBlogById(String id) throws SQLException;

    /**
     * 根据用户id查询他发布的所有博客
     * @param userId
     * @return
     * @throws SQLException
     */
    public List<Blog> getBlogByUserId(String userId) throws SQLException;

    /**
     * 获取所有博客
     * @return
     * @throws SQLException
     */
    public List<Blog> getAllBlogs() throws SQLException;

    /**
     * 创建新的博客
     * @param blog
     * @return
     * @throws SQLException
     */
    public boolean addBlogs(Blog blog) throws SQLException;

    /**
     * 删除博客
     * @param blogId
     * @return
     * @throws SQLException
     */
    public boolean deleteBlogs(String blogId) throws SQLException;


    /**
     * 修改博客
     * @param blog
     * @return
     * @throws SQLException
     */
    public boolean updateBlogs(Blog blog) throws SQLException;

    /**
     * 获取所有精华博客
     * @return
     */
    public List<Blog> getQuintBlogs() throws SQLException;
}
