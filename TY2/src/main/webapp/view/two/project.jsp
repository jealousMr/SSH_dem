<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: 91308
  Date: 2018/8/23
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project</title>
    <script src="../../js/jquery-3.3.1.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/projectPage.css">
    <link rel="stylesheet" href="../../css/project.css">
</head>
<body>
<header class="header">
    <div class="navbar navbar-default main-menu">
        <div class="container">
            <div class="row">
                <div class="navbar-collapse">
                    <ul class="nav navbar-nav navbar-left">
                        <li class="first-page">
                            <a href="eh"><img src="../../image/projectPage/toHome.png" style="margin: 2px;">首页</a>
                        </li>
                        <li class="separator"></li>
                        <li class="first-page">
                            <a href="enterPerson"><img src="../../image/projectPage/toperson.png" style="margin: 2px;">个人中心</a>
                        </li>
                        <li class="first-page">
                            <a href="#"><img src="../../image/projectPage/topro.png" style="margin: 2px;">项目</a>
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
                                        <img src="../../image/projectPage/idea.png" class="img-responsive">
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
        Sending your
        <span style="color: #fd5c63">Project</span>
    </h3>
</div>
<div class="container toB">
    <div class="row">
        <div class="col-lg-12 text-center">
            <h2 style=" font-family:'Microsoft YaHei';">外包发布</h2>
            <hr class="star-primary">
        </div>
    </div>
    <div class="row">
        <p class="p1" style="text-align: center">
            Please input the name of the project and the specific circumstances of the project to publish your project.  </p>
    </div>
    <div class="row">
        <form class="ng-dirty ng-valid-parse ng-invalid ng-invalid-required ng-valid-email hero_form">
            <div class="form-group">
                <label>项目名称</label>
                <input class="form-control input-lg ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched" type="text" id="proName" required="" ng-model="lead.first_name">
            </div>
            <div class="form-group">
                <label>项目详情</label>
                <input class="form-control input-lg ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched" type="text" id="proContent" required="" ng-model="lead.first_name">
            </div>
            <div class="form-group">
                <label>截至时间</label>
                 <input class="form-control input-lg ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched" type="date" id="times"  required="" ng-model="lead.first_name">
            </div>
            <div class="form-group">
                <label>预计报酬(￥)</label>
                <input class="form-control input-lg ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched" type="text" id="reward" required="">
            </div>
            <div class="form-group">
                <label>项目类型</label>
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3">
                            <section>
                                <h3>美工类型</h3>
                                <div class="checkboxFour">
                                    <input type="checkbox" id="c1" value="1"/>
                                    <label></label>
                                </div>
                            </section>
                        </div>
                        <div class="col-lg-3">
                            <section>
                                <h3>家教类型</h3>
                                <div class="checkboxFour">
                                    <input type="checkbox" value="2" id="c2"/>
                                    <label></label>
                                </div>
                            </section>
                        </div>
                        <div class="col-lg-3">
                            <section>
                                <h3>外包项目</h3>
                                <div class="checkboxFour">
                                    <input type="checkbox" value="3" id="c3"/>
                                    <label></label>
                                </div>
                            </section>
                        </div>
                        <div class="col-lg-3">
                            <section>
                                <h3>其他类型</h3>
                                <div class="checkboxFour">
                                    <input type="checkbox" value="4" id="c4"/>
                                    <label></label>
                                </div>
                            </section>
                        </div>
                    </div>
                </div>
            </div>

            <div class="submit">
                <input type="file" style="display: none" id="proImg" name="proImg">
                <button id="lead-submit" type="button" class="btn btn-lg btn-danger pull-right">
                    点击发布
                    <i class="fa fa-spinner fa-pulse ng-hide"></i>
                </button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    var proName=document.getElementById("proName");
    var proContent=document.getElementById("proContent");
    var reward=document.getElementById("reward");
    var time=document.getElementById("times");

    $(function() {
        $("#lead-submit").click(function () {
            var cc=null;
            if ($("#c1").get(0).checked)
                cc=document.getElementById("c1");
            else if($("#c2").get(0).checked)
                cc=document.getElementById("c2");
            else if ($("#c3").get(0).checked)
                cc=document.getElementById("c3");
            else if($("#c4").get(0).checked)
                cc=document.getElementById("c4");
           if (cc==null)
               alert("请选择项目类型");
           else if(proName.value.length<3)
               alert("请输入项目名");
           else if(proContent.value.length<3)
               alert("请填写项目具体描述");
           else if(reward.value==null||reward.value=="")
               alert("请输入报酬数目￥");
           else if(isNaN(reward.value))
               alert("请输入数字");
           else if(time.value==null)
               alert("请选择项目完成时间");
           else {
               $.ajax({
                   type: "post",
                   url: "view/addProject",
                   async:false,
                   data:{
                      "proName":proName.value,
                       "proContent":proContent.value,
                       "reward":reward.value,
                       "deadline":time.value,
                       "type":cc.value
                   },
                   dataType: "json",
                   success: function(data){
                       var obj = JSON.parse(data);
                       alert(obj.is);
                       $(document).ready(function () {
                           if(location.href.indexOf("#reloaded")==-1){
                               location.href=location.href+"#reloaded";
                               location.reload();
                           }
                       });
                   },
                   error:function () {
                       alert("提交失败");
                   }
               });
           }
        })
    })

</script>
<hr>
<div class="container">
    <div class="row">
        <div>
            <img src="../../image/projectPage/his1.png" class="img-responsive" style="margin-left: 44%;" id="history">
        </div>
        <%--项目管理--%>
            <link rel="stylesheet" href="../../css/morePro.css">
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title">
                            <img src="../../image/personPage/gou.png"><i id="pro-title"></i>
                        </h4>
                    </div>
                    <div class="modal-body">
                        <section class="secss">
                            <div class="container">
                                <div class="row integrations-container">
                                    <div class="col-sm-6" style="margin-left: -2%">
                                        <div class="feature-container">
                                            <img src="../../image/personPage/idea.jpg" class="hidden-xs" style="max-width: 130px">
                                            <h4 id="pro-na"></h4>
                                            <form action="projectImg" namespace="/view" method="POST" enctype="multipart/form-data" id="form2">
                                                <input type="file" style="display: none" name="imgs" id="to-img">
                                                <input type="text" name="sign" style="display: none;" id="go-pro">
                                                 <button class="btn btn-default" id="rto-img" type="button">图片设置</button>
                                                 <button class="btn btn-default" type="button" id="sure-go">确认上传</button>
                                            </form>
                                        </div>
                                        <div class="feature-container">
                                            <img src="" class="hidden-xs" id="img-applicant" style="max-width: 100px">
                                            <h4 id="pro-applicant"></h4>
                                            <button class="btn btn-default" type="button" onclick="cancel()">取消资格</button>
                                            <button class="btn btn-default" type="button" onclick="becomeExecutor()">授予执行</button>
                                        </div>
                                        <div class="feature-container">
                                            <img src="../../image/loginPage/180.png" class="hidden-xs" id="img-executor" style="max-width: 100px">
                                            <h4 id="pro-executor"></h4>
                                            <button class="btn btn-default" type="button">☝执行者</button>
                                            <button class="btn btn-default" type="button" onclick="succ()">项目结算</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </div>

        <c:set var="coo" value="${cookie.id.value}"/>
        <c:forEach items="${sessionScope[coo].projectList}" var="item">
            <div class="col-lg-6 ml-auto ">
                <div class="md-4">
                    <div class="position position--genuine featured-sign position--featured">
                        <div class="btn btn-xs btn-danger" style="position: absolute; top: -10px; pointer-events: none;">
                            Pro!
                        </div>
                        <div class="flax">
                            <img style="max-width: 60px;max-height: 70px;" src="getProImg?proName=${item.proName}">
                            <h4 class="persons2">${item.proName}</h4><br>
                            <span class="persons">发布时间:
                                ${item.sendTime}
                            </span>
                            <span class="persons">执行者：
                                <c:if test="${item.executor!='-1'}">
                                    ${item.executor}
                                </c:if>
                             </span>
                            <span class="persons">
                                <button class="btn btn-default  pull-right" onclick="mores(this)" type="button" value="${item.proName}" data-toggle="modal" data-target="#myModal"><i>项目管理</i></button>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script type="text/javascript">
   var sends=document.getElementById("sends");
   var file=document.getElementById("file");
   sends.onclick=function (ev) { file.click(); }

    $(function() {
        $("#file").change(function() {
            var fileObj = $(this)[0];
            var windowURL = window.URL || window.webkitURL;
            var dataURL;
            if(fileObj && fileObj.files && fileObj.files[0]) {
                dataURL = windowURL.createObjectURL(fileObj.files[0]);
                $("#loading").attr("src", dataURL);
            }
        })
    })

    var his=document.getElementById("history");
   his.onmousemove=function () {
       his.src="../../image/projectPage/his2.png";
   }
   his.onmouseout=function () {
       his.src="../../image/projectPage/his1.png";
   }

</script>
<form style="display: none" id="pay" action="enterPayPage" method="post">
    <input type="submit" style="display: none" id="subPay">
</form>
<script type="text/javascript">
    var NAME;//当前项目名

    //按钮功能
    function becomeExecutor() {
        $.ajax({
            type: "post",
            url: "view/becomeExecutor",
            async:false,
            data:{
                "proName":NAME
            },
            dataType: "json",
            success: function(data){
                var obj = JSON.parse(data);
                var pro_executor=document.getElementById("pro-executor");
                var pro_applicant=document.getElementById("pro-applicant");
                pro_executor.innerHTML=pro_applicant.innerHTML;
                alert("操作成功");
            },
            error:function () {
                alert("连接超时");
            }
        });
    }
    function cancel(){
        var pro_applicant=document.getElementById("pro-applicant");
        $.ajax({
            type: "post",
            url: "view/cancelApplicant",
            async:false,
            data:{
                "proName":NAME
            },
            dataType: "json",
            success: function(data){
                var obj = JSON.parse(data);
                alert("操作成功");
                pro_applicant.innerHTML="无";
            },
            error:function () {
                alert("连接超时");
            }
        });
    }
    function succ() {
        var mes="确定结算此项目吗？"
        if (confirm(mes)==true){
            sendto();
            return true;
        }else{
            return false;
        }
    }
    function sendto() {//项目结算
        $.ajax({
            type: "post",
            url: "view/endProject",
            async:false,
            data:{
                "proName":NAME
            },
            dataType: "json",
            success: function(data){
                var obj = JSON.parse(data);
                if(obj.is=='1'){
                    alert("结算成功")
                    var pay=document.getElementById("subPay");
                    pay.click()
                }else{
                    alert(obj.is);
                }
            },
            error:function () {
                alert("连接超时");
            }
        });

    }

   function mores(ev) {
       var proName=ev.value;
       NAME=proName;

       $.ajax({
           type: "post",
           url: "view/getMoreProject",
           async:false,
           data:{
               "proName":proName
           },
           dataType: "json",
           success: function(data){
               var obj = JSON.parse(data);
               NAME=obj.proName;
               var pro_title=document.getElementById("pro-title");
               var pro_na=document.getElementById("pro-na");
               var pro_applicant=document.getElementById("pro-applicant");
               var pro_executor=document.getElementById("pro-executor");
               pro_title.innerHTML=obj.proName;
               pro_na.innerHTML=obj.proName;
               if (obj.applicant=='-1'){
                   pro_applicant.innerHTML="暂无申请人"
               } else{
                   pro_applicant.innerHTML=obj.applicant;
               }
               if (obj.executor=='-1'){
                   pro_executor.innerHTML="暂无执行人员";
               } else {
                   pro_executor.innerHTML=obj.executor;
               }

               //处理申请人头像
               var img_applicant=document.getElementById("img-applicant");
               if (obj.applicant!='-1'){
                   img_applicant.src="getTu?userNumber="+obj.applicant;
               }else {
                  img_applicant.src="../../image/loginPage/180.png";
               }
               //处理执行者头像
               var img_executor=document.getElementById("img-executor");
               if (obj.executor=='-1'||obj.executor==null){
                   img_executor.src="../../image/loginPage/180.png";
               } else{
                   img_executor.src="getTu?userNumber="+obj.executor;
               }
               //处理项目图片
               var to_img=document.getElementById("to-img");
               var rto_img=document.getElementById("rto-img");
               rto_img.onclick=function () {
                   to_img.click();
               }
               var sure_go=document.getElementById("sure-go");
               var form2=document.getElementById("form2");
               sure_go.onclick=function () {//提交图片
                    var go_pro=document.getElementById("go-pro");
                    go_pro.value=obj.proName;
                    form2.submit();
               }





           },
           error:function () {
               alert("连接超时");
           }
       });
   }
</script>
<script type="text/javascript">
    var cli=document.getElementById("proImg");
    var bb=document.getElementById("bb");
    bb.onclick=function (ev) {
        cli.click();
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
