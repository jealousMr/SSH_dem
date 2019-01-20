package com.service;

import com.dao.ProjectDao;
import com.model.Project;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service("projectService")
public class ProjectServiceImpl implements  ProjectService{
    @Resource
    private ProjectDao projectDao;
    @Override
    public List<Project> getAllProject() {
        return projectDao.getAllProject();
    }

    @Override
    public List<Project> getThreeProject() {

        List<Project> res=new ArrayList<>();
        List<Project> cc=projectDao.getAllProject();
        if(cc==null||cc.size()==0){//project表为空
            return res;
        }
        int a1=new Random().nextInt(cc.size());
        int a2=new Random().nextInt(cc.size());
        int a3=new Random().nextInt(cc.size());
        res.add(cc.get(a1));
        res.add(cc.get(a2));
        res.add(cc.get(a3));
        return res;
    }

    @Override
    public List<Project> projectTypes(int n) {
        return projectDao.projectTypes(n);
    }

    @Override
    public boolean isRightProName(String proName) {
        return projectDao.isRightProName(proName);
    }

    @Override
    public boolean isAorE(String proName) {
        return projectDao.isAorE(proName);
    }

    @Override
    public Project getProject(String proName) {
        return projectDao.getProject(proName);
    }

    @Override
    public boolean isInProject(String proName) {
        return projectDao.isInProject(proName);
    }

    @Override
    public void addProject(Project project) {
        projectDao.addProject(project);
    }

    @Override
    public byte[] getProImg(String proName) {
        return projectDao.getProImg(proName);
    }

    @Override
    public void changeAtoE(String proName) {
        projectDao.changeAtoE(proName);
    }

    @Override
    public void cancelApplicant(String proName) {
        projectDao.cancelApplicant(proName);
    }

    @Override
    public void updateProImg(String proName, File file) {
        projectDao.updateProImg(proName,file);
    }

    @Override
    public void updateProContent(String proName, String proContent) {
        projectDao.updateProContent(proName,proContent);
    }

    @Override
    public void endProject(String proName) {
        projectDao.endProject(proName);
    }

    @Override
    public boolean hasExecutor(String proName) {
        return projectDao.hasExecutor(proName);
    }

    @Override
    public List<Project> randomPro() {
        return projectDao.randomPro();
    }

    @Override
    public byte[] getImgByProName(String proName) {
        return projectDao.getImgByProName(proName);
    }

    @Override
    public List<Project> getProjectByType(int type) {
        return projectDao.getProjectByType(type);
    }

    @Override
    public void endTeamProject(String proName) {
        projectDao.endTeamProject(proName);
    }

    @Override
    public List<Project> searchPros(String proName) {
        return projectDao.searchPros(proName);
    }
}
