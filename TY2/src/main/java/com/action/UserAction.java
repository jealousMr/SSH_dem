package com.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.model.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.CreditService;
import com.service.ProjectService;
import com.service.UserService;
import com.util.ImgChange;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//用户cookie,session规则：
// 发布者角色session：（key，value）【（"user.getUserNumber",user）,(user.getId,Some)】
// 发布者角色cookies:（key,value）["userNumber",user.getUserNumber]["userId",user.getId]

//执行者角色session(key,value)[("user.getUserNumber",user),(user.getId,vector)]
//执行者角色cookies(key,value)["userNumber",user.getUserNumber][userId,user.getId]
@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport {
    @Resource
    private UserService userService;
    @Resource
    private ProjectService projectService;
    @Resource
    private CreditService creditService;

    HttpServletResponse response;
    HttpServletRequest request;



    private User user;
    private String result;
    private String nickName;
    private String email;
    private String phoneNumber;
    private String realName;
    private File file;
    private String password2;
    private String userNumber;
    private int type;
    private String proName;
    private String password;
    private String cookieUserNumber;
    private String cookieUserId;

    public void setCookieUserId(String cookieUserId) {
        this.cookieUserId = cookieUserId;
    }

    public void setCookieUserNumber(String cookieUserNumber) {
        this.cookieUserNumber = cookieUserNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNickName() {
        return nickName;
    }

    public String getRealName() {
        return realName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    //登陆
    public String login(){
        ActionContext ac=ActionContext.getContext();
        Map session=ac.getSession();

        //比对密码
        String pass="";
        try {
            pass=userService.getUser(user.getUserNumber()).getPassword();
        }catch (NullPointerException e){
            e.printStackTrace();
            this.addFieldError("tip","Tip:用户名或密码错误");
            return INPUT;
        }

        if (pass.equals(user.getPassword())) {//比对成功
            user=userService.getUser(user.getUserNumber());
            //判断用户类型
            if (user.getType()==2){//发布者角色
                Some some=new Some();
                some.setUserList(creditService.bigCountUser(8));
                session.put(user.getUserNumber(),user);
                session.put(user.getId(),some);
                response=ServletActionContext.getResponse();
                Cookie cookie1=new Cookie("userNumber",user.getUserNumber());
                response.addCookie(cookie1);
                Cookie cookie2=new Cookie("id",String.valueOf(user.getId()));
                response.addCookie(cookie2);
                return "twoUser";
            }
            //用户角色
            Vectors vectors=new Vectors();
            vectors.setListProject(projectService.getThreeProject());
            vectors.setUserCount(creditService.getUserCount(user.getUserNumber()));
            if (user.getProName()!=null &&!user.getProName().equals("-1"))
                vectors.setuProject(projectService.getProject(user.getProName()));
            //初始化session
            session.put(user.getUserNumber(),user);
            session.put(user.getId(),vectors);
            response=ServletActionContext.getResponse();
            Cookie cookie1=new Cookie("userNumber",user.getUserNumber());
            response.addCookie(cookie1);
            Cookie cookie2=new Cookie("id",String.valueOf(user.getId()));
            response.addCookie(cookie2);
            return SUCCESS;
        }
        else{
            this.addFieldError("tip","Tip:用户名或密码错误");
            return INPUT;
        }

    }

    public String backLogin(){
        return SUCCESS;
    }

    //用户2从个人中心进入首页
    public String twoPtoH(){
        request=ServletActionContext.getRequest();
        if (request.getCookies()!=null){
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("userNumber")) {
                    try {
                        cookieUserNumber= URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        ActionContext ac=ActionContext.getContext();
        Map session =ac.getSession();
        User user= (User) session.get(cookieUserNumber);
        session.put(cookieUserNumber,user);
        return SUCCESS;
    }
    //用户1从teamPage进入主页
    public String teamToHome(){return SUCCESS;}
    //校验用户输入

    public void validateLogin(){
        if (user.getUserNumber()==null || "".equals(user.getUserNumber().trim())){
            this.addFieldError("userNumberMsg","用户名不能为空！");
        }else {
            Pattern p=Pattern.compile("\\w{6,20}");
            Matcher m=p.matcher(user.getUserNumber().trim());
            if (!m.matches()){
                this.addFieldError("userNumberMsg","Tip:用户名由下划线，字母，数字构成，长度为6-20");
            }
        }
        if (user.getPassword().trim().length()==0)
            this.addFieldError("passwordMsg","Tip:密码不能为空");
    }
    //更新个人中心用户信息表单
    @SkipValidation
    public String updatePersonMes(){
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
        //获取session中要更新的user
        Map session =ActionContext.getContext().getSession();
        User sUser= (User) session.get(cookieUserNumber);
        if(nickName!=null && !nickName.equals(""))
            sUser.setNickName(nickName);
        if (email!=null && !email.equals(""))
            sUser.setEmail(email);
        if (phoneNumber!=null && !phoneNumber.equals(""))
            sUser.setPhoneNumber(phoneNumber);
        if (realName!=null && !realName.equals(""))
            sUser.setRealName(realName);
        userService.updateUser(sUser);
        //更新完成后更新session中的user
        session.put(cookieUserNumber,sUser);
        JSONObject js=new JSONObject();
        js.put("state","1");
        setResult(js.toJSONString());
        return SUCCESS;
    }
    //更新个人中心头像
    public String updateImg() throws FileNotFoundException {
        request=ServletActionContext.getRequest();
        if (request.getCookies()!=null){
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("userNumber")) {
                    try {
                        cookieUserNumber= URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (file==null){
            this.addFieldError("file","请选择头像");
            return SUCCESS;
        }
        //判断文件类型
        FileInputStream fileInputStream=new FileInputStream(file);
        String filetype= ImgChange.getTypeByStream(fileInputStream);
        if (filetype.equals("png") || filetype.equals("jpg") ||filetype.equals("gif")){
            if (file.length()<1048576){
                Map session = ActionContext.getContext().getSession();
                User qu = (User) session.get(cookieUserNumber);//需要更新的user
                userService.updateImg(qu, file);
                //更新session
                qu.setImg(ImgChange.fileTo(file));
                session.put(cookieUserNumber,qu);
                return SUCCESS;
            }else{
                this.addFieldError("file","图片内容太大");
                return SUCCESS;
            }

        }else {
            this.addFieldError("file","请选择合适的图片类型");
            return SUCCESS;
        }
    }
    public String updateImg2() throws FileNotFoundException {
        request=ServletActionContext.getRequest();
        if (request.getCookies()!=null){
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("userNumber")) {
                    try {
                        cookieUserNumber= URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (file==null){
            this.addFieldError("file","请选择头像");
            return SUCCESS;
        }
        //判断文件类型
        FileInputStream fileInputStream=new FileInputStream(file);
        String filetype= ImgChange.getTypeByStream(fileInputStream);
        if (filetype.equals("png") || filetype.equals("jpg") ||filetype.equals("gif")){
            if (file.length()<1048576){
                Map session = ActionContext.getContext().getSession();
                User qu = (User) session.get(cookieUserNumber);//需要更新的user
                userService.updateImg(qu, file);
                //更新session
                qu.setImg(ImgChange.fileTo(file));
                session.put(cookieUserNumber,qu);
                return SUCCESS;
            }else{
                this.addFieldError("file","图片内容太大");
                return SUCCESS;
            }

        }else {
            this.addFieldError("file","请选择合适的图片类型");
            return SUCCESS;
        }
    }
    //加载页面时获取个人头像
    public String getOwnImg(){

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
        User user= (User) session.get(cookieUserNumber);
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


    //用户注册
    public String register(){
        JSONObject jsonObject=new JSONObject();
        if (userService.isUser(userNumber)){
            jsonObject.put("is","用户名已存在");
            result=jsonObject.toJSONString();
            return SUCCESS;
        }
        //设置默认属性：
        User user=new User();
        user.setUserNumber(userNumber);
        user.setPassword(password);
        user.setType(type);
        user.setEmail(email);
        user.setSex(1);
        user.setPhoneNumber("-1");
        user.setProName("-1");
        user.setTeamName("-1");
        user.setNickName("Mr.chen");
        user.setRealName("Mr.json");
        //默认头像
        try{
            URL url=UserAction.class.getResource("/def.png");
            String path = String.valueOf(url);
            path=path.substring(6);
            File file = new File(path);
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            byte[] content = new byte[fis.available()];
            fis.read(content);
            user.setImg(content);
        }catch (IOException e){
            e.printStackTrace();
        }
        userService.addUser(user);
        jsonObject.put("is","注册成功");
        result=jsonObject.toJSONString();
        return SUCCESS;
    }

    //进入个人中心
    public String enterPersonPage(){
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
        User user= (User) session.get(cookieUserNumber);

        if (!user.getProName().equals("-1")){
            Project project=projectService.getProject(user.getProName());
            vc.setuProject(project);
            session.put(cookieUserId,vc);
        }
        return SUCCESS;
    }

    //用户从searchPage进入主页,更新projectList
    public String enterHomePage(){
        ActionContext ac=ActionContext.getContext();
        Map session=ac.getSession();
        Vectors vectors=new Vectors();
        vectors.setListProject(projectService.getThreeProject());
        session.put("vectors",vectors);
        return SUCCESS;
    }
    //用户从projectPage进入serachpage页
    public String enterSearchProject(){
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
        //随机获得3-6个项目
        List<Project> list=projectService.randomPro();
        vc.setListProject(list);
        return SUCCESS;
    }
    //用户从首页搜索进入searchpage页面
    public String homeToSearchProject(){
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
        Project project=projectService.getProject(proName);
        ActionContext ac=ActionContext.getContext();
        Map session=ac.getSession();
        Vectors vc= (Vectors) session.get(cookieUserId);
        List<Project> list=new ArrayList<>();
        list.add(project);
        vc.setListProject(list);
        return SUCCESS;
    }
    //searchpage页面的模糊搜索功能
    public String searchProjectPage(){
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
        List<Project> lp=projectService.searchPros(proName);
        if (lp==null){
            vc.setListProject(null);
            return INPUT;
        }
        else {
            vc.setListProject(lp);
            return SUCCESS;
        }
    }



    //-----------------------------------
    //用户2的搜索引擎，搜索执行者
    public String searchUser(){
        List<String> res=new ArrayList<>();
        for(int i=0;i<userService.getExeUsers().size();i++){
            res.add(userService.getExeUsers().get(i).getUserNumber());
        }
        JSONArray array=JSONArray.parseArray(JSON.toJSONString(res));
        result=array.toJSONString();
        return SUCCESS;
    }
    //用户2搜索执行者结果
    public String showSearchUser(){
        JSONObject jsonObject=new JSONObject();
        User user=null;
        user=userService.getUser(userNumber);
        if (user==null){
            jsonObject.put("is","不存在此用户");
            result=jsonObject.toJSONString();
            return SUCCESS;
        }else {
            jsonObject.put("is","1");
            jsonObject.put("userNumber",user.getUserNumber());
            jsonObject.put("nickName",user.getNickName());
            jsonObject.put("email",user.getEmail());
            result=jsonObject.toJSONString();
            return SUCCESS;
        }
    }
    //用户2进入个人中心界面
    public String enterPerson(){
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
        Some some= (Some) session.get(cookieUserId);
        User user= (User) session.get(cookieUserNumber);
        List<Project> list=userService.getUProjectList(user.getUserNumber());
        some.setProjectList(list);
        session.put(cookieUserId,some);
        session.put(cookieUserNumber,user);
        return SUCCESS;
    }
    //用户2进入项目发布页
    public String enterProjectTwo(){
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
        Some some= (Some) session.get(cookieUserId);
        User user= (User) session.get(cookieUserNumber);
        some.setProjectList(userService.getUProjectList(user.getUserNumber()));
        session.put(cookieUserId,some);
        return SUCCESS;
    }
    //用户2从项目页进入首页
    public String eh(){return SUCCESS;}


    //进入注册界面
    public String enterRegister(){
        return SUCCESS;
    }

    //
}
