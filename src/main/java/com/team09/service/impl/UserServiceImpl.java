package com.team09.service.impl;

import com.team09.bean.User;
import com.team09.dao.UserDao;
import com.team09.dao.impl.UserDaoImpl;
import com.team09.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserService userService = new  UserServiceImpl();

    private UserServiceImpl(){}

    public static  UserService getInstance(){
        return  userService;
    }

    private static UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public User getUserById(String id) {
        try {
            if (id == null || id.isEmpty()){
                return null;
            }
            return userDao.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByName(String username) {
        try {
            if (username == null || username.isEmpty()){
                return null;
            }
            return userDao.getUserByName(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addUser(User user) {
        try {
            if (user == null){
                return false;
            }
            return userDao.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUserById(String id) {
        try {
            if (id == null || id.isEmpty()){
                return false;
            }
            return userDao.deleteUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUserByName(String username) {
        try {
            if (username == null || username.isEmpty()){
                return false;
            }
            return userDao.deleteUserByName(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            if (user == null){
                return false;
            }
            return userDao.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> findByPage(Integer page, Integer pageSize) {
        try {
            if (page == null || pageSize == null){
                return null;
            }
            return userDao.findByPage(page, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查看用户总数
     * @return
     */
    @Override
    public int getUserCount(){
        try {
            return userDao.getUserCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
