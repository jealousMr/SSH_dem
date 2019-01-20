<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 91308
  Date: 2018/7/28
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>person</title>
    <script src="../js/jquery-3.3.1.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/personPage.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/bootstrap-grid.min.css">
    <link rel="stylesheet" href="../css/personMes.css">
</head>
<body>
<script>
    $(document).ready(function () {
        if(location.href.indexOf("#reloaded")==-1){
            location.href=location.href+"#reloaded";
            location.reload();
        }
    });
</script>
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
                <div class="col-sm-3 two-three">
                    <a href="homePage.jsp" style="text-decoration:none;color: #1a1a1a;">
                        <h6 class="two-title" style="font-size: 50px;">
                           首页
                            <div class="two-line"></div>
                            <div class="two-line2"></div>
                        </h6>
                    </a>
                </div>
                <div class="col-sm-3 two-three">
                    <button class="btn" style="background-color: transparent;" id="ah">
                        <h6 class="two-title" style="font-size: 50px;">
                            <i >个人中心</i>
                            <div class="two-line"></div>
                            <div class="two-line2"></div>
                        </h6>
                    </button>
                </div>
                <div class="col-sm-3 two-three">
                    <a href="enterProjectPage" style="text-decoration:none;color: #1a1a1a">
                        <h6 class="two-title" style="font-size: 50px;">
                            申请
                            <div class="two-line"></div>
                            <div class="two-line2"></div>
                        </h6>
                    </a>
                </div>
            </div>

        </div>
    </div>
</div>
<div class="three">
    <div>
        <div class="three-1">
            <img src="../image/personPage/me1.png" id="me" class="img-responsive">
        </div>
        <c:set var="coo" value="${cookie.userNumber.value}"></c:set>
        <div class="demo">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 col-sm-6">
                        <div class="box">
                            <div class="box-img">
                                <img src="../image/personPage/rt.jpg" alt="">
                            </div>
                            <div class="box-content">
                                <h4 class="title">person</h4>
                                <form>
                                    <input type="text" name="nickName" id="nickName" class="pee" placeholder="Nickname--${sessionScope[coo].nickName}">
                                    <input type="email" name="email" id="email" class="pee" placeholder="Email--${sessionScope[coo].email}">
                                    <input type="text" name="phoneNumber" id="phoneNumber" class="pee" placeholder="Phone--${sessionScope[coo].phoneNumber}">
                                    <input type="text" name="realName" id="realName" class="pee" placeholder="RealName--${sessionScope[coo].realName}"><br>
                                    <input type="button" id="subs" class="btn btn-default pee" value="提交">
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <div class="box">
                            <div class="box-img">
                                <img src="../image/personPage/mm.png" alt="">
                            </div>
                            <div class="box-content">
                                <h4 class="title">Modification</h4>
                                <p class="description">
                                    Here you can change your avatar, modify personal information, such as nicknames, mobile phone numbers, etc., so that more people can understand you.
                                </p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <div class="box">
                            <div class="box-img">
                                <img src="../image/personPage/rt.jpg" alt="">
                            </div>
                            <div class="box-content">
                                <h4 class="title">头像设置</h4>
                                <p class="description">
                                    <img class="img-responsive" src="<s:url action="getOwnImg"/>" style="max-width: 160px;max-height: 160px;margin-left: 34%;">
                                    select your img</p>
                                <s:form action="updateImg" enctype="multipart/form-data" method="POST" namespace="/view">
                                     <s:file cssStyle="display: none" name="file" id="ch1"/>
                                <button  class="btn btn-secondary" type="button" id="ch2">
                                    头像设置
                                </button>
                                <button class="btn btn-secondary" type="submit">
                                    确认上传
                                </button>
                                </s:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<hr/>
<script type="text/javascript">
    var mes=document.getElementById("me")
    mes.onmousemove=function () {
        mes.src="../image/personPage/mes2.png";
    }
    mes.onmouseout=function () {
        mes.src="../image/personPage/me1.png";
    }
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
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <h2>已接项目列表</h2>
                <p class="highlight-p">
                    &nbspTip:你已接收项目委托：
                </p>
                <ul class="highlight-ul">
                    <c:set var="pros" value="${cookie.id.value}"></c:set>
                    <c:if test="${sessionScope[coo].proName=='-1'}">
                        <li></li>
                    </c:if>
                    <c:if test="${sessionScope[coo].proName!='-1'}">
                        <li>项目名：${sessionScope[coo].proName}</li>
                        <li>发布人：${sessionScope[pros].uProject.publisher}</li>
                        <li>截至时间：${sessionScope[pros].uProject.deadline}</li>
                        <li>报酬：${sessionScope[pros].uProject.reward}$</li>
                    </c:if>
                </ul>
            </div>
            <div class="col-sm-6">
                <img src="../image/personPage/idea.jpg"/>
            </div>
        </div>
    </div>
</div>
<hr/>
<script type="text/javascript">
    var ch1=document.getElementById("ch1");
    var ch2=document.getElementById("ch2");
    ch2.onclick=function () {
        ch1.click();
    }

</script>
</body>
</html>

