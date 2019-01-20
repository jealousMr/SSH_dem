package com.service;

import com.dao.TeamDao;
import com.model.Project;
import com.model.Team;
import com.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("teamService")
public class TeamServiceImpl implements TeamService{

    @Resource
    private TeamDao teamDao;

    @Override
    public List<Team> getAllTeam() {
        return teamDao.getAllTeam();
    }

    @Override
    public List getFiveTeam() {
        List<Team> teams=teamDao.getAllTeam();
        if (teams.size()<5)
            return teams;
        List<Team> show=new ArrayList<>();
        int begin=(int)(Math.random()*(teams.size()-1));
        for(int i=begin;i<teams.size();i++){
            if (show.size()>5)
                break;
            show.add(teams.get(i));
        }
        return show;
    }

    @Override
    public boolean isJoinTeam(String userNumber) {
        return teamDao.isJoinTeam(userNumber);
    }

    @Override
    public boolean isExist(String teamName) {
        return teamDao.isExist(teamName);
    }

    @Override
    public void addTeam(String teamName, User user) {
        teamDao.addTeam(teamName,user);
    }

    @Override
    public void joinTeam(String teamName, String userNumber) {
        teamDao.joinTeam(teamName,userNumber);
    }

    @Override
    public void outTeam(String userNumber) {
        teamDao.outTeam(userNumber);
    }

    @Override
    public byte[] getMemberImg(String teamName, int n) {
        return teamDao.getMemberImg(teamName,n);
    }

    @Override
    public Team getTeam(String teamName) {
        return teamDao.getTeam(teamName);
    }

    @Override
    public void deleteOne(String userNumber,String teamName) {
        teamDao.deleteOne(userNumber,teamName);
    }

    @Override
    public boolean isCaptain(String userNumber) {
        return teamDao.isCaptain(userNumber);
    }

    @Override
    public void joinProject(String teamName, String proName) {
        teamDao.joinProject(teamName,proName);
    }

    @Override
    public boolean isApplyProject(String teamName) {
        return teamDao.isApplyProject(teamName);
    }

    @Override
    public Project getTeamProject(String teamName) {
        return teamDao.getTeamProject(teamName);
    }

    @Override
    public void updateTeamMessage(String teamName, String message) {
        teamDao.updateTeamMessage(teamName,message);
    }
}
