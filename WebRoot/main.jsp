<%@page import="com.hust.model.user.User"%>
<%@page import="com.hust.dao.menu.impl.MenuDaoImpl"%>
<%@page import="com.hust.model.menu.Menu"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>布之峰网站后台管理系统</title>
<link rel="icon" href="appico.ico" type="image/x-icon"/>
<link rel="shortcut icon" href="appico.ico" type="image/x-icon"/>
<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css">
<script type="text/javascript" src="js/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery/easyui-lang-zh_CN.js"></script>
<script src="resources/js/common.js"></script>
<script>
jQuery(function () {
    InitLeftMenu();
    jQuery('body').layout();
})

function InitLeftMenu() {
    jQuery('.easyui-accordion li a').hover(function () {
        jQuery(this).parent().addClass("hover");
    }, function () {
        jQuery(this).parent().removeClass("hover");
    });
}
function addTab(subtitle, url) {
    if (!jQuery('#tabs').tabs('exists', subtitle)) {
        jQuery('#tabs').tabs('add', {
            title: subtitle,
            content: createFrame(url),
            closable: true,
            width: jQuery('#mainPanle').width() - 10,
            height: jQuery('#mainPanle').height() - 26
        });
    } else {
        jQuery('#tabs').tabs('select', subtitle);
    }
}
function createFrame(url) {
    var s = '<iframe name="mainFrame" scrolling="no" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
    return s;
}
function logout(){
    window.location.href="logout"; 
}

function add(title,content){
			if ($('#tabs').tabs('exists',title)){
			  $('#tabs').tabs('select',title);
			}else{
			$('#tabs').tabs('add',{
				title: title,
				content: '<iframe c scrolling="auto" frameborder="0"  src="'+content+'" style="width:100%;height:99%;"></iframe>',
				closable: true
			});}
		}
</script>
<style type="text/css">
body {
	margin:0px;
	padding:0px;
	width:100%;
	height:100%;
}
.top_left{ background: url(resources/images/7.png) 0 0 no-repeat; height:76px; width:690px; float:left}
.top_middle{ background:url(resources/images/28.png) repeat-x; width:auto; height:100%; float: left}
.top_botton{ background:url(resources/images/29.png); height:74px; width:136px; float: right; margin-right:20px;}
        .easyui-accordion ul
        {
            list-style-type: none;
            margin: 0px;
            padding: 10px;
        }
        .easyui-accordion ul li
        {
            padding: 0px;
        }
        .easyui-accordion ul li a
        {
            line-height: 24px; text-decoration:none; color:#5c919c;
        }
        .easyui-accordion ul li div
        {
            margin: 2px 0px;
            padding-left: 10px;
            padding-top: 2px; background:#e6f4f7; border:1px solid #abdae4;
        }
        .easyui-accordion ul li div.hover
        {
            border: 1px dashed #99BBE8;
            background: #E0ECFF;
            cursor: pointer;
        }
        .easyui-accordion ul li div.hover a
        {
            color: #416AA3;
        }
        .easyui-accordion ul li div.selected
        {
            border: 1px solid #99BBE8;
            background: #E0ECFF;
            cursor: default;
        }
        .easyui-accordion ul li div.selected a
        {
            color: #416AA3;
            font-weight: bold;
        }
		.top_botton:hover{ background:  url("resources/images/29_2.png") repeat scroll 0 0 transparent;}
</style>
</head>
<body class="easyui-layout"  style="overflow-y:hidden;" scroll="no">
<%
		User user = (User) session.getAttribute("user");
			if (user == null)
		response.sendRedirect("login.html");
			else {
	%>
	<noscript>
        <div style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px;
            width: 100%; background: white; text-align: center;">
            <img src="images/noscript.gif" alt='抱歉，请开启脚本支持!' />
        </div>
    </noscript>
	<!-- top -->
 	<div data-options="region:'north'" border="false" style="height:76px;background: url(resources/images/28.png) 100% 0 no-repeat #50b8d6;">
 		<div class="top_left"></div>
        <div class="top_middle"></div>
        <div class="top_botton" onclick="logout()"></div>
        <div onclick="logout()" style="background:url()"></div>
 	</div>
	<div data-options="region:'west',split:true" title="导航菜单" style="width: 180px;overflow:hidden; " iconCls="icon-column">
		<div id="menu" class="easyui-accordion" fit="true" border="false">
		   <%
					MenuDaoImpl menudao = new MenuDaoImpl();
					List<Menu> menulist = (List<Menu>) session.getAttribute("menu");
					List<Menu> menulevel1 = menudao.getMenuByLevelFromList(menulist, 1);
					List<Menu> menulevel2 = menudao.getMenuByLevelFromList(menulist, 2);
					List<Menu> menulevel3 = menudao.getMenuByLevelFromList(menulist, 3);
					for (Menu menu2 : menulevel2) {	
				%>
			<div title="<%=menu2.getMenuName()%>" style="overflow:auto; padding: 10px;" iconCls="icon-reverse">
                <div title="<%=menu2.getMenuName()%>">
                    <ul>
                        <li>
                        <%
									for (Menu menu3 : menulevel3) {
														if (menu2.getMenuId().equals(
																menu3.getMenuId().substring(0, 5))) {
								%>
                        	<div>
                                <a href="javascript:void(0)" onClick="add('<%=menu3.getMenuName()%>','<%=menu3.getUrl()%>')"><%=menu3.getMenuName()%></a>
                            </div>
                            <%} }%>
                        </li>
                    </ul>
                </div>
            </div>	
            <%}%>
        </div>
	</div>
	<!-- center -->
	<div data-options="region:'center'" id="mainPanle" style="background: #eee;overflow:hidden;">
        <div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="主页" style="padding: 20px; background:url(resources/images/30.png) 50% 50% no-repeat #fff " id="home">
                <h1>Welcome${sessionScope.user.username}</h1>
            </div>
        </div>
    </div>  
	<!-- end -->
	<div data-options="region:'south'" border="false" style="height:20px;line-height:20px;background:#abdae4;">
		<div style="text-align: center; color:#255762;"></div>
	</div>
	<%
		}
	%>
</body>
</html>