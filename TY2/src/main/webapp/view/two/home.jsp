<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<%--
  Created by IntelliJ IDEA.
  User: 91308
  Date: 2018/8/21
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>home</title>

    <script type="text/javascript" src="../../js/jquery-3.3.1.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/homePage.css">
    <link rel="stylesheet" href="../../css/navs.css">

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
            <form role="search" class="row search-form">
                <div class="col-md-8 col-md-push-2">
                    <div class="search-wrapper">
                        <div class="search-container">
                            <input type="text" class="store-search typeahead form-control tt-hint inp1" readonly autocomplete="off" spellcheck="false" tabindex="-1" dir="ltr" style="">

                            <input id="search_text" name="local" type="text"  placeholder="执行人员搜寻..." required="" class="store-search typeahead form-control tt-input inp2" autocomplete="off" spellcheck="false" dir="auto" >

                            <div id="auto_div" class="tip">

                            </div>
                        </div>
                    </div>
                    <button type="button" class="btn-primary btn"  onclick="gos()" data-toggle="modal" data-target="#myModal">
                        <img src="../image/homePage/sousuo.png"> Search
                    </button>
                </div>
            </form>
        </div>
    </header>
</div>

<hr>
<section class="content-wrap">
    <div class="container">
        <div class="row">
            <main class="col-md-8 main-content">
                <%--高评分执行者列表--%>
                <c:set var="coo" value="${cookie.userNumber.value}"/>
                    <c:set var="list" value="${cookie.id.value}"/>
                    <c:forEach items="${sessionScope[list].userList}" var="item">
                        <article class="post">
                            <div class="post-head">
                                 <h1 class="post-title">
                                     <i>
                                        优秀执行者： ${item.userNumber}
                                     </i>
                                 </h1>
                            <div class="post-meta">
                                <span class="author">
                                    用户名：<i>${item.userNumber}</i>
                                </span> •
                            <time class="post-date">
                                   评价人： ${item.publisher}
                             </time>
                             </div>
                            </div>
                        <div class="featured-media">
                             <a href="#">
                                <img src="memberImg?userNumber=${item.userNumber}" style="max-width: 130px;">
                            </a>
                        </div>
                        <div class="post-content">
                            <p></p>
                            <p>
                            ${item.message}
                             </p>
                            <p></p>
                        </div>
                        <div class="post-permalink">
                            <div class="page">
                                 <nav class="nav pull-left">
                                    <ul>
                                         <li><a onclick="to_people('${item.userNumber}','${item.count}')" data-toggle="modal" data-target="#myModal" style="max-width: 70px;max-height: 70px;padding-top:3%;padding-left: 30%">签约</a></li>
                                    </ul>
                                 </nav>
                        <div class="pull-right">
                             &nbsp;信誉评分：&nbsp;
                            <c:forEach begin="1" end="${item.count}">
                                <img src="../image/homePage/t2.png">
                            </c:forEach>
                        </div>
                        </div>
                        <footer class="post-footer clearfix"></footer>
                        </div>
                        </article>
                    </c:forEach>
            </main>
            <aside class="col-md-4 teams">
                <div class="shi-1">
                    <div class="page">
                        <section class="demo">
                            <nav class="nav">
                                <ul>
                                    <li><a href="#">首页</a></li>
                                    <li><a href="enterProjectTwo">项目</a></li>
                                    <li><a href="enterPerson">个人</a></li>
                                </ul>
                            </nav>
                        </section>
                    </div>
                </div>
                <div class="shi-1">
                    <div class="shi-2">
                        <span data-reactid="96">Welcome
                           ${user.nickName}
                        </span>
                    </div>
                    <div class="shi-3">
                        <div class="shi-4">
                            <img class="img-responsive" src="<s:url action="getOwnImg"/>" style="margin-left: 30%;max-width: 120px;max-height: 120px;">
                        </div>
                    </div>
                    <div class="shi-4" style="margin-right: 18%;">
                        <button type="button" class="btn btn-default shi-5">
                            <span data-reactid="103">
                                &nbsp;用户名：&nbsp;
                                ${sessionScope[coo].userNumber}
                            </span>
                        </button>
                    </div>
                </div>

                <div class="shi-1">
                    <div class="shi-2">
                        <span data-reactid="96">Project</span>
                    </div>
                    <div class="shi-3">
                        <div class="shi-4">
                            <img src="../image/homePage/pros.png" >
                        </div>
                        <span data-reactid="100">Create <b>your project</b> for free. <b>for feature </b> .....</span>
                    </div>
                    <div class="shi-4">
                        <button type="button" class="btn btn-default shi-5">
                            <img src="../image/homePage/sendss.png">
                            <a  href="enterProjectTwo">&nbsp;&nbsp;send &nbsp;&nbsp;</a>
                        </button>
                    </div>
                </div>


                <div class="shi-1">
                    <div class="shi-2">
                        <span data-reactid="96" class="type">About us</span>
                    </div>
                    <div class="weight">
                        <div class="content tab-content" style="padding-left: 5px;padding-bottom: 20px">
                            <div style="min-height: 5px"></div>
                            <img src="../image/homePage/ab1.png"  id="aboutUs">
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
<%--个人信息展示--%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="people">
                </h4>
            </div>
            <div class="modal-body">
                <img src="" id="people-img" style="max-width: 130px;">
                <h4 id="people-count"></h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div>
    </div>
</div>



<script>
    var people=document.getElementById("people");
    var people_img=document.getElementById("people-img");
    var people_count=document.getElementById("people-count");
    function to_people(peo,count) {
        people.innerHTML=peo;
        people_img.src="getTu?userNumber="+peo;
        people_count.innerHTML="综合评价:"+count;
    }

</script>
<script>
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
</script>
<script type="text/javascript">
        // 搜索显示
    var searchMes=document.getElementById("search_text");
    function gos() {
        if (searchMes.value.length < 6) {
            alert("输入用户名太短");
        } else{
            $.ajax({
                type: "post",
                url: "view/showSearchUser",
                async: false,
                data: {
                    "userNumber": searchMes.value
                },
                dataType: "json",
                success: function (data) {
                    var res=JSON.parse(data)
                    if (res.is==1){
                        var people=document.getElementById("people");
                        var people_img=document.getElementById("people-img");
                        var people_count=document.getElementById("people-count");
                        people.innerHTML=res.userNumber;
                        people_img.src="getTu?userNumber="+res.userNumber;
                        people_count.innerHTML="email："+res.email;

                    } else{
                        alert(res.is);
                    }
                },
                error: function () {
                    alert("加载超时");
                }
            });
        }
    }

</script>
<script type="text/javascript">
    //获取后台数据
    var test_list;
    $(function() {
        $.ajax({
            type: "post",
            url: "view/searchUser",
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
