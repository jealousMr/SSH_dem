<%@ page import="com.model.User" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: 91308
  Date: 2018/8/5
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>team</title>
    <script src="../js/jquery.min.js"></script>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../css/teamPage.css">

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
<div class="container">
    <div class="navbar-header navbar-main-header">
        <a class="navbar-brand">
            <img src="../image/teamPage/liwu.png" class="img-responsive">
        </a>
    </div>
    <hr/>
    <hr/>
</div>

<div class="back">
    <div class="container">
        <div class="row">
            <div class="col-sm-8">
                <img src="../image/teamPage/team.png" class="img-responsive">
            </div>

        </div>
    </div>
    <div class="video">
        <div class="container">
            <div class="row">
                <div class="col-sm-3 title"><i><a href="teamToHome" style="color:#989898;text-decoration:none;">首页</a></i></div>
                <div class="col-sm-3 title"><i>团队</i></div>
                <div class="col-sm-3 title"><i id="te">项目申请</i></div>
                <button style="display: none" data-toggle="modal" data-target="#joinTeam" id="joinT"/>
            </div>
        </div>
    </div>
</div>
<script>
    var joinT=document.getElementById("joinT");
    var te=document.getElementById("te");
    te.onclick=function () {
        joinT.click();
    }
</script>
<%--hidden --%>
<div class="modal fade" id="joinTeam" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="background-color:  #ebeaea">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">申请项目（仅队长操作）：</h4>
            </div>
            <div class="modal-body">
                <div class="well">
                    <h4 style="text-align: center;color: #fcfcff;">Applying</h4>
                    <form class="ng-dirty ng-valid-parse ng-invalid ng-invalid-required ng-valid-email hero_form">
                        <div class="form-group">
                            <label>项目名</label>
                            <input  class="form-control input-lg ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched" type="text" name="proName" required="" id="pn" ng-model="lead.first_name">
                        </div>
                        <div class="form-group">
                            <label>再次输入项目名</label>
                            <input  class="form-control input-lg ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched" type="text"  required="" id="pn2" ng-model="lead.first_name">
                        </div>
                        <div class="form-group">
                            <label>团队名：</label>
                            <input class="form-control input-lg ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched" type="text"  ng-model="lead.first_name" placeholder="${user.teamName}(不可更改)">
                        </div>
                        <div class="submit">
                            <button  type="button" class="btn btn-lg" id="ap" style="background-color: #fcfcff">
                                点击申请
                                <i class="fa fa-spinner fa-pulse ng-hide"></i>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        </div>
    </div>
</div>
<script>
    var pn=document.getElementById("pn");
    var pn2=document.getElementById("pn2");
    var apply=document.getElementById("ap");
    apply.onclick=function () {
        if (pn.value.length<3){
            alert("请输入正确团队名");
        }else if(pn.value!=pn2.value){
            alert("两次项目名不一致");
        }else {
            $.ajax({
                type: "post",
                url: "view/teamProject",
                async:false,
                data:{
                    "proName":pn.value,
                },
                dataType: "json",
                success: function(data){
                    var obj = JSON.parse(data);
                    alert(obj.is);
                },
                error:function () {
                    alert("申请失败");
                }
            });
        }
    }

</script>



<div class="container">
    <div class="row">
        <div class="col-sm-6">
            <img src="../image/teamPage/join.png" class="img-responsive join">
            <hr/>
            <c:set var="list" value="${cookie.id.value}"></c:set>
            <c:forEach items="${sessionScope[list].listTeam}" var="item">
                <div class="row episode-list">
                <div class="col-sm-2">
                <div class="number">
                <div class="num">${item.id}</div>
                </div>
                </div>
                <div class="col-sm-10">
                <h3>
                <button class="btn btn-default" onclick="joinTeam2('${item.teamName}')">
                <img src="../image/teamPage/jts.png">
                    ${item.teamName}
                </button>
                </h3>
                <p>
                <img src="../image/teamPage/captain.png">
                ${item.captain}
                </p>
                <p class="lead">with</p>
                <div class="tx">
                <img src="getTeamMemberImg?member=1&teamName=${item.teamName}" class="circle toSmall" style=" max-width: 80px;">
                </div>
                <div class="tx">
                <img src="getTeamMemberImg?member=2&teamName=${item.teamName}" class="circle" style=" max-width: 80px;">
                </div>
                <div class="tx">
                <img src="getTeamMemberImg?member=3&teamName=${item.teamName}" class="circle" style=" max-width: 80px;">
                </div>
                <div class="tx">
                <img src="getTeamMemberImg?member=4&teamName=${item.teamName}" class="circle" style=" max-width: 80px;">
                </div>
                <div class="tx">
                <img src="getTeamMemberImg?member=5&teamName=${item.teamName}" class="circle" style=" max-width: 80px;">
                </div>
                </div>
                </div>
                <hr/>
            </c:forEach>
        </div>


        <div class="col-sm-6">
            <div class="well">
                <h2>队伍搜索
                <img src="../image/teamPage/ft.png">
                </h2>
                <form id="searchForm" class="form-group sign-up-form">
                    <input class="form-control input-sign-up" placeholder="Enter team name" id="searchContent">
                    <button id="searchBtn" class="button-sign-up danger" type="button">查找</button>
                    <button  id="show" class="button-sign-up danger pull-right" type="button" onclick="joinTeam()" style="display: none;">点击加入</button>
                </form>
            </div>
            <hr/>

            <script type="text/javascript">
                //链接加入队伍
                function joinTeam2(teamName) {
                    if (teamName!=null){
                        $.ajax({
                            type: "post",
                            url: "view/joinTeam",
                            async:false,
                            data:{
                                "teamName":teamName
                            },
                            dataType: "json",
                            success: function(data){
                                var obj = JSON.parse(data)
                                alert(obj.is);
                            },
                            error:function () {
                                alert("页面加载失败");
                            }
                        });
                    }
                }
            </script>
            <script type="text/javascript">
                //加入队伍
                function joinTeam() {
                    var teamName=document.getElementById("searchContent");
                    if (teamName.value!=null){
                        $.ajax({
                            type: "post",
                            url: "view/joinTeam",
                            async:false,
                            data:{
                                "teamName":teamName.value
                            },
                            dataType: "json",
                            success: function(data){
                                var obj = JSON.parse(data)
                                alert(obj.is);
                            },
                            error:function () {
                                alert("页面加载失败");
                            }
                        });
                    }
                }
            </script>
            <script type="text/javascript">
                var showBtn=document.getElementById("show");
                var inputs=document.getElementById("searchContent");
                var btns=document.getElementById("searchBtn");

                btns.onclick=function (ev) {
                    if (inputs.value==null || inputs.value==" "){
                        alert("请输入队伍名");
                    }else {
                        $(function() {
                            $.ajax({
                                type: "post",
                                url: "view/searchTeam",
                                async:false,
                                data:{
                                    "teamName":inputs.value
                                },
                                dataType: "json",
                                success: function(data){
                                    var obj = JSON.parse(data)
                                    if(obj.status=="1"){
                                        showBtn.style.display="block";
                                    }
                                    else{
                                        showBtn.style.display="none";
                                        alert("不存在此队伍");
                                    }
                                },
                                error:function () {
                                    alert("页面加载失败");
                                }
                            });
                        })
                    }
                }
            </script>
            </hr>
            <c:set var="coo" value="${cookie.userNumber.value}"></c:set>
            <div class="well">
                <h2 class="creatTeam">创建队伍</h2>
                <form class="ng-dirty ng-valid-parse ng-invalid ng-invalid-required ng-valid-email hero_form">
                    <div class="form-group">
                        <label>团队名</label>
                        <input id="tn" class="form-control input-lg ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched" type="text" name="teamName" required="" ng-model="lead.first_name">
                    </div>
                    <div class="form-group">
                        <label>再次输入团队名</label>
                        <input id="tna" class="form-control input-lg ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched" type="text"  required="" ng-model="lead.first_name">
                    </div>
                    <div class="form-group">
                        <label>队长：</label>
                        <input class="form-control input-lg ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched" type="text"  ng-model="lead.first_name" placeholder="${sessionScope[coo].userNumber}(不可更改)">
                    </div>
                    <div class="submit">
                        <button id="lead-submit" type="button" class="btn btn-lg btn-danger">
                            点击创建
                            <i class="fa fa-spinner fa-pulse ng-hide"></i>
                        </button>
                    </div>
                </form>
            </div>
            <script type="text/javascript">
                var teamName=document.getElementById("tn");
                var ateamName=document.getElementById("tna");
                var sub=document.getElementById("lead-submit");
                sub.onclick=function (ev) {
                    if (teamName.value!=ateamName.value){
                        alert("请输入一致的团队名");
                    } else if (teamName.value.length<6) {
                        alert("用户名太短");
                    }else{
                        $(function() {
                            $.ajax({
                                type: "post",
                                url: "view/createTeam",
                                async:false,
                                data:{
                                    "teamName":teamName.value
                                },
                                dataType: "json",
                                success: function(data){
                                    var obj = JSON.parse(data);
                                    alert(obj.is);
                                },
                                error:function () {
                                    alert("创建失败...");
                                }
                            });
                        });
                    }
                }
            </script>
            <div class="clip-text clip-text_one">My  TEAM
                <img src="../image/teamPage/s1.png" id="s12">
                <button style="display: none" data-toggle="modal" data-target="#manger" id="teamManger"/>
            </div>

            <script type="text/javascript">
                var s=document.getElementById("s12");
                var bt=document.getElementById("teamManger");
                s.onmousemove=function () {
                    s.src="../image/teamPage/s2.png";
                }
                s.onmouseout=function () {
                    s.src="../image/teamPage/s1.png";
                }
                s.onclick=function () {
                    bt.click();
                }



                var joinName=document.getElementById("joinName");
                $(function() {
                    $("#joinBtn").click(function () {
                        $.ajax({
                            type: "post",
                            url: "view/joinTeam",
                            async:false,
                            data:{
                                "teamName":joinName.value
                            },
                            dataType: "json",
                            success: function(data){
                                var s =JSON.parse(data);
                                alert(s.is);

                            },
                            error:function () {
                                alert("加入失败");
                            }
                        })
                    })
                });
            </script>

            <hr/>
        </div>
    </div>
</div>

<%--hidden 队伍管理--%>
<div class="modal fade" id="manger" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h1 style="text-align: center;color: #858585;"><img src="../image/teamPage/tms.png">Team Message</h1>

            </div>
            <div class="modal-body">
                <div class="toCenter">
                    <h3 style="color: #c7c7c7;">团队名：${sessionScope[list].uteam.teamName}</h3>
                    <hr>
                </div>
                <div class="toCenter">
                    <h3 style="color: #c7c7c7;">队伍项目：${sessionScope[list].tProject.proName}</h3>
                    <hr>
                </div>
                <div class="toCenter">
                    <h3 style="color: #c7c7c7;">截至时间:${sessionScope[list].tProject.deadline}</h3>
                    <hr>
                </div>
                <div class="toCenter">
                    <h3 style="color: #c7c7c7;">发布者：${sessionScope[list].tProject.publisher}</h3>
                </div>
                <hr>
                <div>
                    <input type="text" id="teamMessage" placeholder="队伍公告:${sessionScope[list].uteam.message}" style="min-height: 10%; min-width: 80%;
    border-radius: 20px;">
                    <img src="../image/teamPage/send1.png" id="sends" style="margin-left: 4%">
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary pull-left" id="outTeam">退出队伍</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">点击关闭</button>
            </div>
        </div>
    </div>
</div>
<script>
    var sendImg=document.getElementById("sends");
    var teamMessage=document.getElementById("teamMessage");
    var outTeam=document.getElementById("outTeam");
    sendImg.onmousemove=function () {
        sendImg.src="../image/teamPage/send2.png";
    }
    sendImg.onmouseout=function () {
        sendImg.src="../image/teamPage/send1.png";
    }

    sendImg.onclick=function () {
       if (teamMessage.value.length==0||teamMessage.value==null){
           alert("请输入内容");
       } else{
           $.ajax({
               type: "post",
               url: "view/teamMessage",
               async: false,
               data: {
                   "message": teamMessage.value
               },
               dataType: "json",
               success: function (data) {
                   var obj = JSON.parse(data);
                   alert("发布成功");
               },
               error: function () {
                   alert("发送失败");
               }
           });
       }
    }
    outTeam.onclick=function () {
        $.ajax({
            type: "post",
            url: "view/outTeam",
            async: false,
            data: {
                "message": "outTeam"
            },
            dataType: "json",
            success: function (data) {
                var obj = JSON.parse(data);
                alert(obj.is);
            },
            error: function () {
                alert("退出失败");
            }
        });
    }
</script>




<script type="text/javascript">
    var outBtn=document.getElementById("outBtn");
    var mes=${user.userNumber};
    if (mes.length<3){
        alert("你尚未加入队伍");
    } else{
        outBtn.onclick=function (ev) {
            $(function() {
                $.ajax({
                    type: "post",
                    url: "view/outTeam",
                    async:false,
                    data:{
                        "userNumber":${sessionScope[coo].userNumber}
                    },
                    dataType: "json",
                    success: function(data){
                        var obj = JSON.parse(data);
                        alert(obj.is);
                    },
                    error:function () {
                        alert("退出失败...");
                    }
                });
            });
        }
    }
</script>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="area">
                <div class="contents">
                    <div class="how-2 how-1">
                        How to do it
                    </div>
                    <div class="work-box text-center">
                        <div class="w1 w2 w3">
                            <div class="step">
                                <img src="../image/teamPage/where.png" id="img1">
                            </div>
                            <div class="lu">
                                <small class="img-circle">&nbsp;</small>
                            </div>
                            <span class="btn btn-danger" id="btn1" data-toggle="modal" data-target="#member">队伍成员</span>

                        </div>
                        <div class="w1 w2 w3">
                            <div class="step">
                                <img src="../image/teamPage/where2.png" id="img2">
                            </div>
                            <div class="lu">
                                <small class="img-circle" >&nbsp;</small>
                            </div>
                            <span class="btn btn-danger" id="btn2">队伍信息</span>
                        </div>
                        <div class="w1 w2 w3">
                            <div class="step">
                                <img src="../image/teamPage/where3.png" id="img3">
                            </div>
                            <div class="lu">
                                <small class="img-circle">&nbsp;</small>
                            </div>
                            <span class="btn btn-danger" id="btn3">查找队伍</span>
                        </div>

                        <div class="w1 w2 w3">
                            <div class="step">
                                <img src="../image/teamPage/where.png" id="img4">
                            </div>
                            <div class="lu">
                                <small class="img-circle">&nbsp;</small>
                            </div>
                            <span class="btn btn-danger" id="btn4">项目申请</span>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var img1=document.getElementById("img1");
    var btn1=document.getElementById("btn1");
    var img2=document.getElementById("img2");
    var btn2=document.getElementById("btn2");
    var img3=document.getElementById("img3");
    var btn3=document.getElementById("btn3");
    var img4=document.getElementById("img4");
    var btn4=document.getElementById("btn4");
    btn1.onmousemove=function () {
        img1.src="../image/teamPage/clickWhere.png";
    }
    btn1.onmouseout=function () {
        img1.src="../image/teamPage/where.png";
    }
    btn2.onmousemove=function () {
        img2.src="../image/teamPage/clickWhere.png";
    }
    btn2.onmouseout=function () {
        img2.src="../image/teamPage/where2.png";
    }
    btn3.onmousemove=function () {
        img3.src="../image/teamPage/clickWhere.png";
    }
    btn3.onmouseout=function () {
        img3.src="../image/teamPage/where3.png";
    }
    btn4.onmousemove=function () {
        img4.src="../image/teamPage/clickWhere.png";
    }
    btn4.onmouseout=function () {
        img4.src="../image/teamPage/where.png";
    }
    
    
    btn2.onclick=function () {
        $("#s12").click();
    }
    btn4.onclick=function () {
        $("#te").click();
    }
    
</script>
<%--hidden 队伍成员--%>
<div class="modal fade" id="member" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title lead">
                    <img src="../image/teamPage/tmas.png">team member</h4>
            </div>
            <div class="modal-body">
                <div class="tx" style="margin-left: 10%;">
                    <img src="getTeamMemberImg?member=1&teamName=${sessionScope[coo].teamName}" class="circle" style=" max-width: 80px;"><br>
                    <span class="btn-block" style="text-align: center;color: #858585;">
                        <c:if test="${sessionScope[list].uteam.member1!='-1'}">
                            ${sessionScope[list].uteam.member1}
                        </c:if>
                    </span><br>
                        <button class="btn btn-default" type="button" onclick="del('${sessionScope[list].uteam.member1}')">点击删除</button>
                </div>
                <div class="tx" style="margin-left: 4%;">
                    <img src="getTeamMemberImg?member=2&teamName=${sessionScope[coo].teamName}" class="circle" style=" max-width: 80px;"><br>
                    <span class="btn-block" style="text-align: center;color: #858585;">
                         <c:if test="${sessionScope[list].uteam.member2!='-1'}">
                             ${sessionScope[list].uteam.member2}
                         </c:if>
                    </span><br>
                    <button class="btn btn-default" type="button" onclick="del('${sessionScope[list].uteam.member2}')">点击删除</button>
                </div>
                <div class="tx" style="margin-left: 4%;">
                    <img src="getTeamMemberImg?member=3&teamName=${sessionScope[coo].teamName}" class="circle" style=" max-width: 80px;"><br>
                    <span class="btn-block" style="text-align: center;color: #858585;">
                       <c:if test="${sessionScope[list].uteam.member3!='-1'}">
                           ${sessionScope[list].uteam.member3}
                       </c:if>
                    </span><br>
                    <button class="btn btn-default" type="button" onclick="del('${sessionScope[list].uteam.member3}')">点击删除</button>
                </div>
                <div class="tx" style="margin-left: 4%;">
                    <img src="getTeamMemberImg?member=4&teamName=${sessionScope[coo].teamName}" class="circle" style=" max-width: 80px;"><br>
                    <span class="btn-block" style="text-align: center;color: #858585;">
                         <c:if test="${sessionScope[list].uteam.member4!='-1'}">
                             ${sessionScope[list].uteam.member4}
                         </c:if>
                    </span><br>
                    <button class="btn btn-default" type="button" onclick="del('${sessionScope[list].uteam.member4}')">点击删除</button>
                </div>
                <div class="tx" style="margin-left: 4%;">
                    <img src="getTeamMemberImg?member=5&teamName=${sessionScope[coo].teamName}" class="circle" style=" max-width: 80px;"><br>
                    <span class="btn-block" style="text-align: center;color: #858585;">
                          <c:if test="${sessionScope[list].uteam.member5!='-1'}">
                              ${sessionScope[list].uteam.member5}
                          </c:if>
                    </span><br>
                    <button class="btn btn-default" type="button" onclick="del('${sessionScope[list].uteam.member5}')">点击删除</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function del(obj) {
        var msg = "您确定要删除"+obj+"?";
        if (confirm(msg)==true){
            $.ajax({
                type: "post",
                url: "view/deleteOne",
                async:false,
                data:{
                    "userNumber":obj
                },
                dataType: "json",
                success: function(data){
                    var obj = JSON.parse(data);
                    alert(obj.is);
                },
                error:function () {
                    alert("删除失败...");
                }
            });

        }else{
            return false;
        }
    }
</script>


<div class="footer">
    <div class="container footer-container">
        <ol class="breadcrumb">
            <li>首页</li>
            <li>新闻</li>
            <li>更多资讯</li>
            <li>发现项目</li>
            <li>提示</li>
            <li>信息</li>
        </ol>
        <p>@啦啦啦制作</p>
    </div>
</div>
</body>
</html>
