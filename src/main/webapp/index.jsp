<%--
  Created by IntelliJ IDEA.
  User: xxx_
  Date: 2021/1/9
  Time: 2:50 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>simpleBBS</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">simpleBBS 简易博客</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item  layui-this"><a href="index.jsp">主页</a></li>
            <li class="layui-nav-item"><a href="">精华帖</a></li>
            <li class="layui-nav-item"><a href="">发布博客</a></li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <jstl:if test="${userInfo == null}">
                <li class="layui-nav-item">
                    <a href="login.jsp">
                        点击登陆
                    </a>
                </li>
            </jstl:if>
            <jstl:if test="${userInfo != null}">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                        ${userInfo.username}
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="">修改信息</a></dd>
                        <dd><a href="">查看我的博客</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="logout">退出登陆</a></li>
            </jstl:if>
        </ul>
    </div>

    <div style="padding:20px 200px;">
        <!-- 内容主体区域 -->
        <div style=" width: 100%; height: 100%; background-color: #0C0C0C">

        </div>
    </div>

</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
</body>
</html>

