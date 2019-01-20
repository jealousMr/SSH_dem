<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%--
  Created by IntelliJ IDEA.
  User: 91308
  Date: 2018/8/22
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人中心</title>
    <script src="../../js/jquery-3.3.1.js"></script>
    <script src="../../js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/personPage.css">
    <link rel="stylesheet" href="../../css/pform.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css">
</head>
<body>

<div class="mo">
    <header class="main-header">
        <div class="container">
            <div class="row section-title">
                <div class="col-md-12">
                    ...
                </div>
            </div>
            <form role="search" action="" class="row search-form">
                <div class="col-md-8 col-md-push-2">
                </div>
            </form>
        </div>
    </header>
</div>
<div class="two-1">
    <div class="two-2">

        <div class="container">
            <div class="row">
                    <div class="col-sm-12">
                        <table width=100% height=auto text-align=center>
                            <tr>
                                <td>
                                    <a href="twoPtoH">
                                        <img src="../../image/homePage/sh2.png" id="s-1" width="90px" height="90px" style="margin-left: 10%">
                                    </a>
                                </td>
                                <td>
                                    <img src="../../image/homePage/g1.png" id="s-3" width="90px" height="90px" style="margin-left: 40%"title="个人中心"
                                         data-container="body" data-toggle="popover" data-placement="bottom"
                                         data-content="通过下面内容修改个人信息">
                                </td>
                                <td>
                                    <a href="enterProjectTwo">
                                    <img src="../../image/homePage/f1.png" id="s-2" width="90px" height="90px" style="margin-left: 50%">
                                    </a>
                                </td>
                            </tr>
                        </table>
                        <script>
                            $(function () {
                                $("[data-toggle='popover']").popover();
                            });
                            var shou=document.getElementById("s-1");
                            var xiang=document.getElementById("s-2");
                            var nv=document.getElementById("s-3");
                            nv.onmousemove=function(){
                                nv.src="../../image/homePage/g2.png";
                            }
                            nv.onmouseout=function(){
                                nv.src="../../image/homePage/g1.png";
                            }
                            shou.onmousemove=function () {
                                shou.src="../../image/homePage/sh1.png"
                            }
                            shou.onmouseout=function () {
                                shou.src="../../image/homePage/sh2.png"
                            }
                            xiang.onmousemove=function () {
                                xiang.src="../../image/homePage/f2.png";
                            }
                            xiang.onmouseout=function () {
                                xiang.src="../../image/homePage/f1.png";
                            }
                        </script>
                    </div>
            </div>

        </div>
    </div>
</div>
<div class="three">
        <div class="container">
                <c:set var="coo" value="${cookie.userNumber.value}"/>
                <div class="row">
                    <div class="col-lg-6">
                        <div class="three-1" style="padding-left:35%">
                            <img src="../../image/personPage/mes2.png" id="me" class="img-responsive">
                        </div>
                        <div class="person-div">
                            <form>
                                <p>
                                    <label >NickName<br>
                                        <input type="text" id="nickName"  class="input"  size="20" placeholder="${sessionScope[coo].nickName}">
                                    </label>
                                </p>
                                <p>
                                    <label >Email<br>
                                        <input type="text" id="email"  class="input"  size="20" placeholder="${sessionScope[coo].email}">
                                    </label>
                                </p>
                                <p>
                                    <label>Phone<br>
                                        <input type="text" id="phoneNumber"  class="input"  size="20" placeholder="${sessionScope[coo].phoneNumber}">
                                    </label>
                                </p>
                                <p>
                                    <label>RealName<br>
                                        <input type="text" id="realName" class="input"  size="20" placeholder="${sessionScope[coo].realName}">
                                    </label>
                                </p>
                                <p class="submit">
                                    <input type="submit" id="subs" class="btn btn-info pull-right">
                                </p>
                            </form>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="three-1" style="padding-left:28%">
                            <img src="../../image/personPage/mes2.png"  class="img-responsive">
                        </div>
                        <div class="person-div">
                            <s:form action="updateImg2" enctype="multipart/form-data" method="POST" namespace="/view">
                                <img class="img-responsive" src="<s:url action="getOwnImg"/>" style="max-width: 160px;max-height: 160px;margin-left: 26%">
                                <h2>Modify personal portrait here</h2>
                                <p class="highlight-p">Choose the right picture and click upload</p>
                                <s:file cssStyle="display: none" name="file" id="ch1"/>
                                <button type="button" data-track="" data-track-location="open-source" href="/" class="btn btn-secondary pull-left" id="ch2">
                                    头像设置
                                </button>
                                <s:submit value="确认上传" cssClass="btn btn-secondary pull-right"/>
                                <s:fielderror/>
                            </s:form>
                        </div>
                    </div>

                </div>
        </div>
        <div class="mi"></div>
</div>
<script type="text/javascript">
    var nickName=document.getElementById("nickName");
    var email=document.getElementById("email");
    var phoneNumber=document.getElementById("phoneNumber");
    var realName=document.getElementById("realName");
    $(function() {
        $("#subs").click(function () {
            $.ajax({
                type: "post",
                url: "view/updatePersonMes",
                async:false,
                data:{
                    "nickName":nickName.value,
                    "email":email.value,
                    "phoneNumber":phoneNumber.value,
                    "realName":realName.value
                },
                dataType: "json",
                success: function(data){
                    alert("Tip:更新成功");
                },
                error:function () {
                    alert("更新失败");
                }
            });
        })
    })
</script>
<hr/>

<hr/>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <h2>已发布项目列表</h2>
                <p class="highlight-p">
                    &nbspTip:你当前正在处理的项目：
                </p>
                <ul class="highlight-ul">
                    <c:set var="coos" value="${cookie.id.value}"/>
                    <c:set value="${sessionScope[coos].projectList}" var="list"/>
                    <c:if test="${list==null || fn:length(list)==0}">
                        <li></li>
                    </c:if>
                    <c:if test="${fn:length(list)!=0}">
                        <c:forEach items="${list}" var="item">
                            <li>项目名：${item.proName}<br/>发布时间：${item.sendTime}</li>
                            <li style="list-style-image:url('../../image/personPage/stauts.png');">项目状态：
                                <c:if test="${item.type==-1}">
                                    已完成
                                    <button class="btn btn-default pull-right" type="button" onclick="comment('${item.proName}')"
                                            data-toggle="modal" data-target="#myModal">
                                    <img src="../../image/personPage/say.png">评论
                                    </button>
                                </c:if>
                                <c:if test="${item.type!=-1}">
                                    <button class="btn btn-default" title="Tip"
                                    data-container="body" data-toggle="popover" data-placement="right"
                                    data-content="项目结算后可进评论">未完成</button>
                                </c:if>
                            </li>
                            <hr>
                        </c:forEach>
                    </c:if>
                </ul>
            </div>
            <div class="col-sm-6">
                <img src="../../image/personPage/idea.jpg"/>
            </div>
        </div>
    </div>
</div>
<hr/>
<!-- 评论区域 -->
<link rel="stylesheet" href="../../css/commenPage.css">
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="background-color:transparent;">
            <div class="modal-body">
                    <div class="col-xs-12">
                        <div class="panel-lite">
                            <div class="thumbur">
                                <img src="" id="now-img" class="img-responsive" style="margin-left: 14%;max-width: 100px;">
                            </div>
                           <h4 id="now-executor"></h4>
                            <div class="form-group">
                                <input required="required" class="form-control" >
                                <label class="form-label" id="now-proName">当前项目--</label>
                            </div>
                            <div class="form-group">
                                <input required="required" class="form-control" id="message">
                                <label class="form-label">项目评论(点击输入)    </label>
                            </div>
                            <div class="ping">
                                <img src="../../image/commentPage/good2.png" id="god">
                                <input type="checkbox" id="check1" class="regular-checkbox" /><img src="../../image/commentPage/xin.png" class="xins" id="xin1">
                                <input type="checkbox" id="check2" class="regular-checkbox" /><img src="../../image/commentPage/xin.png" class="xins" id="xin2">
                                <input type="checkbox" id="check3" class="regular-checkbox" /><img src="../../image/commentPage/xin.png" class="xins" id="xin3">
                                <input type="checkbox" id="check4" class="regular-checkbox" /><img src="../../image/commentPage/xin.png" class="xins" id="xin4">
                                <input type="checkbox" id="check5" class="regular-checkbox" /><img src="../../image/commentPage/xin.png" class="xins" id="xin5">
                            </div>
                            <button class="floating-btn" type="button" id="blo" onclick="tosubmit()">
                                <img src="../../image/commentPage/tj1.png" id="sub-n">
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var ch1=document.getElementById("ch1");
    var ch2=document.getElementById("ch2");
    var pro;

    ch2.onclick=function () {
        ch1.click();
    }
    function comment(proName) {
        pro=proName;
        var img_tou=document.getElementById("now-img");
        var pro_now=document.getElementById("now-proName");
        var now_executor=document.getElementById("now-executor");
        $.ajax({
            type: "post",
            url: "view/enterCommentPage",
            async:false,
            data:{
                "proName":proName
            },
            dataType: "json",
            success: function(data){
                var pro=JSON.parse(data);
                img_tou.src="getTu?userNumber="+pro.executor;
                pro_now.innerHTML="当前项目--"+pro.proName;
                now_executor.innerHTML=pro.executor;
            },
            error:function () {
                alert("页面加载失败");
            }
        });
    }
    function tosubmit() {
        var mes=document.getElementById("message");
        var temp=0;
        if ($("#check1").prop("checked")){
            temp++;
        }
        if ($("#check2").prop("checked")){
            temp++;
        }
        if ($("#check3").prop("checked")){
            temp++;
        }
        if ($("#check4").prop("checked")){
            temp++;
        }
        if ($("#check5").prop("checked")){
            temp++;
        }
        if (temp==0)
            temp=1;
        if (mes.value==null||mes.value==""){
            alert("请输入评价内容");
        }else if (pro!=null) {
            $.ajax({
                type: "post",
                url: "view/commentMes",
                async:false,
                data:{
                    "proName":pro,
                    "message":mes.value,
                    "count":temp
                },
                dataType: "json",
                success: function(data){
                    alert("Tip:评论成功");
                },
                error:function () {
                    alert("评论失败");
                }
            });
        }
    }
    var xin1=document.getElementById("xin1");
    var check1=document.getElementById("check1");
    var xin2=document.getElementById("xin2");
    var check2=document.getElementById("check2");
    var xin3=document.getElementById("xin3");
    var check3=document.getElementById("check3");
    var xin4=document.getElementById("xin4");
    var check4=document.getElementById("check4");
    var xin5=document.getElementById("xin5");
    var check5=document.getElementById("check5");
    var god=document.getElementById("god");
    xin1.onclick=function(){
        if(!check1.checked){
            xin1.src="../../image/commentPage/xin2.png";
        }else{
            xin1.src="../../image/commentPage/xin.png";
        }
        check1.click();
    }
    xin2.onclick=function(){
        if(!check2.checked){
            xin2.src="../../image/commentPage/xin2.png";
        }else{
            xin2.src="../../image/commentPage/xin.png";
        }
        check2.click();
    }
    xin3.onclick=function(){
        if(!check3.checked){
            xin3.src="../../image/commentPage/xin2.png";
        }else{
            xin3.src="../../image/commentPage/xin.png";
        }
        check3.click();
    }
    xin4.onclick=function(){
        if(!check4.checked){
            xin4.src="../../image/commentPage/xin2.png";
        }else{
            xin4.src="../../image/commentPage/xin.png";
        }
        check4.click();
    }
    xin5.onclick=function(){
        if(!check5.checked){
            xin5.src="../../image/commentPage/xin2.png";
        }else{
            xin5.src="../../image/commentPage/xin.png";
        }
        check5.click();
    }
    var sub=document.getElementById("sub-n");

    sub.onmousemove=function(){
        sub.src="../../image/commentPage/tj2.png";
    }
    sub.onmouseout=function(){
        sub.src="../../image/commentPage/tj1.png";
    }

</script>
</body>
</html>
