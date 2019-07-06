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

<title>商品类别信息管理</title>
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
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" charset="utf-8"
	src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="ueditor/ueditor.all.js">
	
</script>
<script type="text/javascript" charset="utf-8"
	src="ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
	function deletegoodscat() {

		var slrow = $('#datagrid').datagrid("getSelected");

		if (slrow != null) {
			$.messager.confirm('确认', '是否确认删除？', function(r) {
				if (r) {

					$.ajax({
						url : "goods/goodsAction_deleteGoodsCat",
						dataType : "json",
						data : {
							'goodsCatId' : slrow.goodsCatId
						},
						success : function(msg) {
							$.messager.alert('提示', msg.msg, 'info');
							$('#datagrid').datagrid('reload');
						},
						error : function() {
							$.messager.alert('提示', '删除失败', 'info');
						}
					});
				}

			});

		} else {
			$.messager.alert('警告', '请选择要删除的商品类别信息！', 'info');

		}
	}
	function searchadvertisement() {
		$('#datagrid').datagrid('load', {
			keyword : $('#key').attr('value')
		});
	}

	function openeditwindow(rowIndex, rowData) {
		var slrow = $('#datagrid').datagrid("getSelected");
		if (slrow != null) {
			$('#editform').form('load', slrow);
			$('#editwindow').window('open');
		} else {
			$.messager.alert('警告', '请选择要修改的商品信息！', 'info');

		}

	}
	function openaddwindow(rowIndex, rowData) {
		$('#addwindow').window('open');
	}

	function resetForm(editorparam) {
		if (editorparam == 'editor') {			
			$('#editform').form('load', $('#datagrid').datagrid('getSelected'));			
		} else {			
			$('#addform').form('reset');
		}
	}
	function submitForm(form, window) {
		$('#' + form).form('submit', {
			success : function(data) {
				var data = eval('(' + data + ')');
				$('#datagrid').datagrid('reload');
				$.messager.alert('返回结果', data.msg, 'info');
				$('#' + window).window('close');
			}
		});
	}

	function closeForm(window) {
		if (window == 'editwindow') {			
			$('#editform').form('load', $('#datagrid').datagrid('getSelected'));			
			$('#editwindow').window('close');
		} else {			
			$('#addform').form('reset');			
			$('#addwindow').window('close');
		}
	}
</script>
</head>

<body>

	<div class="easyui-panel" style="padding:2px;" data-options="fit:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north',border:false"
				style="height:50px;padding:5px">
				关键字： <input type="text" id="key" name="key" /> <a id="searchbutton"
					class="easyui-linkbutton" data-options="iconCls:'icon-search'"
					href="javascript:void(0)"
					onclick="javascript:searchadvertisement()">搜索</a>
			</div>

			<div data-options="region:'center'" style="padding:5px">
				<table id="datagrid" class="easyui-datagrid"
					data-options="fit:true,singleSelect:true,collapsible:true,url:'goods/goodsAction_queryGoodsCat',onDblClickRow:openeditwindow,toolbar:'#gridtb',  
                pagination:true,rownumbers:true">
					<thead>
						<tr>
							<th data-options="field:'goodsCatCode'" align="left">类别编码</th>
							<th data-options="field:'goodsCatName'" align="center">类别名称</th>
							<th data-options="field:'goodsCatLevel'" align="center">类别级别</th>
							<th data-options="field:'goodsCatFatherCode'" align="center">父类编号</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>

	<div id="editwindow" class="easyui-window" title="修改商品类别信息"
		data-options="modal:true,closed:true,iconCls:'icon-save',collapsible:false,minimizable:false,maximizable:false,resizable:false,inline:true"
		style="width:700px;height:400px;padding:10px;">		
		<form id="editform" method="post" action="goods/goodsAction_editGoodsCat">
			<input type="hidden" name="goodsCatId" />
			<table style="padding: 0pt 0pt 0pt 5px; width: auto">
				<tr>
					<td valign="top">商品名称：<input name="goodsCatName"
						class="easyui-validatebox" data-options="required:true" />
					</td>
				</tr>	
			</table>
		</form>
		<div>
			<a id="submitButton" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'" href="javascript:void(0)"
				onclick="javascript:submitForm('editform','editwindow')">提交</a> <a
				id="resetButton" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'" href="javascript:void(0)"
				onclick="javascript:resetForm('editor')">重填</a> <a id="closeButton"
				class="easyui-linkbutton" data-options="iconCls:'icon-back'"
				href="javascript:void(0)"
				onclick="javascript:closeForm('editwindow')">取消</a>

		</div>
	</div>
	<div id="addwindow" class="easyui-window" title="添加商品类别信息"
		data-options="modal:true,closed:true,iconCls:'icon-save',collapsible:false,minimizable:false,maximizable:false,resizable:false,inline:true"
		style="width:700px;height:400px;padding:10px;">		
		<form id="addform" method="post" action="goods/goodsAction_addGoodsCat">
			<table style="padding: 0pt 0pt 0pt 5px; width: auto">
				<tr>
					<td valign="top">类别名称：<input name="goodsCatName"
						class="easyui-validatebox" data-options="required:true" />
					</td>
				</tr>
				<tr>
					<td valign="top">类别级别：<label><input name="goodsCatLevel" type="radio" value="1" checked="checked"/>一级</label><label><input name="goodsCatLevel" type="radio" value="2"/>二级</label>
					</td>
				</tr>
				<tr>
					<td valign="top">父类：<input id="goodsCatFatherCode"
						name="goodsCatFatherCode" class="easyui-combobox"
						data-options="valueField:'goodsCatCode',textField:'goodsCatName',url:'goods/goodsAction_queryGoodsFirstLevelCat'" />
					</td>
				</tr>				
			</table>
		</form>
		<div>
			<a id="submitButton" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'" href="javascript:void(0)"
				onclick="javascript:submitForm('addform','addwindow')">提交</a> <a
				id="resetButton" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'" href="javascript:void(0)"
				onclick="javascript:resetForm('editorAdd')">重填</a> <a
				id="closeButton" class="easyui-linkbutton"
				data-options="iconCls:'icon-back'" href="javascript:void(0)"
				onclick="javascript:closeForm('addwindow')">取消</a>

		</div>
	</div>
	<div id="gridtb">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true"
			onclick="openaddwindow()">添加</a> <a href="javascript:void(0)"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true"
			onclick="openeditwindow()">修改</a> <a href="javascript:void(0)"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true"
			onclick="deletegoodscat()">删除</a>

	</div>

</body>
</html>
