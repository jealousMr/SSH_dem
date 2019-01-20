package com.model;

import java.util.List;

//发布者大集合
public class Some {

    private List<User> userList;//优秀执行者集合
    private List<Project> projectList;//用户发布的项目;
    private Project nproject;//用户当前要评论的项目

    public Project getNproject() {
        return nproject;
    }

    public void setNproject(Project nproject) {
        this.nproject = nproject;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
