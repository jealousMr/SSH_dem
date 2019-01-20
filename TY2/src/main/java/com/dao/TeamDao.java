package com.dao;

import com.model.Project;
import com.model.Team;
import com.model.User;

import java.util.List;

public interface TeamDao {
    public List<Team> getAllTeam();//获取全部队伍
    public boolean isJoinTeam(String userNumber);//判断用户是否已经加入团队
    public boolean isExist(String teamName);//判断是否存在此队伍
    public void addTeam(String teamName, User user);
    public void joinTeam(String teamName,String userNumber);//用户加入队伍
    public void outTeam(String userNumber);//用户退出队伍
    //获取某一队伍指定某一成员的头像(第n号，n《=6)
    public byte[] getMemberImg(String teamName,int n);
    public Team getTeam(String teamName);//通过队伍名获得队伍
    public void deleteOne(String userNumber,String teamName);//队长T人
    public boolean isCaptain(String userNumber);//是否是队长
    public void joinProject(String teamName,String proName);//队伍申请项目
    public boolean isApplyProject(String teamName);//判断队伍是否已近加入项目
    public Project getTeamProject(String teamName);//通过队伍名获取队伍所接项目
    public void updateTeamMessage(String teamName,String message);//更新队伍公告

}
