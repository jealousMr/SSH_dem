package com.dao;

import com.model.Project;
import com.model.User;

import java.io.File;
import java.util.List;

public interface UserDao {
    public User getUser(String userNumber);
    public void updateUser(User user);
    public void updateImg(User user, File img);//要更新的user和更新的图片
    public boolean isJoinProject(String userNumber);//用户是否已经加入项目（个人）
    public boolean isApplicant(String userNumber,String proName);//用户是否成为此项目申请人
    public boolean isInApplicant(String userNumber);//用户是否处于项目申请者的状态
    public void beApplicant(String userNumber,String proName);//用户成为项目申请者;
    public String getPhone(String userNumber);//获得用户电话号码
    public boolean isUser(String userNumber);//判断数据库是否存在用户
    public void addUser(User user);//添加用户
    public byte[] getImg(String userNumber);//获得用户头像
    //_--------------------------------------------------------------//第二个账号
    public List<User> getExeUsers();//获取所有执行者
    public List<Project> getUProjectList(String userNumber);//获取用户发布的项目
}
