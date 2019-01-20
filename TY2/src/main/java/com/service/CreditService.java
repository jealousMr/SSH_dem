package com.service;

import com.model.Credit;
import com.model.User;

import java.util.List;

public interface CreditService {
    public int getUserCount(String userNumber);
    public List<User> bigCountUser(int n);
    public void addCredit(Credit credit);
    public void updateCredit(String proName,String message,int count);
    public void updateUserNumber(String proName,String userNumber);
}
