<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>修改密码</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="js/jquery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css">
<script type="text/javascript" src="js/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script>    
<script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	
	function resetForm() {
		$('#advertisementform').form('reset');		
	}
	function submitForm() {
	    $.messager.confirm('确认', '是否确认提交修改？', function(r){
				if (r){
					$('#advertisementform').form('submit', {
					  success : function(data) {	
					    var data = eval('(' + data + ')');				    
				        $.messager.alert('返回结果',data.msg,'info');
				      }
			        });
				}
			}); 
	}	
	
</script>

</head>

<body>
	<div class="easyui-panel" style="padding:2px;" data-options="fit:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'south',border:false"
				style="height:50px;padding:2px">
				<a id="submitButton" class="easyui-linkbutton"
					data-options="iconCls:'icon-ok'" href="javascript:void(0)"
					onclick="javascript:submitForm()">提交</a> <a id="resetButton"
					class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
					href="javascript:void(0)" onclick="javascript:resetForm()">重填</a>
			</div>
			<div data-options="region:'center',fit:true" style="padding:2px">
				<form id="advertisementform" method="post"
					action="index/indexAction_updatePassword">
					<input type="hidden" name="documentId" value="${document.documentId }"/> 
					<table style="padding: 0pt 0pt 0pt 5px; width: 100%;font-size:12px">
						<tr>
							<td>原密码：<input name="oldPassword" type="password"/></td>							
						</tr>
						<tr>
							<td>新密码：<input name="newPassword" type="password"/></td>							
						</tr>
						<tr>
							<td>确认新密码：<input name="verifyPassword" type="password"/></td>							
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
