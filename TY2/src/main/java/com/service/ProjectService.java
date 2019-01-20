package com.service;

import com.model.Project;

import java.io.File;
import java.util.List;

public interface ProjectService {
    public List<Project> getAllProject();
    public List<Project> getThreeProject();//获得随机三条数据.
    public List<Project> projectTypes(int n);
    public boolean isRightProName(String proName);
    public boolean isAorE(String proName);
    public Project getProject(String proName);
    public boolean isInProject(String proName);
    public void addProject(Project project);
    public byte[] getProImg(String proName);
    public void changeAtoE(String proName);
    public void cancelApplicant(String proName);
    public void updateProImg(String proName, File file);
    public void updateProContent(String proName,String proContent);
    public void endProject(String proName);
    public boolean hasExecutor(String proName);
    public List<Project> randomPro();
    public byte[] getImgByProName(String proName);
    public List<Project> getProjectByType(int type);
    public void endTeamProject(String proName);
    public List<Project> searchPros(String proName);
}
