package com.model;

import java.util.List;

//用户大集合
public class Vectors {
    private List<Project> listProject;//项目
    private List<Team> listTeam;//团队
    private int userCount;//用户的信誉等级
    private Project uProject;//用户所接项目
    private List<String> teamMember;//用户所在队伍成员列表
    private Project tProject;//队伍所接项目
    private Team uteam;//用户所加入的团队

    public Team getUteam() {
        return uteam;
    }

    public void setUteam(Team uteam) {
        this.uteam = uteam;
    }

    public Project gettProject() {
        return tProject;
    }

    public void settProject(Project tProject) {
        this.tProject = tProject;
    }

    public List<String> getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(List<String> teamMember) {
        this.teamMember = teamMember;
    }

    public Project getuProject() {
        return uProject;
    }

    public void setuProject(Project uProject) {
        this.uProject = uProject;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public List<Project> getListProject() {
        return listProject;
    }

    public void setListProject(List<Project> listProject) {
        this.listProject = listProject;
    }

    public List<Team> getListTeam() {
        return listTeam;
    }

    public void setListTeam(List<Team> listTeam) {
        this.listTeam = listTeam;
    }
}
