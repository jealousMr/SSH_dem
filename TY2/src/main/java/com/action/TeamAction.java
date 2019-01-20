package com.action;

import com.alibaba.fastjson.JSONObject;
import com.model.Project;
import com.model.Team;
import com.model.User;
import com.model.Vectors;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ProjectService;
import com.service.TeamService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller("teamAction")
@Scope("prototype")
public class TeamAction extends ActionSupport {
    @Resource
    private TeamService teamService;
    @Resource
    private UserService userService;
    @Resource
    private ProjectService projectService;

    private String result;
    private String teamName;
    private String userNumber;
    private int member;
    private String proName;
    private String message;

    HttpServletResponse response;
    HttpServletRequest request;

    private String cookieUserNumber;
    private String cookieUserId;

    public void setCookieUserNumber(String cookieUserNumber) {
        this.cookieUserNumber = cookieUserNumber;
    }

    public void setCookieUserId(String cookieUserId) {
        this.cookieUserId = cookieUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }


    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public void setMember(int member) {
        this.member = member;
    }

    public int getMember() {
        return member;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    //用户进入team，更新session
    public String enterTeamPage(){
        request=ServletActionContext.getRequest();
        if (request.getCookies()!=null){
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("userNumber")) {
                    try {
                        cookieUserNumber= URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }else if (cookie.getName().equals("id")){
                    try {
                        cookieUserId= URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        ActionContext ac=ActionContext.getContext();
        Map session=ac.getSession();
        Vectors vc= (Vectors) session.get(cookieUserId);
        vc.setListTeam(teamService.getFiveTeam());//随机获取团队用于显示
        User user= (User) session.get(cookieUserNumber);
        if(user.getTeamName().equals("-1")||user.getTeamName()==null){
            session.put(cookieUserId,vc);
            return SUCCESS;
        }else {
            Team team=teamService.getTeam(user.getTeamName());
            vc.setUteam(team);//用户所加队伍
            if(!team.getProName().equals("-1")){//用户队伍所接项目
                Project project=projectService.getProject(team.getProName());
                vc.settProject(project);
            }
            session.put(cookieUserId,vc);
            return SUCCESS;
        }
    }


    //搜索队伍
    public String searchTeam(){
        boolean is=teamService.isExist(teamName);
        JSONObject jsonObject=new JSONObject();
        if(is){
            jsonObject.put("status","1");
            result=jsonObject.toJSONString();
        }else{
            jsonObject.put("status","2");
            result=jsonObject.toJSONString();
        }
        return SUCCESS;
    }
    //创建队伍
    public String createTeam(){
        request=ServletActionContext.getRequest();
        if (request.getCookies()!=null){
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("userNumber")) {
                    try {
                        cookieUserNumber= URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }else if (cookie.getName().equals("id")){
                    try {
                        cookieUserId= URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        JSONObject jsonObject=new JSONObject();
        ActionContext ac=ActionContext.getContext();
        Map session=ac.getSession();
        User user= (User) session.get(cookieUserNumber);
        //判断队伍是否已近存在
        if (teamService.isExist(teamName)){
            jsonObject.put("is","此队伍已经存在");
            result=jsonObject.toJSONString();
            return SUCCESS;
        }
        //判断用户是否已近加入队伍
        if(teamService.isJoinTeam(user.getUserNumber())){
            jsonObject.put("is","你已近加入队伍，退出后可创建");
            result=jsonObject.toJSONString();
            return SUCCESS;
        }

        teamService.addTeam(teamName,user);
        jsonObject.put("is","创建成功");
        result=jsonObject.toJSONString();
        //更新session
        user.setTeamName(teamName);
        session.put(cookieUserNumber,user);
        return SUCCESS;
    }
    //加入队伍
    public String joinTeam(){
        request=ServletActionContext.getRequest();
        if (request.getCookies()!=null){
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("userNumber")) {
                    try {
                        cookieUserNumber= URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }else if (cookie.getName().equals("id")){
                    try {
                        cookieUserId= URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        JSONObject jsonObject=new JSONObject();
        ActionContext ac=ActionContext.getContext();
        Map session=ac.getSession();
        User user= (User) session.get(cookieUserNumber);
        //判断队伍是否存在
        if (!teamService.isExist(teamName)){
            jsonObject.put("is","此队伍不存在");
            result=jsonObject.toJSONString();
            return SUCCESS;
        }
        //判断用户是否已近加入队伍
        if(teamService.isJoinTeam(user.getUserNumber())){
            jsonObject.put("is","你已经加入队伍，退出后可加入");
            result=jsonObject.toJSONString();
            return SUCCESS;
        }
        teamService.joinTeam(teamName,user.getUserNumber());
        jsonObject.put("is","加入成功");
        result=jsonObject.toJSONString();
        //更新session
        user.setTeamName(teamName);
        session.put(cookieUserNumber,user);
        return SUCCESS;
    }
    //退出队伍
    public String outTeam(){
        request=ServletActionContext.getRequest();
        if (request.getCookies()!=null){
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("userNumber")) {
                    try {
                        cookieUserNumber= URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }else if (cookie.getName().equals("id")){
                    try {
                        cookieUserId= URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        JSONObject jsonObject=new JSONObject();
        ActionContext ac=ActionContext.getContext();
        Map session=ac.getSession();
        User user= (User) session.get(cookieUserNumber);
        Vectors vc= (Vectors) session.get(cookieUserId);
        if(!vc.getUteam().getProName().equals("-1")){
            jsonObject.put("is","项目未执行完成，不能退出团队");
            result=jsonObject.toJSONString();
            return SUCCESS;
        }
        teamService.outTeam(user.getUserNumber());
        jsonObject.put("is","退出成功");
        result=jsonObject.toJSONString();
        user.setTeamName("-1");
        vc.setUteam(null);
        session.put(cookieUserNumber,user);
        session.put(cookieUserId,vc);
        return SUCCESS;
    }
    //获取队伍各成员头像
    public String getTeamMemberImg(){
        if (teamName==null||teamName.equals("-1"))
            return null;
        byte[]img=teamService.getMemberImg(teamName,member);
        if (img!=null){
            ServletOutputStream out = null;
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("multipart/form-data");
            try {
                out=response.getOutputStream();
                out.write(img);
                out.flush();
                out.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }


    //加载队伍各成员得头像
    public String memberImg(){
        byte[] temp=userService.getImg(userNumber);
        if (temp!=null){
            ServletOutputStream out = null;
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("multipart/form-data");
            try {
                out=response.getOutputStream();
                out.write(temp);
                out.flush();
                out.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }
    //队伍管理页面队长删除用户
    public String deleteOne(){
        request=ServletActionContext.getRequest();
        if (request.getCookies()!=null){
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("userNumber")) {
                    try {
                        cookieUserNumber= URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }else if (cookie.getName().equals("id")){
                    try {
                        cookieUserId= URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        JSONObject jsonObject=new JSONObject();
        ActionContext ac=ActionContext.getContext();
        Map session=ac.getSession();
        User user= (User) session.get(cookieUserNumber);
        Team team=teamService.getTeam(user.getTeamName());
        if(!team.getCaptain().equals(user.getUserNumber())){
            jsonObject.put("is","你不是队长，无权操作");
            result=jsonObject.toJSONString();
            return SUCCESS;
        }
        if (userNumber.equals("-1")){
            jsonObject.put("is","此位置无人");
            result=jsonObject.toJSONString();
            return SUCCESS;
        }
       teamService.deleteOne(userNumber,user.getTeamName());
        //更新session
        Vectors vc= (Vectors) session.get(cookieUserId);
        vc.setUteam(teamService.getTeam(user.getTeamName()));
        jsonObject.put("is","删除成功");
        result=jsonObject.toJSONString();
        return SUCCESS;
    }
    //组队申请项目
    public String teamProject(){
        request=ServletActionContext.getRequest();
        if (request.getCookies()!=null){
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("userNumber")) {
                    try {
                        cookieUserNumber= URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }else if (cookie.getName().equals("id")){
                    try {
                        cookieUserId= URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        ActionContext ac=ActionContext.getContext();
        Map session =ac.getSession();
        User user= (User) session.get(cookieUserNumber);
        JSONObject jsonObject=new JSONObject();
        if (!teamService.isCaptain(user.getUserNumber())){//不是队长，无权操作
            jsonObject.put("is","你不是队长，无法申报项目");
            result=jsonObject.toJSONString();
            return SUCCESS;
        }
        //验证项目名
        if(!projectService.isRightProName(proName)){
            jsonObject.put("is","不存在此项目");
            result=jsonObject.toJSONString();
            return SUCCESS;
        }
        //验证团队名
        if (user.getTeamName().equals("-1")||user.getTeamName()==null){
            jsonObject.put("is","你未加入团队");
            result=jsonObject.toJSONString();
            return SUCCESS;
        }

        //开始执行申请项目步骤
        //成为申请者

        //判段队伍是否已近接收项目
        if (!teamService.getTeam(user.getTeamName()).getProName().equals("-1")){
            jsonObject.put("is","你的队伍已经接收项目，无法申请另一个项目");
            result=jsonObject.toJSONString();
            return SUCCESS;
        }
        //是否是队长
        if(!teamService.getTeam(user.getTeamName()).getCaptain().equals(user.getUserNumber())){
            jsonObject.put("is","你不是队长无权操作");
            result=jsonObject.toJSONString();
            return SUCCESS;
        }
        //判断项目是否被申请或执行中
        if(projectService.isAorE(proName)){
            jsonObject.put("is","此项目已经被执行");
            result=jsonObject.toJSONString();
            return SUCCESS;
        }
        //申请项目
        teamService.joinProject(user.getTeamName(),proName);
        jsonObject.put("is","申请成功，等待发布者同意");
        result=jsonObject.toJSONString();
        return SUCCESS;
    }
    //发布队伍公告
    public String teamMessage(){
        request=ServletActionContext.getRequest();
        if (request.getCookies()!=null){
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("userNumber")) {
                    try {
                        cookieUserNumber= URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }else if (cookie.getName().equals("id")){
                    try {
                        cookieUserId= URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        ActionContext ac=ActionContext.getContext();
        Map session=ac.getSession();
        Vectors vc= (Vectors) session.get(cookieUserId);
        Team team=vc.getUteam();
        teamService.updateTeamMessage(team.getTeamName(),message);
        team.setMessage(message);
        vc.setUteam(team);
        session.put(cookieUserId,vc);
        JSONObject js=new JSONObject();
        js.put("is",message);
        result=js.toJSONString();
        return SUCCESS;
    }
}
