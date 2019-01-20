package com.dao;

import com.model.Project;
import com.model.Team;
import com.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Transactional(rollbackFor = Exception.class)
@Repository("projectDao")
public class ProjectDaoImpl implements ProjectDao {
    @Resource
    private SessionFactory sessionFactory;
    @Override
    public List<Project> getAllProject() {
        Query query=sessionFactory.getCurrentSession().createQuery("from Project ");
        List list=query.list();
        List<Project> li=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            li.add((Project) list.get(i));
        }
        return li;
    }

    @Override
    public List<Project> projectTypes(int n) {
        Query query=sessionFactory.getCurrentSession().createQuery("from Project ");
        List list=query.list();
        List<Project> li=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            li.add((Project) list.get(i));
        }
        List<Project> res=new ArrayList<>();
        for (int y=0;y<li.size();y++){
            if (li.get(y).getType()==n)
                res.add(li.get(y));
        }
        return res;
    }

    @Override
    public boolean isRightProName(String proName) {
        Query query=sessionFactory.getCurrentSession().createQuery("from Project ");
        List list=query.list();
        List<Project> li=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            li.add((Project) list.get(i));
        }
      for(int i=0;i<li.size();i++){
          if (proName.equals(li.get(i).getProName()))
              return true;
      }
      return false;
    }

    @Override
    public boolean isAorE(String proName) {
        StringBuffer hql = new StringBuffer();
        hql.append("select p from Project p where p.proName = '").append(proName).append("'");
        Project project= (Project) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        String applicant=project.getApplicant();
        String executor=project.getExecutor();
        if (applicant!=null&&!applicant.equals("-1"))
            return true;
        else if(executor!=null&&!executor.equals("-1")){
            return true;
        }
        return false;
    }

    @Override
    public Project getProject(String proName) {
        StringBuffer hql = new StringBuffer();
        hql.append("select p from Project p where p.proName = '").append(proName).append("'");
        Project project= (Project) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        return project;
    }

    @Override
    public boolean isInProject(String proName) {
        Project p=null;
        StringBuffer hql = new StringBuffer();
        hql.append("select p from Project p where p.proName = '").append(proName).append("'");
        p=(Project) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        if (p==null)
             return false;
        else
            return true;
    }

    @Override
    public void addProject(Project project) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(project);
        session.beginTransaction().commit();
        session.close();
    }

    @Override
    public byte[] getProImg(String proName) {
        StringBuffer hql = new StringBuffer();
        hql.append("select p from Project p where p.proName = '").append(proName).append("'");
        Project project= (Project) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        byte[] temp=null;
        temp=project.getImg();
        return temp;
    }

    @Override
    public void changeAtoE(String proName) {
        StringBuffer hql = new StringBuffer();
        hql.append("select p from Project p where p.proName = '").append(proName).append("'");
        Project project= (Project) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        String applicant=project.getApplicant();
        if(applicant=="-1")
            return;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
       project= (Project) session.get(Project.class,project.getId());
       project.setExecutor(applicant);
       project.setApplicant("-1");
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void cancelApplicant(String proName) {
        StringBuffer hql = new StringBuffer();
        hql.append("select p from Project p where p.proName = '").append(proName).append("'");
        Project project= (Project) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        project= (Project) session.get(Project.class,project.getId());
        project.setApplicant("-1");
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateProImg(String proName, File file) {
        StringBuffer hql = new StringBuffer();
        hql.append("select p from Project p where p.proName = '").append(proName).append("'");
        Project project= (Project) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        int ID=project.getId();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
       project= (Project) session.get(Project.class,ID);
       try{
           FileInputStream fis = new FileInputStream(file);
           byte[] content = new byte[fis.available()];
           fis.read(content);
           project.setImg(content);
           session.getTransaction().commit();
       }catch (IOException e){
           e.printStackTrace();
       }finally {
           session.close();
       }
    }

    @Override
    public void updateProContent(String proName, String proContent) {
        StringBuffer hql = new StringBuffer();
        hql.append("select p from Project p where p.proName = '").append(proName).append("'");
        Project project= (Project) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        int ID=project.getId();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        project= (Project) session.get(Project.class,ID);
        project.setProContent(proContent);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void endProject(String proName) {
        String userNumber="-1";
        StringBuffer hql = new StringBuffer();
        hql.append("select p from Project p where p.proName = '").append(proName).append("'");
        Project project= (Project) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        int ID=project.getId();
        userNumber=project.getExecutor();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        project= (Project) session.get(Project.class,ID);
        project.setType(-1);
        session.getTransaction().commit();
        session.close();

        StringBuffer str=new StringBuffer();
        str.append("select u from User u where u.userNumber='").append(userNumber).append("'");
        User user= (User) sessionFactory.getCurrentSession().createQuery(str.toString()).uniqueResult();
        Session s=sessionFactory.openSession();
        s.beginTransaction();
        user= (User) s.get(User.class,user.getId());
        user.setProName("-1");
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public boolean hasExecutor(String proName) {
        StringBuffer hql = new StringBuffer();
        hql.append("select p from Project p where p.proName = '").append(proName).append("'");
        Project project= (Project) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        if (project.getExecutor()==null||project.getExecutor().equals("-1")){
            return false;
        }
        return true;
    }

    @Override
    public List<Project> randomPro() {
        Query query=sessionFactory.getCurrentSession().createQuery("from Project p");
       int size=query.list().size();
       if(size>=6){
           Random r=new Random();
           query.setMaxResults(6);
           int a=r.nextInt(size)+1;
           a=(a>=1&&a<size)?a:1;
           query.setFirstResult(a);
           List<Project> li=new ArrayList<>();
           for(int i=0;i<query.list().size();i++){
               li.add((Project) query.list().get(i));
           }
           return li;
       }else
       {
           List<Project> li=new ArrayList<>();
           for(int i=0;i<query.list().size();i++){
               li.add((Project) query.list().get(i));
           }
           return li;
       }


    }

    @Override
    public byte[] getImgByProName(String proName) {
        StringBuffer hql = new StringBuffer();
        hql.append("select p from Project p where p.proName = '").append(proName).append("'");
        Project project= (Project) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        String userNumber=project.getPublisher();
        StringBuffer hql2=new StringBuffer();
        hql2.append("select u from User u where u.userNumber='").append(userNumber).append("'");
        User us= (User) sessionFactory.getCurrentSession().createQuery(hql2.toString()).uniqueResult();
        return us.getImg();
    }

    @Override
    public List<Project> getProjectByType(int type) {
        Query query=sessionFactory.getCurrentSession().createQuery("from Project p where type="+type);
        List<Project>list=query.list();
        return list;
    }

    @Override
    public void endTeamProject(String proName) {
        StringBuffer hql = new StringBuffer();
        hql.append("select p from Project p where p.proName = '").append(proName).append("'");
        Project project= (Project) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        //更新project的type -1;
        StringBuffer hql2=new StringBuffer();
        hql2.append("update Project p set p.type=-1").append(" where proName='").append(proName).append("'");
        Query query =sessionFactory.getCurrentSession().createQuery(hql2.toString());
        query.executeUpdate();
        //更新Team表的proName -1;
        StringBuffer hql3 = new StringBuffer();
        hql3.append("update Team t set t.proName='").append("-1'").append(" where teamName='").append(project.getExecutor()).append("'");
        Query query3 =sessionFactory.getCurrentSession().createQuery(hql3.toString());
        query3.executeUpdate();
    }

    @Override
    public List<Project> searchPros(String proName) {
        Query query=sessionFactory.getCurrentSession().createQuery("from Project p where p.proName like :param");
        query.setString("param","%"+proName+"%");
        return query.list();
    }
}
