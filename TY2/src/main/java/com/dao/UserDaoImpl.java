package com.dao;

import com.model.Credit;
import com.model.Project;
import com.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Repository("loginDao")
public class UserDaoImpl implements UserDao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;


    @Override
    public User getUser(String userNumber) {
        StringBuffer hql = new StringBuffer();
        hql.append("select u from User u where u.userNumber = '").append(userNumber).append("'");
        User user= (User)sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        return user;
    }

    @Override
    public void updateUser(User user) {
        String hql="update User u set u.email='"+user.getEmail()+"',u.realName='"+user.getRealName()+"',u.nickName='"+user.getNickName()+"',u.phoneNumber='"+user.getPhoneNumber()+"'"+
                "where u.userNumber='"+user.getUserNumber()+"'";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.executeUpdate();
    }

    @Override
    public void updateImg(User user, File img) {
        StringBuffer hql = new StringBuffer();
        hql.append("select u from User u where u.userNumber = '").append(user.getUserNumber()).append("'");
        User tuser= (User)sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        int ID=tuser.getId();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        tuser= (User) session.get(User.class,ID);
        try {
            FileInputStream fis = new FileInputStream(img);
            byte[] content = new byte[fis.available()];
            fis.read(content);
            tuser.setImg(content);
            session.getTransaction().commit();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public boolean isJoinProject(String userNumber) {
        StringBuffer hql = new StringBuffer();
        hql.append("select u from User u where u.userNumber = '").append(userNumber).append("'");
        User tuser= (User)sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        String is=tuser.getProName();
        if (is==null||is.equals("-1"))
            return false;
        return true;
    }

    @Override
    public boolean isApplicant(String userNumber, String proName) {
        StringBuffer hql = new StringBuffer();
        hql.append("select p from Project p where p.proName = '").append(proName).append("'");
        Project project= (Project) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        if (project.getApplicant().equals(userNumber)&&project.getApplicant()!=null)
            return true;
        return false;
    }

    @Override
    public boolean isInApplicant(String userNumber) {
        Query query=sessionFactory.getCurrentSession().createQuery("from Project ");
        List list=query.list();
        List<Project> li=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            li.add((Project) list.get(i));
        }
        for (int k=0;k<li.size();k++){
            if (li.get(k).getApplicant()!=null&&li.get(k).getApplicant().equals(userNumber))
                return true;
        }
        return false;
    }

    @Override
    public void beApplicant(String userNumber,String proName) {
        StringBuffer str=new StringBuffer();
        str.append("select u from User u where u.userNumber='").append(userNumber).append("'");
        User user= (User) sessionFactory.getCurrentSession().createQuery(str.toString()).uniqueResult();
        Session s=sessionFactory.openSession();
        s.beginTransaction();
        user= (User) s.get(User.class,user.getId());
        user.setProName(proName);
        s.getTransaction().commit();
        s.close();

        StringBuffer hql = new StringBuffer();
        hql.append("select p from Project p where p.proName = '").append(proName).append("'");
        Project project= (Project) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        int ID=project.getId();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        project= (Project) session.get(Project.class,ID);
        try {
            project.setApplicant(userNumber);
            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

    @Override
    public String getPhone(String userNumber) {
        StringBuffer hql2=new StringBuffer();
        hql2.append("select u from User u where u.userNumber='").append(userNumber).append("'");
        User us= (User) sessionFactory.getCurrentSession().createQuery(hql2.toString()).uniqueResult();
        return us.getPhoneNumber();
    }

    @Override
    public boolean isUser(String userNumber) {
        StringBuffer hql=new StringBuffer();
        hql.append("select u from User u where u.userNumber='").append(userNumber).append("'");
        User user=null;
        try{
            user=(User) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (user==null)
            return false;
        return true;
    }

    @Override
    public void addUser(User user) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.beginTransaction().commit();
        session.close();
    }

    @Override
    public byte[] getImg(String userNumber) {
        StringBuffer hql2=new StringBuffer();
        hql2.append("select u from User u where u.userNumber='").append(userNumber).append("'");
        User us= (User) sessionFactory.getCurrentSession().createQuery(hql2.toString()).uniqueResult();
        return us.getImg();
    }

    @Override
    public List<User> getExeUsers() {
        Query query=sessionFactory.getCurrentSession().createQuery("from User ");
        List list=query.list();
        List<User> tl=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            tl.add((User) list.get(i));
        }
        List<User> res=new ArrayList<>();
        for(int j=0;j<tl.size();j++){
            if (tl.get(j).getType()==1)
                res.add(tl.get(j));
        }
        return res;
    }

    @Override
    public List<Project> getUProjectList(String userNumber) {
        StringBuffer hql=new StringBuffer();
        hql.append("from Project where publisher='").append(userNumber).append("'");
        Query query=sessionFactory.getCurrentSession().createQuery(hql.toString());
        List list=query.list();
        List<Project> li=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            li.add((Project) list.get(i));
        }
        return list;
    }
}
