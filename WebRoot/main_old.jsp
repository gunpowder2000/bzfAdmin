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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>企业信息平台后台管理</title>
<link rel="icon" href="appico.ico" type="image/x-icon"/>
<link rel="shortcut icon" href="appico.ico" type="image/x-icon"/>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="企业信息平台后台管理">
<link rel="stylesheet" type="text/css"
	href="js/jquery/themes/cupertino/easyui.css">
<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css">
<script type="text/javascript" src="js/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
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
</head>

<body>
	<%
		User user = (User) session.getAttribute("user");
			if (user == null)
		response.sendRedirect("login.html");
			else {
	%>

	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',border:false"
			style="height:34px;padding:1px 0 0 5px">
			<table width="100%">
				<tr>
					<td>欢迎<span style="color:red">${sessionScope["user"].username}</span>登录系统
					</td>
					<td align="right" style="padding:0 20px 0 0 "><a href="logout"
						class="easyui-linkbutton" data-options="iconCls:'icon-back'">注销</a>
					</td>
				</tr>
			</table>
		</div>

		<div data-options="region:'west',split:true,title:'功能菜单'"
			style="width:150px">
			 
			<ul class="easyui-tree">
				<%
					MenuDaoImpl menudao = new MenuDaoImpl();
								List<Menu> menulist = (List<Menu>) session.getAttribute("menu");
								List<Menu> menulevel1 = menudao.getMenuByLevelFromList(
										menulist, 1);
								List<Menu> menulevel2 = menudao.getMenuByLevelFromList(
										menulist, 2);
								List<Menu> menulevel3 = menudao.getMenuByLevelFromList(
										menulist, 3);
								for (Menu menu1 : menulevel1) {
				%>
				<li><span><%=menu1.getMenuName()%></span>
					<ul>
						<%
							for (Menu menu2 : menulevel2) {
										if (menu1.getMenuId().equalsIgnoreCase(
												menu2.getMenuId().substring(0, 2))) {
						%>
						<li data-options="state:'closed'"><span><%=menu2.getMenuName()%></span>
							<ul>
								<%
									for (Menu menu3 : menulevel3) {
														if (menu2.getMenuId().equals(
																menu3.getMenuId().substring(0, 5))) {
								%>
								<li><span><a href="javascript:void(0)" onClick="add('<%=menu3.getMenuName()%>','<%=menu3.getUrl()%>')"><%=menu3.getMenuName()%></a></span></li>
								<%
									}
													}
								%>
							</ul></li>
						<%
							}
									}
						%>
					</ul></li>
				<%
					}
				%>				
			</ul>
			
			
		</div>
		<div data-options="region:'center',iconCls:'icon-ok'">
		<div id="tabs" class="easyui-tabs" style="width:700px;height:auto" data-options="fit:true">
		</div>
		</div>
	</div>
	<%
		}
	%>
</body>
</html>
