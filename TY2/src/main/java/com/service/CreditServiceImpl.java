package com.service;

import com.dao.CreditDao;
import com.model.Credit;
import com.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("creditService")
public class CreditServiceImpl implements CreditService {
    @Resource
    private CreditDao creditDao;
    @Override
    public int getUserCount(String userNumber) {
        return creditDao.getUserCount(userNumber);
    }

    @Override
    public List<User> bigCountUser(int n) {
        return creditDao.bigCountUser(n);
    }

    @Override
    public void addCredit(Credit credit) {
        creditDao.addCredit(credit);
    }

    @Override
    public void updateCredit(String proName, String message, int count) {
        creditDao.updateCredit(proName,message,count);
    }

    @Override
    public void updateUserNumber(String proName, String userNumber) {
        creditDao.updateUserNumber(proName,userNumber);
    }


}
