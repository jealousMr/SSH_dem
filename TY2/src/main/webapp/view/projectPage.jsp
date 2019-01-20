<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 91308
  Date: 2018/8/9
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>project</title>
    <script src="../js/jquery-3.3.1.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/projectPage.css">
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
<header class="header">
    <div class="navbar navbar-default main-menu">
        <div class="container">
            <div class="row">
                <div class="navbar-collapse">
                    <ul class="nav navbar-nav navbar-left">
                        <li class="first-page">
                            <a href="enterHomePage"><img src="../image/projectPage/toHome.png" style="margin: 2px;">首页</a>
                        </li>
                        <li class="separator"></li>
                        <li class="first-page">
                            <a href="enterPersonPage"><img src="../image/projectPage/toperson.png" style="margin: 2px;">个人中心</a>
                        </li>
                        <li class="first-page">
                            <a href="#"><img src="../image/projectPage/topro.png" style="margin: 2px;">项目</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="jumbotron">
            <div class="container">
                <div class="clearfix">
                    <div class="main-tu">
                        <div class="col-lg-10 back">
                            <div class="container">
                                <div class="row">
                                    <div class="col-sm-8">
									<span class="P-1">
										P
									</span>
                                        <span class="P-2">
										r
									</span>
                                        <span class="P-3">
										o
									</span>
                                        <span class="P-4">
										J
									</span>
                                        <span class="P-5">
										e
									</span>
                                        <span class="P-6">
										c
									</span>
                                        <span class="P-7">
										t
									</span>
                                    </div>
                                    <div class="col-sm-4">
                                        <img src="../image/projectPage/idea.png" class="img-responsive">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>

<div class="container">
    <h3 class="type-title">
        Classification
        <span style="color: #fd5c63">of project types</span>
    </h3>
</div>
<div class="container-fluid homePage">
    <div class="container">
        <div class="row">
            <div class="col-sm-6 col-left to-top">
                <div class="img-round2">
                    <span class="helper"></span>
                </div>
                <h3>美工</h3>
                <p>Click here to find items of art type, such as fine arts, retouching and so on.Find suitable projects and get higher evaluations. </p>
                <a href="typeProject?typeName=1">
                    <button class="btn btn-green const">Read more</button>
                </a>
            </div>

            <div class="col-sm-6 col-left to-top">
                <div class="img-round3">
                    <span class="helper"></span>

                </div>
                <h3>家教</h3>
                <p>Click here for more information on tutoring programs. Find suitable projects and get higher evaluations.  </p>
                <a href="typeProject?typeName=2">
                    <button class="btn btn-green const">Read more</button>
                </a>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6 col-left to-top">
                <div class="img-round4">
                    <span class="helper"></span>

                </div>
                <h3>科学</h3>
                <p>Click here for more scientific projects, such as mathematical calculations, software projects, etc. </p>
                <a href="typeProject?typeName=3">
                    <button class="btn btn-green const">Read more</button>
                </a>
            </div>
            <div class="col-sm-6 col-left to-top">
                <div class="img-round5">
                    <span class="helper"></span>

                </div>
                <h3>更多</h3>
                <p>Click here for more projects and find a project that belongs to you. Find suitable projects and get higher evaluations. </p>
                <a href="enterSearchProject">
                    <button class="btn btn-green const">Find more</button>
                </a>
            </div>
        </div>

    </div>
</div>
<div class="temp">
    <hr/>
</div>
<c:set var="list" value="${cookie.id.value}"></c:set>
<div class="container">
    <div class="row">
        <c:forEach items="${sessionScope[list].listProject}" var="item">
            <div class="col-lg-6 ml-auto ">
            <div class="md-4">
            <div class="position position--genuine featured-sign position--featured">
            <div class="btn btn-xs btn-danger" style="position: absolute; top: -10px; pointer-events: none;">
                <c:if test="${item.id<10}">
                    New!
                </c:if>
                <c:if test="${item.id>=10}">
                    Odd!
                </c:if>
            </div>
            <div class="flax">
            <img src="getUserTwoImg?userNumber=${item.publisher}" style="max-width: 100px;max-height: 100px">
            <h4>${item.proName}</h4><br>
            <span class="persons">发布者:${item.publisher}</span>
            <span class="persons">发布时间:${item.sendTime}</span>
            <span class="persons">截至时间:${item.deadline}</span>
            </div>
            </div>
            </div>
            </div>
        </c:forEach>
    </div>
</div>

<div class="container toB">
    <div class="row">
        <div class="col-lg-12 text-center">
            <h2>项目申请</h2>
            <hr class="star-primary">
        </div>
    </div>
    <div class="row">
        <p class="p1" style="text-align: center; color: #c7c7c7;">
            Higher evaluation can be obtained through application projects
        </p>
    </div>
    <div class="row">
        <form class="ng-dirty ng-valid-parse ng-invalid ng-invalid-required ng-valid-email hero_form">
            <div class="form-group">
                <label>Project Name</label>
                <input class="form-control input-lg ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched" type="text" id="projects" required="" ng-model="lead.first_name">
            </div>
            <div class="form-group">
                <label>Your Name</label>
                <input class="form-control input-lg ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched" type="text" name="firstName" placeholder="${user.userNumber}" ng-model="lead.first_name">
            </div>
            <div class="form-group">
                <label>Phone</label>
                <input class="form-control input-lg ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched" type="text" id="phone"  ng-model="lead.first_name">
            </div>
            <div class="submit">
                <button id="lead-submit" type="button" class="btn btn-lg btn-danger pull-right">
                    点击申请
                </button>
                <a href="enterSearchProject">
                <button  type="button" class="btn btn-lg btn-danger pull-left">
                    了解更多
                </button>
                </a>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    var btn=document.getElementById("lead-submit");
    var pro=document.getElementById("projects");
    var phone=document.getElementById("phone");
    var show=document.getElementById("showPro");
    btn.onclick=function (ev) {
       if (pro.value.length<3)
           alert("输入项目名太短");
       else if(phone.value.length<8)
           alert("请输入正确的号码");
       else {
           $(function () {
               $.ajax({
                   type: "post",
                   url: "view/joinProject",
                   async: false,
                   data: {
                       "proName": pro.value,
                       "phone": phone.value
                   },
                   dataType: "json",
                   success: function (data) {
                       var obj = JSON.parse(data);
                       if (obj.is==10){
                           alert("申请成功");
                       } else{
                           alert(obj.is);
                       }

                   },
                   error: function () {
                       alert("页面加载失败");
                   }
               });
           })
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
