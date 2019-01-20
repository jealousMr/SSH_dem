package com.service;

import com.dao.UserDao;
import com.model.Project;
import com.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public User getUser(String userNumber) {
        return userDao.getUser(userNumber);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void updateImg(User user, File img) {
        userDao.updateImg(user,img);
    }

    @Override
    public boolean isJoinProject(String userNumber) {
        return userDao.isJoinProject(userNumber);
    }

    @Override
    public boolean isApplicant(String userNumber, String proName) {
        return userDao.isApplicant(userNumber,proName);
    }

    @Override
    public boolean isInApplicant(String userNumber) {
        return userDao.isInApplicant(userNumber);
    }

    @Override
    public void beApplicant(String userNumber, String proName) {
        userDao.beApplicant(userNumber,proName);
    }

    @Override
    public String getPhone(String userNumber) {
        return userDao.getPhone(userNumber);
    }

    @Override
    public boolean isUser(String userNumber) {
        return userDao.isUser(userNumber);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public byte[] getImg(String userNumber) {
        return userDao.getImg(userNumber);
    }

    @Override
    public List<User> getExeUsers() {
        return userDao.getExeUsers();
    }


    @Override
    public List<Project> getUProjectList(String userNumber) {
        return userDao.getUProjectList(userNumber);
    }


}
