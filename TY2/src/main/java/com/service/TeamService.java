package com.service;

import com.model.Project;
import com.model.Team;
import com.model.User;

import java.util.List;

public interface TeamService {
    public List<Team> getAllTeam();
    public List getFiveTeam();//随机获得5条团队信息
    public boolean isJoinTeam(String userNumber);
    public boolean isExist(String teamName);
    public void addTeam(String teamName, User user);
    public void joinTeam(String teamName,String userNumber);
    public void outTeam(String userNumber);
    public byte[] getMemberImg(String teamName,int n);
    public Team getTeam(String teamName);
    public void deleteOne(String userNumber,String teamName);
    public boolean isCaptain(String userNumber);
    public void joinProject(String teamName,String proName);
    public boolean isApplyProject(String teamName);
    public Project getTeamProject(String teamName);
    public void updateTeamMessage(String teamName,String message);
}
