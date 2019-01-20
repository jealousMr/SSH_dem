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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
@Transactional(rollbackFor = Exception.class)
@Repository("teamDao")
public class TeamDaoImpl implements TeamDao{
    @Resource
    private SessionFactory sessionFactory;
    @Override
    public List<Team> getAllTeam() {
        Query query=sessionFactory.getCurrentSession().createQuery("from Team ");
        List list=query.list();
        List<Team> tl=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            tl.add((Team) list.get(i));
        }
        return tl;
    }

    @Override
    public boolean isJoinTeam(String userNumber) {
        StringBuffer hql = new StringBuffer();
        hql.append("select u from User u where u.userNumber = '").append(userNumber).append("'");
        User user= (User)sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        if (user.getTeamName().equals("-1")||user.getTeamName().length()<3)
            return false;
        return true;
    }

    @Override
    public boolean isExist(String teamName) {
        Query query=sessionFactory.getCurrentSession().createQuery("from Team ");
        List list=query.list();
        List<Team> t=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            t.add((Team) list.get(i));
        }
        for (int j=0;j<t.size();j++){
            if (t.get(j).getTeamName().equals(teamName))
                return true;
        }
        return false;
    }

    @Override
    public void addTeam(String teamName, User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User nu= (User) session.get(User.class,user.getId());
        nu.setTeamName(teamName);
        session.getTransaction().commit();
        session.close();

        Team team=new Team();
        team.setTeamName(teamName);
        team.setCaptain(user.getUserNumber());
        team.setMember1("-1");
        team.setMember2("-1");
        team.setMember3("-1");
        team.setMember4("-1");
        team.setMember5("-1");
        team.setProName("-1");
        Session sc=sessionFactory.openSession();
        sc.beginTransaction();
        sc.save(team);
        sc.getTransaction().commit();
        sc.close();
    }

    @Override
    public void joinTeam(String teamName, String userNumber) {
        StringBuffer hql = new StringBuffer();
        hql.append("select u from User u where u.userNumber = '").append(userNumber).append("'");
        User user= (User)sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User nu= (User) session.get(User.class,user.getId());
        nu.setTeamName(teamName);
        session.getTransaction().commit();
        session.close();

        StringBuffer thql = new StringBuffer();
        thql.append("select t from Team t where t.teamName = '").append(teamName).append("'");
        Team team= (Team) sessionFactory.getCurrentSession().createQuery(thql.toString()).uniqueResult();
        Session ts=sessionFactory.openSession();
        ts.beginTransaction();
        Team t2= (Team) ts.get(Team.class,team.getId());
        if (t2.getMember1().length()<6||t2.getMember1()=="-1"||t2.getMember1()==null)
            t2.setMember1(userNumber);
        else if(t2.getMember2().length()<6 || t2.getMember2()=="-1"||t2.getMember2()==null)
            t2.setMember2(userNumber);
        else if(t2.getMember3().length()<6 || t2.getMember3()=="-1"||t2.getMember3()==null)
            t2.setMember3(userNumber);
        else if(t2.getMember4().length()<6 || t2.getMember4()=="-1"||t2.getMember4()==null)
            t2.setMember4(userNumber);
        else if(t2.getMember5().length()<6 || t2.getMember5()=="-1"||t2.getMember5()==null)
            t2.setMember5(userNumber);
        ts.getTransaction().commit();
        ts.close();
    }

    @Override
    public void outTeam(String userNumber) {
        String teamName="";
        StringBuffer hql = new StringBuffer();
        hql.append("select u from User u where u.userNumber = '").append(userNumber).append("'");
        User user= (User)sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        teamName=user.getTeamName();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User changeU= (User) session.get(User.class,user.getId());
        changeU.setTeamName("-1");
        session.getTransaction().commit();
        session.close();

        StringBuffer hql2=new StringBuffer();
        hql2.append("select t from Team t where t.teamName='").append(teamName).append("'");
        Team team= (Team) sessionFactory.getCurrentSession().createQuery(hql2.toString()).uniqueResult();
        boolean isCaptain=(team.getCaptain().equals(userNumber));//根据队长删除表
        Session sc=sessionFactory.openSession();
        sc.beginTransaction();
        try{
            Team changeT= (Team) sc.get(Team.class,team.getId());
            if (isCaptain){
                List<String> names=new ArrayList<>();
                if (team.getMember1()!=null && team.getMember1().length()>6)
                    names.add(team.getMember1());
                if (team.getMember2()!=null && team.getMember2().length()>6)
                    names.add(team.getMember2());
                if (team.getMember3()!=null && team.getMember3().length()>6)
                    names.add(team.getMember3());
                if (team.getMember4()!=null && team.getMember4().length()>6)
                    names.add(team.getMember4());
                if (team.getMember5()!=null && team.getMember5().length()>6)
                    names.add(team.getMember5());
                System.out.println("时队长-----------++"+names.size());
                if (names.size()>0)
                    for (int i=0;i<names.size();i++){
                        changeTeamName(names.get(i));
                    }
                sc.delete(changeT);
                sc.getTransaction().commit();
            }else {
                if (changeT.getMember1()!=null && changeT.getMember1().equals(userNumber))
                    changeT.setMember1("-1");
                else if (changeT.getMember2()!=null && changeT.getMember2().equals(userNumber))
                    changeT.setMember2("-1");
                else if (changeT.getMember3()!=null &&changeT.getMember3().equals(userNumber))
                    changeT.setMember3("-1");
                else if (changeT.getMember4()!=null &&changeT.getMember4().equals(userNumber))
                    changeT.setMember4("-1");
                else if (changeT.getMember5()!=null &&changeT.getMember5().equals(userNumber))
                    changeT.setMember5("-1");
                sc.getTransaction().commit();
            }
        }finally {
            sc.close();
        }
    }

    @Override
    public byte[] getMemberImg(String teamName, int n) {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from Team t where t.teamName = '").append(teamName).append("'");
        Team team= (Team) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        String member="0";
        switch (n){
            case 1:{
                member=team.getMember1();
                break;
            }
            case 2:{
                member=team.getMember2();
                break;
            }
            case 3:{
                member=team.getMember3();
                break;
            }
            case 4:{
                member=team.getMember4();
                break;
            }
            case 5:{
                member=team.getMember5();
                break;
            }
        }
        if(member==null || member.equals("-1") ||member.equals("0")){
            try{
                URL url=TeamDaoImpl.class.getResource("/null.png");
                String path = String.valueOf(url);
                path=path.substring(6);
                File file = new File(path);
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                byte[] content = new byte[fis.available()];
                fis.read(content);
                return content;
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        if (!member.equals("0")){
            StringBuffer ss = new StringBuffer();
            ss.append("select u from User u where u.userNumber = '").append(member).append("'");
            User user= (User) sessionFactory.getCurrentSession().createQuery(ss.toString()).uniqueResult();
            return user.getImg();
        }
        return new byte[0];
    }

    @Override
    public Team getTeam(String teamName) {
        StringBuffer ss = new StringBuffer();
        ss.append("select t from Team t where t.teamName = '").append(teamName).append("'");
        Team team= (Team) sessionFactory.getCurrentSession().createQuery(ss.toString()).uniqueResult();
        return team;
    }

    @Override
    public void deleteOne(String userNumber,String teamName) {
        //更新user表得teamname字段
       StringBuffer hql1=new StringBuffer();
       hql1.append("select u from User u where u.userNumber='").append(userNumber).append("'");
       User u= (User) sessionFactory.getCurrentSession().createQuery(hql1.toString()).uniqueResult();
       Session session=sessionFactory.openSession();
       session.beginTransaction();
       u.setTeamName("-1");
        session.getTransaction().commit();
        session.close();

//        更新team表的member字段
        StringBuffer hql2 = new StringBuffer();
        hql2.append("select t from Team t where t.teamName = '").append(teamName).append("'");
        Team team= (Team) sessionFactory.getCurrentSession().createQuery(hql2.toString()).uniqueResult();
        Session s=sessionFactory.openSession();
        s.beginTransaction();
        if (team.getMember1().equals(userNumber))
            team.setMember1("-1");
        else if (team.getMember2().equals(userNumber))
            team.setMember2("-1");
        else if (team.getMember3().equals(userNumber))
            team.setMember3("-1");
        else if (team.getMember4().equals(userNumber))
            team.setMember4("-1");
        else if (team.getMember5().equals(userNumber))
            team.setMember5("-1");
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public boolean isCaptain(String userNumber) {
        StringBuffer hql = new StringBuffer();
        hql.append("select u from User u where u.userNumber = '").append(userNumber).append("'");
        User user= (User)sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        String teamName=user.getTeamName();
        StringBuffer sb=new StringBuffer();
        sb.append("select t from Team t where t.teamName='").append(teamName).append("'");
        Team team= (Team) sessionFactory.getCurrentSession().createQuery(sb.toString()).uniqueResult();
        if (team.getCaptain().equals(userNumber))
            return true;
        return false;
    }

    @Override
    public void joinProject(String teamName,String proName) {

        //更新team表
        StringBuffer hql = new StringBuffer();
        hql.append("select t from Team t where t.teamName = '").append(teamName).append("'");
        Team team= (Team) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        team.setProName(proName);
        session.getTransaction().commit();
        session.close();

        //更新project表
        StringBuffer s=new StringBuffer();
        s.append("select p from Project p where p.proName='").append(proName).append("'");
        Project project= (Project) sessionFactory.getCurrentSession().createQuery(s.toString()).uniqueResult();
        Session so=sessionFactory.openSession();
        so.beginTransaction();
        project.setApplicant(teamName);
        project.setIsTeam(2);//2表示团队完成项目
        so.getTransaction().commit();
        so.close();
    }

    @Override
    public boolean isApplyProject(String teamName) {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from Team t where t.teamName = '").append(teamName).append("'");
        Team team= (Team) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        if (team.getProName()==null||team.getProName().equals("-1"))
            return false;
        return true;
    }

    @Override
    public Project getTeamProject(String teamName) {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from Team t where t.teamName = '").append(teamName).append("'");
        Team team= (Team) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        String proName=team.getProName();
        if (proName.equals("-1")||proName==null)
            return null;
        StringBuffer hql2 = new StringBuffer();
        hql2.append("select p from Project p where p.proName = '").append(proName).append("'");
        Project project= (Project) sessionFactory.getCurrentSession().createQuery(hql2.toString()).uniqueResult();
        return project;
    }

    @Override
    public void updateTeamMessage(String teamName,String message) {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from Team t where t.teamName = '").append(teamName).append("'");
        Team team= (Team) sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        team.setMessage(message);
        session.getTransaction().commit();
        session.close();
    }

    //更新某一用户的listName为-1
    private void changeTeamName(String userNumber){
        StringBuffer hql = new StringBuffer();
        hql.append("select u from User u where u.userNumber = '").append(userNumber).append("'");
        User user= (User)sessionFactory.getCurrentSession().createQuery(hql.toString()).uniqueResult();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User changeU= (User) session.get(User.class,user.getId());
        changeU.setTeamName("-1");
        session.getTransaction().commit();
        session.close();
    }
}
