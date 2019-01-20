package com.service;

import com.model.Project;
import com.model.User;

import java.io.File;
import java.util.List;

public interface UserService {
    public User getUser(String userNumber);
    public void updateUser(User user);
    public void updateImg(User user, File img);
    public boolean isJoinProject(String userNumber);
    public boolean isApplicant(String userNumber,String proName);
    public boolean isInApplicant(String userNumber);
    public void beApplicant(String userNumber,String proName);
    public String getPhone(String userNumber);
    public boolean isUser(String userNumber);
    public void addUser(User user);
    public byte[] getImg(String userNumber);
    public List<User> getExeUsers();
    public List<Project> getUProjectList(String userNumber);

}
