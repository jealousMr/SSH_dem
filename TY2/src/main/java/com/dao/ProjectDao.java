package com.dao;

import com.model.Project;

import java.io.File;
import java.util.List;

public interface ProjectDao {
    //获得数据库中全部数据
    public List<Project> getAllProject();
    //根据类型放回所有相同类型的项目
    public List<Project> projectTypes(int n);
    //项目是否存在
    public boolean isRightProName(String proName);
    //项目是否有申请者或执行者
    public boolean isAorE(String proName);
    //通过项目名获取项目
    public Project getProject(String proName);
    //判断是否已经存在项目名
    public boolean isInProject(String proName);
    //添加项目
    public void addProject(Project project);
    //通过项目名获得项目图片
    public byte[] getProImg(String proName);
    //applicant->executor
    public void changeAtoE(String proName);
    //用户2取消申请人资格
    public void cancelApplicant(String proName);
    //更新项目图片
    public void updateProImg(String proName, File file);
    //更新proContent
    public void updateProContent(String proName,String proContent);
    //结算项目
    public void endProject(String proName);
    //判断项目是否有执行人
    public boolean hasExecutor(String proName);
    //随机获得3个到6个项目
    public List<Project> randomPro();
    //用户通过项目名获得发布者的头像
    public byte[] getImgByProName(String proName);
    //根据type获得项目
    public List<Project> getProjectByType(int type);
    //结算团队项目
    public void endTeamProject(String proName);
    //模糊查找项目
    public List<Project> searchPros(String proName);
}
