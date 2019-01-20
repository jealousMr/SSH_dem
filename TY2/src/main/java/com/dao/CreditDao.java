package com.dao;

import com.model.Credit;
import com.model.User;

import java.util.List;

public interface CreditDao {
    //获取用户评价信誉等级(平均值)
    public int getUserCount(String userNumber);
    //获得信誉评价（非评价，仅看一条数据）大于一的N个用户
    public List<User> bigCountUser(int n);
    //添加一个Credit对象
    public void addCredit(Credit credit);//一个项目对应一个Credit
    //更新Credit对象
    public void updateCredit(String proName,String message,int count);
    //更新执行人员字段
    public void updateUserNumber(String proName,String userNumber);
}
