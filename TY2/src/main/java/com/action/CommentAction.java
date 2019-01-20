package com.action;

import com.alibaba.fastjson.JSONObject;
import com.model.Project;
import com.model.Some;
import com.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.CreditService;
import com.service.ProjectService;
import com.service.UserService;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

@Controller("commentAction")
@Scope("prototype")
public class CommentAction extends ActionSupport {
    @Resource
    private CreditService creditService;
    @Resource
    private ProjectService projectService;
    @Resource
    private UserService userService;

    private HttpServletResponse response;
    private HttpServletRequest request;

    private String result;
    private String proName;
    private String userNumber;
    private int count;
    private String message;
    private String cookieUserNumber;
    private String cookieUserId;

    public void setCookieUserNumber(String cookieUserNumber) {
        this.cookieUserNumber = cookieUserNumber;
    }

    public void setCookieUserId(String cookieUserId) {
        this.cookieUserId = cookieUserId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    //用户从个人中心进入评论页面
    public String enterCommentPage(){
        JSONObject jsonObject=new JSONObject();
        Project project=projectService.getProject(proName);
        jsonObject.put("proName",project.getProName());
        jsonObject.put("executor",project.getExecutor());
        result=jsonObject.toJSONString();
        return SUCCESS;
    }
    //获取执行者(用户)的头像
    public String getTu(){
        User user=userService.getUser(userNumber);
        byte[] img=user.getImg();
        if (img==null)
            return null;
        ServletOutputStream out = null;
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("multipart/form-data");
        try {
            out=response.getOutputStream();
            out.write(user.getImg());
            out.flush();
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    //评论页面搜索获得项目
    public String findPro(){
        JSONObject jsonObject=new JSONObject();
        Project p=null;
        p=projectService.getProject(proName);
        if (p==null){
            jsonObject.put("is","不存在此项目");
            result=jsonObject.toJSONString();
            return SUCCESS;
        }
        ActionContext ac=ActionContext.getContext();
        Map session = ac.getSession();
        Some some= (Some) session.get("some");
        some.setNproject(p);
        session.put("some",some);
        jsonObject.put("is","1");
        jsonObject.put("executor",p.getExecutor());
        jsonObject.put("proName",p.getProName());
        result=jsonObject.toJSONString();
        return SUCCESS;
    }
    //更新评论
    public String commentMes(){
        creditService.updateCredit(proName,message,count);
        JSONObject jo=new JSONObject();
        jo.put("is","评论成功");
        result=jo.toJSONString();
        return SUCCESS;
    }
}
