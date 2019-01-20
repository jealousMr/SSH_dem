<%--
  Created by IntelliJ IDEA.
  User: 91308
  Date: 2018/7/28
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>首页</title>
    <script src="../js/jquery-3.3.1.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/homePage.css">
    <link rel="stylesheet" href="../css/facetu.css">
</head>
<body class="bb">
<script>
    $(document).ready(function () {
        if(location.href.indexOf("#reloaded")==-1){
            location.href=location.href+"#reloaded";
            location.reload();
        }
    });
</script>
<div class="yin">
    <header class="main-header">
        <div class="container">
            <div class="row section-title">
                <div class="col-md-12 go">
                    <h1 class="titleSize">Find</h1>
                </div>
            </div>
            <form role="search" class="row search-form" action="homeToSearchProject">
                <div class="col-md-8 col-md-push-2">
                    <div class="search-wrapper">
                        <div class="search-container">
                            <input type="text" class="store-search typeahead form-control tt-hint inp1" readonly autocomplete="off" spellcheck="false" tabindex="-1" dir="ltr" style="">

                            <input id="search_text" name="proName" type="text"  placeholder="项目搜索..." required="" class="store-search typeahead form-control tt-input inp2" autocomplete="off" spellcheck="false" dir="auto" >

                            <div id="auto_div" class="tip">

                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn-primary btn">
                        <img src="../image/homePage/sousuo.png"> Search
                    </button>
                </div>
            </form>
        </div>
    </header>
</div>

<nav class="main-navigation">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="navbar-header">
					<span class="nav-toggle-button collapsed" data-toggle="collapse" data-target="#main-menu" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<img src="../image/homePage/ss.png" class="fa fa-bars" id="cli">
					</span>
                </div>
                <div class="navbar-collapse collapse " id="main-menu" aria-expanded="false" style="height: 1px;">
                    <div class="container">
                        <div class="row">
                            <ul>
                                <li>
                                    <div class='link'>
                                        <a  target='_blank'></a>
                                        <a  target='_blank'></a>
                                        <a  target='_blank'></a>
                                        <a  target='_blank'></a>
                                        <div class='cube github'>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/homes.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/homes.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/homes.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/homes.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/homes.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/homes.png" class="img-responsive">
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class='link'>
                                        <a href="enterPersonPage" target='_blank'></a>
                                        <a href="enterPersonPage" target='_blank'></a>
                                        <a href="enterPersonPage" target='_blank'></a>
                                        <a href="enterPersonPage" target='_blank'></a>
                                        <div class='cube github'>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/mans.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/mans.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/mans.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/mans.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/mans.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/mans.png" class="img-responsive">
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class='link'>
                                        <a href='enterProjectPage' target='_blank'></a>
                                        <a href='enterProjectPage' target='_blank'></a>
                                        <a href='enterProjectPage' target='_blank'></a>
                                        <a href='enterProjectPage' target='_blank'></a>
                                        <div class='cube github'>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/logo_project.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/logo_project.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/logo_project.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/logo_project.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/logo_project.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/logo_project.png" class="img-responsive">
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class='link'>
                                        <a onclick="clickAbs()" target='_blank'></a>
                                        <a onclick="clickAbs()" target='_blank'></a>
                                        <a onclick="clickAbs()" target='_blank'></a>
                                        <a onclick="clickAbs()" target='_blank'></a>
                                        <div class='cube github'>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/aboutUs.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/aboutUs.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/aboutUs.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/aboutUs.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/aboutUs.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/aboutUs.png" class="img-responsive">
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class='link'>
                                        <a  target='_blank'></a>
                                        <a  target='_blank'></a>
                                        <a  target='_blank'></a>
                                        <a  target='_blank'></a>
                                        <div class='cube github'>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/github.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/github.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/github.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/github.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/github.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/github.png" class="img-responsive">
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class='link'>
                                        <a  target='_blank'></a>
                                        <a  target='_blank'></a>
                                        <a  target='_blank'></a>
                                        <a  target='_blank'></a>
                                        <div class='cube github'>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/facebook.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/facebook.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/facebook.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/facebook.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/facebook.png" class="img-responsive">
                                            </div>
                                            <div style="background-color: transparent;">
                                                <img src="../image/homePage/facebook.png" class="img-responsive">
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</nav>

<section class="content-wrap">
    <div class="container">
        <div class="row">
            <main class="col-md-8 main-content">
                <c:set var="lis" value="${cookie.id.value}"/>
                <c:forEach items="${sessionScope[lis].listProject}" var="item">
                        <article class="post">
                            <div class="post-head">
                                <h1 class="post-title">
                                    <i>
                                        ${item.proName}
                                    </i>
                                </h1>
                                <div class="post-meta">
								<span class="author">
									发布者：<i style="color: #858585">
                                    ${item.publisher}
                                </i>
								</span> •
                                    <time class="post-date">
                                        ${item.sendTime}
                                    </time>
                                </div>
                            </div>
                            <div class="featured-media" style="padding-left: 15%;">
                                <img src="getProImg?proName=${item.proName}" class="img-responsive">
                            </div>
                            <div class="post-content">
                                <p></p>
                                <p>
                                    项目描述--
                                    ${item.proContent}
                                </p>
                                <p></p>
                            </div>
                            <div class="post-permalink">
                                <a href="enterProjectPage" class="btn btn-default">了解更多</a>
                                <span class="btn btn-default pull-right">报酬￥：${item.reward}</span>
                            </div>
                            <footer class="post-footer clearfix"></footer>
                        </article>
                </c:forEach>
                <nav class="pagination" role="navigation">
                    <a class="older-posts" href="tonext">
                       <img src="../image/homePage/top.png">
                    </a>
                    <i class="page-number">
                        上一页 / 下一页
                    </i>
                    <a class="older-posts" href="tonext">
                        <img src="../image/homePage/next.png">
                    </a>
                </nav>
            </main>

            <c:set var="coo" value="${cookie.userNumber.value}"/>
            <aside class="col-md-4 teams">
                <div class="shi-1">
                    <div class="shi-2">
                        <span data-reactid="96">Welcome--<i style="font-size: 14px">${sessionScope[coo].nickName}</i></span>
                    </div>
                    <div class="shi-3">
                        <div class="shi-4">
                            <img class="img-responsive"  src="<s:url action="getOwnImg"/>" style="margin-left: 26%;max-width: 100px;max-height: 100px;">
                        </div>
                        <span data-reactid="100">Welcome to <b>${sessionScope[coo].userNumber}</b> landing</span>
                    </div>
                    <div class="shi-4">
                        <button type="button" class="btn btn-default shi-5" style="width: 100%;">
                            <span data-reactid="103">
                                &nbsp;信誉评分：&nbsp;
                                <c:forEach var="its" begin="1" end="${sessionScope[lis].userCount}">
                                    <img src="../image/homePage/t2.png">
                                </c:forEach>
                            </span>
                        </button>
                    </div>
                </div>

                <div class="shi-1">
                    <div class="shi-2">
                        <span data-reactid="96">Team</span>
                    </div>

                    <div class="shi-3">
                        <div class="shi-4">
                            <img src="../image/homePage/tm.png" style="margin-left: 30%">
                        </div>
                        <c:if test="${sessionScope[coo].teamName=='-1'}">
                            <span data-reactid="100">Create <b>your team</b> for free. <b>for feature </b> .....</span>
                        </c:if>
                        <c:if test="${sessionScope[coo].teamName!='-1'}">
                            <h4 style="text-align: center;font-family:Georgia;color: #c7c7c7;">${sessionScope[coo].teamName}</h4>
                        </c:if>
                    </div>

                    <div class="shi-4">
                            <a href="/view/enterTeamPage" class="pull-right">
                                <img src="../image/homePage/join1.png" id="joinT">
                            </a>
                    </div>
                </div>
                <div class="shi-1">
                    <div class="shi-2">
                        <span data-reactid="96" class="type">TYPE</span>
                    </div>
                    <div class="weight">
                        <div class="content tab-content" style="padding-left: 5px;padding-bottom: 20px">
                            <div style="min-height: 5px"></div>
                            <i class="btn btn-default ts">
                                <a href="proType?type=1">美工</a>
                            </i>
                            <i class="btn btn-default ts">
                                <a href="proType?type=2">家教</a>
                            </i>
                            <i class="btn btn-default ts">
                                <a href="proType?type=1">美化界面</a>
                            </i>
                            <i class="btn btn-default ts">
                                <a href="proType?type=1">修图</a>
                            </i>
                            <i class="btn btn-default ts">
                                <a href="proType?type=2">数学教学</a>
                            </i>
                            <i class="btn btn-default ts">
                                <a href="proType?type=1">美工</a>
                            </i>
                            <i class="btn btn-default ts">
                                <a href="proType?type=2">家教</a>
                            </i>
                            <i class="btn btn-default ts">
                                <a href="proType?type=3">更多</a>
                            </i>
                            <i class="btn btn-default ts">
                                <a href="proType?type=3">其他</a>
                            </i>
                            <i class="btn btn-default ts">
                                <a href="#">...</a>
                            </i>
                        </div>
                    </div>
                </div>

                <div class="shi-1">
                    <div class="shi-2">
                        <span data-reactid="96" class="type">About us</span>
                    </div>
                    <div class="weight">
                        <div class="content tab-content" style="padding-left: 5px;padding-bottom: 20px">
                            <div style="min-height: 5px"></div>
                           <img src="../image/homePage/ab1.png" style="margin-left: 36%;" id="aboutUs">
                        </div>
                        <div>
                            <img src="../image/homePage/gitt.png" style="margin-left: 10%">
                            <img src="../image/homePage/face.png" style="margin: 4%">
                            <img src="../image/homePage/al.png" style="margin: 4%">
                        </div>
                    </div>
                </div>
            </aside>

        </div>
    </div>
</section>


<script type="text/javascript">
    var img=document.getElementById("joinT");
    img.onmousemove=function () {
        img.src="../image/homePage/join2.png";
    }
    img.onmouseout=function () {
        img.src="../image/homePage/join1.png";
    }
    var aboutUs=document.getElementById("aboutUs");
    aboutUs.onmousemove=function () {
        aboutUs.src="../image/homePage/ab2.png";
    }
    aboutUs.onmouseout=function () {
        aboutUs.src="../image/homePage/ab1.png";
    }
    aboutUs.onclick=function () {
        alert("制作者：。。。")
    }
    function clickAbs() {
        $("#aboutUs").click();
    }
</script>
<div class="footer">
    <div class="container footer-container">
        <ol class="breadcrumb" style="text-align: center">
            <li>首页</li>
            <li>新闻</li>
            <li>更多资讯</li>
            <li>发现项目</li>
            <li>提示</li>
            <li>信息</li>
        </ol>
        <p style="text-align: center">@啦啦啦制作</p>
    </div>
</div>

<script type="text/javascript">
    //获取后台数据
    var test_list;
    $(function() {
        $.ajax({
            type: "post",
            url: "view/searchProject",
            async:false,
            data:{
                "local":"some"
            },
            dataType: "json",
            success: function(data){
                test_list=JSON.parse(data);
            },
            error:function () {
                alert("页面加载失败");
            }
        });
    })

    var old_value = "";
    var highlightindex = -1;
    //自动完成
    function AutoComplete(auto, search, mylist) {
        if ($("#" + search).val() != old_value || old_value == "") {
            var autoNode = $("#" + auto);   //缓存对象（弹出框）
            var carlist = new Array();
            var n = 0;
            old_value = $("#" + search).val();
            for (i in mylist) {
                if (mylist[i].indexOf(old_value) >= 0) {
                    carlist[n++] = mylist[i];
                }
            }
            if (carlist.length == 0) {
                autoNode.hide();
                return;
            }
            autoNode.empty();  //清空上次的记录
            for (i in carlist) {
                var wordNode = carlist[i];
                var newDivNode = $("<div>").attr("id", i);
                newDivNode.attr("style", "font:14px/25px arial;height:25px;width:600px;opacity:0.5;padding:0 8px;cursor: pointer;");
                newDivNode.html(wordNode).appendTo(autoNode);  //追加到弹出框

                newDivNode.mouseover(function () {
                    if (highlightindex != -1) {
                        autoNode.children("div").eq(highlightindex).css("background-color", "white");
                    }
                    highlightindex = $(this).attr("id");
                    $(this).css("background-color", "#e3f4ff");
                });
                newDivNode.mouseout(function () {
                    $(this).css("background-color", "#E3F4FF");
                });

                newDivNode.click(function () {
                    var comText = autoNode.hide().children("div").eq(highlightindex).text();
                    highlightindex = -1;
                    $("#" + search).val(comText);
                })
                if (carlist.length > 0) {
                    autoNode.show();
                } else {
                    autoNode.hide();
                    highlightindex = -1;
                }
            }
        }
        document.onclick = function (e) {
            var e = e ? e : window.event;
            var tar = e.srcElement || e.target;
            if (tar.id != search) {
                if ($("#" + auto).is(":visible")) {
                    $("#" + auto).css("display", "none")
                }
            }
        }
    }
    $(function () {
        old_value = $("#search_text").val();
        $("#search_text").focus(function () {
            if ($("#search_text").val() == "") {
                AutoComplete("auto_div", "search_text", test_list);
            }
        });
        $("#search_text").keyup(function () {
            AutoComplete("auto_div", "search_text", test_list);
        });
    });
</script>

</body>
</html>
