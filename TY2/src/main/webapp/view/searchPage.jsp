<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 91308
  Date: 2018/9/6
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>searchroject</title>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/search.css">
</head>
<body>
<div class="below-nav col-xs-12">
    <h1 class="futura-title futura-title-spaced toR">
        Find your special <b> projet .</b>
    </h1>
    <div class="row"></div>
    <form id="forms" action="searchProjectPage" method="post">
        <div class="col-xs-1 col-xs-offset-0 col-sm-1 col-sm-offset-1 col-md-1 col-md-offset-1">
            <img class="fa fa-search dark" src="../image/searchPage/search.png">
        </div>
        <input type="text" name="proName" placeholder="输入项目名" class="search-form-control col-xs-11 col-xs-offset-0 col-sm-9 col-sm-offset-0 col-md-8 col-md-offset-0" id="finds">
        <br>
        <input type="button"  value="Find" class="button-view button-view-small button-view-filled" id="find">
    </form>
    <script>
        var find=document.getElementById("find");
        find.onmousemove=function(){
            find.style.textShadow=" 1px 1px 1px green";
        }
        find.onmouseout=function(){
            find.style.textShadow="";
        }
        find.onclick=function (ev) {
            if(finds.value.length<1||finds.value.length>20){
                alert("请输入正确的项目名");
            }else{
                $("#forms").submit()
            }
        }
    </script>

    <div class="row"></div>
    <div class="bigr"></div>
    <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2">
        <!--		循环-->
        <c:set var="list" value="${cookie.id.value}"></c:set>
        <c:forEach var="item" items="${sessionScope[list].listProject}">
            <div class="col-xs-12 col-sm-6 col-md-4">
                <a>
                    <div class="col-xs-10 col-xs-offset-1 bg-white supercool-shadow">
                        <div class="row">
                            <img class="profile-pic navbar-pic" src="getPui?proName=${item.proName}">
                        </div>
                        <div class="row">
                            <hr>
                            <h5 class="futura-title futura-title-spaced somes">${item.proName}</h5>
                            <hr>
                        </div>
                        <div class="row">
                            <div class="r1 cool-shadow" style="margin-left: 20%;">
                                <div class="im">
                                    <img src="../image/searchPage/tt.png" id="she" class="img-responsive" onmousemove="shemover(this)" onmouseout="sheout(this)">
                                </div>
                            </div>
                            <div class="r1 cool-shadow">
                                <div class="im">
                                    <img src="../image/searchPage/re.png" class="img-responsive" onclick="join('${item.proName}')" onmouseout="joinout(this)" onmousemove="joinmove(this)">
                                    <button style="display: none" data-toggle="modal" data-target="#apply" id="joinShow"/>
                                </div>
                            </div>
                            <div class="r1 cool-shadow ">
                                <div class="im">
                                    <img src="/image/searchPage/know.png" onmousemove="knowmove(this)"
                                         onclick="knowclick('${item.proName}','${item.publisher}','${item.reward}','${item.sendTime}','${item.deadline}','${item.type}')" onmouseout="knowout(this)"   class="img-responsive">
                                    <button style="display:none" data-toggle="modal" data-target="#myModal" id="showKnow"/>
                                </div>
                            </div>
                            <div class="r1 cool-shadow">
                                <div class="im">
                                    <a href="enterProjectPage">
                                        <img src="../image/searchPage/back.png" onmouseout="backout(this)" onmousemove="backmove(this)" class="img-responsive">
                                    </a>
                                </div>
                            </div>
                        </div>
                        <button class="btn btn-default futura-title futura-title-spaced bt" type="button">project</button>
                    </div>
                </a>
            </div>
        </c:forEach>
        <script>
            function shemover(obj){
                obj.src="../image/searchPage/tt2.png";
            }
            function sheout(obj){
                obj.src="../image/searchPage/tt.png";
            }
            function joinmove(obj){
                obj.src="../image/searchPage/re2.png";
            }
           function joinout(obj){
                obj.src="../image/searchPage/re.png";
            }
            function backmove(obj){
                obj.src="../image/searchPage/back2.png";
            }
            function backout(obj){
                obj.src="../image/searchPage/back.png";
            }
            function knowmove(obj){
                obj.src="../image/searchPage/know2.png";
            }
            function knowout(obj){
                obj.src="../image/searchPage/know.png";
            }
            function knowclick(proName,publisher,reward,sendTime,deadLine,type) {

                var show=document.getElementById("showKnow");
                var men=document.getElementById("men");
                var pn=document.getElementById("pn");
                var st=document.getElementById("st");
                var dt=document.getElementById("dt");
                var re=document.getElementById("reward");
                var status=document.getElementById("status");
                men.innerHTML="发布者："+publisher;
                pn.innerHTML="项目名:"+proName;
                st.innerHTML="发布时间:"+sendTime;
                dt.innerHTML="截至时间:"+deadLine;
                re.innerHTML="报酬:"+reward+"￥";
                if (type=='-1'){
                    status.innerHTML="<button class='btn btn-default' type='button'>项目状态：已被执行</button>";
                } else{
                    status.innerHTML="<button class='btn btn-default' type='button'>项目状态：未执行</button>";
                }
                show.click();
            }

            function join(proName) {
                var showKnow=document.getElementById("joinShow");
                var projectName=document.getElementById("projectName");
                projectName.placeholder=proName;
                showKnow.click();
            }

        </script>
    </div>
</div>
<%--hidden --%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <img src="../image/searchPage/mi.png">
            </div>
            <div class="modal-body">
                <h4 class="modal-title" id="men">发布者：</h4>
                <h6 class="futura-title futura-title-spaced" id="pn"></h6>
                <h6 class="futura-title futura-title-spaced" id="st"></h6>
                <h6 class="futura-title futura-title-spaced" id="dt"></h6>
                <h6 class="futura-title futura-title-spaced" id="reward"></h6>
                <h6 class="futura-title futura-title-spaced" id="status"></h6>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<%--hidden 项目报名--%>
<div class="modal fade" id="apply" tabindex="-1" role="dialog" aria-labelledby="apply" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"><img src="../image/searchPage/appls.png">项目报名</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>Project Name</label>
                    <input class="form-control input-lg ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched" type="text" id="projectName" required="" ng-model="lead.first_name">
                </div>
                <div class="form-group">
                    <label>Your Name</label>
                    <input class="form-control input-lg ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched" type="text" name="firstName" placeholder="${user.userNumber}" ng-model="lead.first_name">
                </div>
                <div class="form-group">
                    <label>Phone</label>
                    <input class="form-control input-lg ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched" type="text" id="phone" ng-model="lead.first_name">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="go">报名</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<%--项目报名--%>
<script type="text/javascript">
    var go=document.getElementById("go");
    var proName=document.getElementById("projectName");
    var phone=document.getElementById("phone");
    go.onclick=function (ev) {
        if(phone.value.length<10)
            alert("请输入正确的号码");
        else{
            $.ajax({
                type: "post",
                url: "view/joinProject",
                async: false,
                data: {
                    "proName": proName.placeholder,
                    "phone": phone.value
                },
                dataType: "json",
                success: function (data) {
                    var obj = JSON.parse(data);
                    if (obj.is==10){
                        alert("报名成功");
                    } else{
                        alert(obj.is);
                    }

                },
                error: function () {
                    alert("页面加载失败");
                }
            });
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
        <p class="pt">@啦啦啦制作</p>
    </div>
</div>
</body>
</html>
