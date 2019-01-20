package com.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.model.Credit;
import com.model.Project;
import com.model.User;
import com.model.Vectors;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.CreditService;
import com.service.ProjectService;
import com.service.UserService;
import com.util.ImgChange;
import org.apache.struts2.ServletActionContext;
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
import java.util.*;

@Controller("projectAction")
@Scope("prototype")
public class ProjectAction extends ActionSupport {

    @Resource
    private ProjectService projectService;
    @Resource
    private UserService userService;
    @Resource
    private CreditService creditService;

    HttpServletResponse response;
    HttpServletRequest request;

    private String cookieUserNumber;
    private String cookieUserId;

    private String result;//返回的json数据

    private int typeName;//返回项目类型

    private String proName;

    private String phone;

    private String proContent;

    private int reward;

    private String deadline;

    private int type;

    private File imgs;

    private String sign;

    private String userNumber;


    public void setCookieUserId(String cookieUserId) {
        this.cookieUserId = cookieUserId;
    }

    public void setCookieUserNumber(String cookieUserNumber) {
        this.cookieUserNumber = cookieUserNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public File getImgs() {
        return imgs;
    }


    public void setImgs(File imgs) {
        this.imgs = imgs;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public String getProContent() {
        return proContent;
    }

    public void setProContent(String proContent) {
        this.proContent = proContent;
    }

    public String getPhone() {
        return phone;
    }

    public String getProName() {
        return proName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public void setTypeName(int typeName) {
        this.typeName = typeName;
    }

    public int getTypeName() {
        return typeName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    //首页搜索项目引擎
    public String searchProject(){
        List<Project> list=projectService.getAllProject();
        List<String> names=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            names.add(list.get(i).getProName());
        }
        JSONArray array=JSONArray.parseArray(JSON.toJSONString(names));
        result=array.toJSONString();
        return SUCCESS;
    }
    //进入项目页，刷新session
    public String enterProjectPage(){
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
        List<Project> projects=projectService.getAllProject();
        if (projects.size()<5)
            vc.setListProject(projects);
        else {
            int a1=new Random().nextInt(projects.size());
            int a2=new Random().nextInt(projects.size());
            int a3=new Random().nextInt(projects.size());
            int a4=new Random().nextInt(projects.size());
            int a5=new Random().nextInt(projects.size());
            List<Project> ts=new ArrayList<>();
            ts.add(projects.get(a1));
            ts.add(projects.get(a2));
            ts.add(projects.get(a3));
            ts.add(projects.get(a4));
            ts.add(projects.get(a5));
            vc.setListProject(ts);
        }
        session.put(cookieUserId,vc);
        return SUCCESS;
    }
    //项目分类，根据类别获得项目
    public String typeProject(){
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
        if (typeName==4){
            List<Project> pc=projectService.getAllProject();
            vc.setListProject(pc);
            session.put(cookieUserId,vc);
            return SUCCESS;
        }else{
            List<Project> pc=projectService.projectTypes(typeName);
            vc.setListProject(pc);
            session.put(cookieUserId,vc);
            return SUCCESS;
        }
    }
    //用户在首页翻页
    public String tonext(){
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
        vc.setListProject(projectService.getThreeProject());
        session.put(cookieUserId,vc);
        return SUCCESS;
    }
    //用户通过项目名获得发布者的头像
    public String getPui(){
        byte[] img=projectService.getImgByProName(proName);
        if (img==null)
            return null;
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
        return null;
    }
    //用户在project页获得用户2的头像
    public String getUserTwoImg(){
        byte[] img=userService.getImg(userNumber);
        if (img==null)
            return null;
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
        return null;
    }
    //用户申请项目
    public String joinProject(){
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
        JSONObject json=new JSONObject();
        //判断用户是否已经申请项目,或已近是项目申请人
        String userNumber=user.getUserNumber();
        if (userService.isJoinProject(userNumber)||userService.isInApplicant(userNumber)){
            json.put("is","你已经申请项目");
            result=json.toJSONString();
            return SUCCESS;
        }
        //判断项目名是否存在
        if (!projectService.isRightProName(proName)){
            json.put("is","不存在此项目");
            result=json.toJSONString();
            return SUCCESS;
        }
        //判断此项目是否有申请者或者执行者
        if (projectService.isAorE(proName)){
            json.put("is","此项目已经被执行");
            result=json.toJSONString();
            return SUCCESS;
        }
        //比对电话号码
        String uP=userService.getPhone(user.getUserNumber());
        if (uP==null){
            json.put("is","请先到个人中心完善个人信息后可申请");
            result=json.toJSONString();
            return SUCCESS;
        }else if(!uP.equals(phone)){
            json.put("is","请输入正确的联系方式");
            result=json.toJSONString();
            return SUCCESS;
        }
        //成为申请者
        Project project=projectService.getProject(proName);
        userService.beApplicant(user.getUserNumber(),proName);
        json.put("is","10");
        json.put("proName",project.getProName());
        json.put("proContent",project.getProContent());
        json.put("publisher",project.getPublisher());
        json.put("reward",project.getReward());
        json.put("sendTime",project.getSendTime());
        json.put("deadline",project.getDeadline());
        result=json.toJSONString();
        //更新session
        user.setProName(proName);
        session.put(cookieUserNumber,user);
        return SUCCESS;
    }

    //用户二发布项目
    public String addProject(){
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
        //同时伴随创建Credit表字段
        ActionContext ac=ActionContext.getContext();
        Map session=ac.getSession();
        User user= (User) session.get(cookieUserNumber);
        JSONObject jsonObject=new JSONObject();
        if(projectService.isInProject(proName)){
            jsonObject.put("is","已存在此项目，请从新输入项目名");
            result=jsonObject.toJSONString();
            return SUCCESS;
        }


        Calendar calendar=Calendar.getInstance();
        String sendTime=calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE);
        Project project=new Project();
        project.setProName(proName);
        project.setProContent(proContent);
        project.setDeadline(deadline);
        project.setType(type);
        project.setPublisher(user.getUserNumber());
        project.setSendTime(sendTime);
        project.setApplicant("-1");
        project.setExecutor("-1");
        project.setIsTeam(1);
        project.setReward(Integer.toString(reward));
        //设置项目默认头像
        try{
            URL url=UserAction.class.getResource("/prodef.jpg");
            String path = String.valueOf(url);
            path=path.substring(6);
            File file = new File(path);
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            byte[] content = new byte[fis.available()];
            fis.read(content);
            project.setImg(content);
        }catch (IOException e){
            e.printStackTrace();
        }
        projectService.addProject(project);
        //为项目添加Credit对象
        Credit credit=new Credit();
        credit.setProName(project.getProName());
        credit.setPublisher(project.getPublisher());
        credit.setUserNumber("-1");
        credit.setCount(1);
        credit.setMessage("-1");
        creditService.addCredit(credit);
        jsonObject.put("is","发布成功");
        result=jsonObject.toJSONString();
        return SUCCESS;
    }
    //获取peoject图片
    public String getProImg(){
        byte[] img=projectService.getProImg(proName);
        ServletOutputStream out = null;
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("multipart/form-data");
        if (img!=null){
            try {
                out=response.getOutputStream();
                out.write(img);
                out.flush();
                out.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            try {
                URL url = UserAction.class.getResource("/prodef.jpg");
                String path = String.valueOf(url);
                path = path.substring(6);
                File file = new File(path);
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                byte[] content = new byte[fis.available()];
                fis.read(content);
                out=response.getOutputStream();
                out.write(content);
                out.flush();
                out.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }
    //用户2project页project详情信息
    public String getMoreProject(){
        Project project=projectService.getProject(proName);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("proName",project.getProName());
        jsonObject.put("proContent",project.getProContent());
        jsonObject.put("reward",project.getReward());
        jsonObject.put("executor",project.getExecutor());
        jsonObject.put("deadline",project.getDeadline());
        jsonObject.put("sendTime",project.getSendTime());
        jsonObject.put("applicant",project.getApplicant());
        jsonObject.put("publisher",project.getPublisher());
        result=jsonObject.toJSONString();
        return SUCCESS;
    }
    //用户2进行操作：applicant-》executor
    public String becomeExecutor(){
      Project p=projectService.getProject(proName);
      //修改Credit对象的执行者对象
        creditService.updateUserNumber(proName,p.getApplicant());
        projectService.changeAtoE(proName);
      JSONObject j=new JSONObject();
      j.put("is","1");
      result=j.toJSONString();
      return SUCCESS;
    }
    //用户2取消申请人资格
    public String cancelApplicant(){
        //判断是否成为执行者
        Project p=projectService.getProject(proName);
        if (p.getExecutor().equals("-1"))
            projectService.cancelApplicant(proName);
        JSONObject j=new JSONObject();
        j.put("is","1");
        result=j.toJSONString();
        return SUCCESS;
    }
    //用户2上传project图片,修改p'ro'C'o'ntent
    public String projectImg() throws FileNotFoundException {
       //处理图片
        if (imgs!=null){
            FileInputStream fileInputStream=new FileInputStream(imgs);
            String filetype= ImgChange.getTypeByStream(fileInputStream);
            if (filetype.equals("png") || filetype.equals("jpg") ||filetype.equals("gif")){
                if (imgs.length()<2048576){
                        projectService.updateProImg(sign,imgs);
                }
            }
        }
        //更新proContent
        if(proContent!=null)
            projectService.updateProContent(sign,proContent);
        return SUCCESS;
    }

    //用户2结算项目
    public String endProject() {
        JSONObject jsonObject = new JSONObject();
        //判断executor是否是-1
        if (!projectService.hasExecutor(proName)) {
            jsonObject.put("is", "你的项目不存在执行人员");
            result = jsonObject.toJSONString();
            return SUCCESS;
        }
        //判断项目是团队还是个人进行
        Project p=projectService.getProject(proName);
        if (p.getIsTeam()==1){//个人
            projectService.endProject(proName);
        }else{//团队
            projectService.endTeamProject(proName);
        }
        jsonObject.put("is", "1");
        result = jsonObject.toJSONString();
        return SUCCESS;
    }
    //项目类别1234
    public String proType(){
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
        Vectors vectors= (Vectors) session.get(cookieUserId);
        vectors.setListProject(projectService.getProjectByType(type));
        session.put(cookieUserId,vectors);
        return SUCCESS;
    }
}
