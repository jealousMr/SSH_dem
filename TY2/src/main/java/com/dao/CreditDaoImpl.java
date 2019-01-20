package com.dao;

import com.model.Credit;
import com.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Repository("creditDao")
public class CreditDaoImpl implements CreditDao {
    @Resource
    private SessionFactory sessionFactory;
    @Override
    public int getUserCount(String userNumber) {
        StringBuffer hql=new StringBuffer();
        hql.append("select c from Credit c where c.userNumber='").append(userNumber).append("'");
        Query query =sessionFactory.getCurrentSession().createQuery(hql.toString());
        List<Credit> list=query.list();
        int sum=0;
        if (list.size()==0)
            return 1;
        else{
            for(int i=0;i<list.size();i++){
                sum+=list.get(i).getCount();
            }
            return (int)sum/list.size();
        }
    }

    @Override
    public List<User> bigCountUser(int n) {
        StringBuffer hql=new StringBuffer();
        hql.append("select c from Credit c where c.count>=1");
        Query query =sessionFactory.getCurrentSession().createQuery(hql.toString());
        query.setMaxResults(n);
        List<User> list=query.list();
        return list;
    }

    @Override
    public void addCredit(Credit credit) {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(credit);
        session.beginTransaction().commit();
        session.close();
    }

    @Override
    public void updateCredit(String proName, String message, int count) {
        StringBuffer hql=new StringBuffer();
        hql.append("update Credit c set c.message='").append(message).append("',c.count=").append(count).append(" where proName='").append(proName).append("'");
        Query query =sessionFactory.getCurrentSession().createQuery(hql.toString());
        query.executeUpdate();
    }

    @Override
    public void updateUserNumber(String proName, String userNumber) {
        StringBuffer hql=new StringBuffer();
        hql.append("update Credit c set c.userNumber='").append(userNumber).append("'").append(" where proName='").append(proName).append("'");
        Query query =sessionFactory.getCurrentSession().createQuery(hql.toString());
        query.executeUpdate();
    }

}
