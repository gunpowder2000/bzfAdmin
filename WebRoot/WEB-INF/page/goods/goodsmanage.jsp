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

<title>商品信息管理</title>
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
	function deletegoods() {

		var slrow = $('#datagrid').datagrid("getSelected");

		if (slrow != null) {
			$.messager.confirm('确认', '是否确认删除？', function(r) {
				if (r) {

					$.ajax({
						url : "goods/goodsAction_deleteGoods",
						dataType : "json",
						data : {
							'goodsId' : slrow.goodsId
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
			$.messager.alert('警告', '请选择要删除的商品信息！', 'info');

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
				var editor = UE.getEditor('goodsDigestEdit', {
					zIndex : 20000
				});
				editor.execCommand('cleardoc');
				editor.execCommand('inserthtml', slrow.goodsDigest);
				var editor1 = UE.getEditor('goodsParamEdit', {
					zIndex : 20000
				});
				editor1.execCommand('cleardoc');
				editor1.execCommand('inserthtml', slrow.goodsParam);
				var editor2 = UE.getEditor('goodsYinianEdit', {
					zIndex : 20000
				});
				editor2.execCommand('cleardoc');
				editor2.execCommand('inserthtml', slrow.goodsYinian);
			

		} else {
			$.messager.alert('警告', '请选择要修改的商品信息！', 'info');

		}

	}

	function openaddwindow(rowIndex, rowData) {
		$('#addwindow').window('open');
		var editor = UE.getEditor('goodsDigestAdd', {
			zIndex : 20000
		});
		editor.execCommand('cleardoc');
		var editor1 = UE.getEditor('goodsParamAdd', {
			zIndex : 20000
		});
		editor1.execCommand('cleardoc');
		var editor2 = UE.getEditor('goodsYinianAdd', {
			zIndex : 20000
		});
		editor2.execCommand('cleardoc');
	}

	function resetForm(editorparam) {
		if (editorparam == 'editor') {
			var editor = UE.getEditor(editorparam);
			editor.destroy();
			$('#editform').form('load', $('#datagrid').datagrid('getSelected'));
			UE.getEditor('editor', {
				zIndex : 20000
			});
			editor.reset();
		} else {
			var editor = UE.getEditor(editorparam);
			editor.destroy();
			$('#addform').form('reset');
			UE.getEditor(editorparam, {
				zIndex : 20000
			});
			editor.reset();

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
			var editor = UE.getEditor('editor');
			editor.destroy();
			$('#editform').form('load', $('#datagrid').datagrid('getSelected'));
			UE.getEditor('editor', {
				zIndex : 20000
			});
			editor.reset();
			$('#editwindow').window('close');
		} else {
			var editor = UE.getEditor('editorAdd');
			editor.destroy();
			$('#addform').form('reset');
			UE.getEditor('editorAdd', {
				zIndex : 20000
			});
			editor.reset();
			$('#addwindow').window('close');
		}
	}

	function setAddFile(obj) {
		$('#' + obj).submit();

	}

	$(document)
			.ready(
					function() {
						$('#uploadAddImage')
								.ajaxForm(
										{
											beforeSubmit:function(){
				                                $.messager.progress(); 
											},
											success : function(data) {
												$('#addGoodsImage').val(
														data.msg);
												$('#addImagePreview')
														.html(
																"<image height='20' src='"+data.msg+"' border='0'/>");
												$.messager.progress('close');
											}
										});
						$('#uploadEditImage')
								.ajaxForm(
										{
											beforeSubmit:function(){
				                                $.messager.progress(); 
											},
											success : function(data) {
												$('#editGoodsImage').val(
														data.msg);
												$('#editImagePreview')
														.html(
																"<image height='20' src='"+data.msg+"' border='0'/>");
												$.messager.progress('close');
											}
										});

						$('#editTab').tabs(
								{
									onSelect : function(title, index) {
									    var slrow=$('#datagrid').datagrid('getSelected');
										if (index == 0) {
											var editor = UE.getEditor(
													'goodsDigestEdit', {
														zIndex : 20000
													});
											editor.execCommand('cleardoc');
											editor.execCommand('inserthtml',
													slrow.goodsDigest);
										}
										if (index == 1) {
											var editor1 = UE.getEditor(
													'goodsParamEdit', {
														zIndex : 20000
													});
											editor1.execCommand('cleardoc');
											editor1.execCommand('inserthtml',
													slrow.goodsParam);
										}
										if (index == 2) {
											var editor2 = UE.getEditor(
													'goodsYinianEdit', {
														zIndex : 20000
													});
											editor2.execCommand('cleardoc');
											editor2.execCommand('inserthtml',
													slrow.goodsYinian);
										}
									}
								});
						$('#addTab').tabs(
								{
									onSelect : function(title, index) {
									    var slrow=$('#datagrid').datagrid('getSelected');
										if (index == 0) {
											var editor = UE.getEditor(
													'goodsDigestAdd', {
														zIndex : 20000
													});
											editor.execCommand('cleardoc');
											editor.execCommand('inserthtml',
													slrow.goodsDigest);
										}
										if (index == 1) {
											var editor1 = UE.getEditor(
													'goodsParamAdd', {
														zIndex : 20000
													});
											editor1.execCommand('cleardoc');
											editor1.execCommand('inserthtml',
													slrow.goodsParam);
										}
										if (index == 2) {
											var editor2 = UE.getEditor(
													'goodsYinianAdd', {
														zIndex : 20000
													});
											editor2.execCommand('cleardoc');
											editor2.execCommand('inserthtml',
													slrow.goodsYinian);
										}
									}
								});		

					});
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
					data-options="fit:true,singleSelect:true,collapsible:true,url:'goods/goodsAction_queryGoods',onDblClickRow:openeditwindow,toolbar:'#gridtb',  
                pagination:true,rownumbers:true">
					<thead>
						<tr>
							<th data-options="field:'goodsName'" align="center">商品名称</th>
							<th data-options="field:'goodsDigest'" align="center">商品简介</th>
							<th data-options="field:'goodsPic'" align="center">商品图片地址</th>
							<th data-options="field:'goodsCatName'" align="center">商品类别</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>

	<div id="editwindow" class="easyui-window" title="修改商品信息"
		data-options="modal:true,closed:true,iconCls:'icon-save',collapsible:false,minimizable:false,maximizable:false,resizable:false,inline:true"
		style="width:700px;height:400px;padding:10px;">
		<form id="uploadEditImage" method="post" action="upload/uploadPro"
			enctype="multipart/form-data">
			<input type="file" name="upload"
				onchange="setAddFile('uploadEditImage')"><span
				id="editImagePreview"></span>
		</form>
		<form id="editform" method="post" action="goods/goodsAction_editGoods">
			<input type="hidden" name="goodsId" />
			<table style="padding: 0pt 0pt 0pt 5px; width: auto">
				<tr>
					<td valign="top">商品名称：<input name="goodsName"
						class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td valign="top">商品图片地址：<input id="editGoodsImage"
						name="goodsPic" /></td>
				</tr>
				<tr>
					<td valign="top">商品类别：<input id="goodsCatCode"
						name="goodsCatCode" class="easyui-combobox"
						data-options="valueField:'goodsCatCode',textField:'goodsCatName',url:'goods/goodsAction_queryGoodsSecondLevelCat'" />
					</td>
				</tr>
				<tr>
					<td valign="top">
						<div id="editTab" class="easyui-tabs"
							style="width:640px;height:300px;">
							<div title="商品简介" style="padding:20px;">
								<textarea id="goodsDigestEdit" name="goodsDigest" rows="120"
									cols="80" style="width: 100%"></textarea>
							</div>
							<div title="产品参数" style="padding:20px;">
								<textarea id="goodsParamEdit" name="goodsParam" rows="120"
									cols="80" style="width: 100%"></textarea>
							</div>
							<div title="产品意念" style="padding:20px;">
								<textarea id="goodsYinianEdit" name="goodsYinian" rows="120"
									cols="80" style="width: 100%"></textarea>
							</div>
						</div>
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
	<div id="addwindow" class="easyui-window" title="添加商品信息"
		data-options="modal:true,closed:true,iconCls:'icon-save',collapsible:false,minimizable:false,maximizable:false,resizable:false,inline:true"
		style="width:700px;height:400px;padding:10px;">
		<form id="uploadAddImage" method="post" action="upload/uploadPro"
			enctype="multipart/form-data">
			上传焦点图：<input id="addFile" type="file" name="upload"
				onchange="setAddFile('uploadAddImage')"><span
				id="addImagePreview"></span>
		</form>
		<form id="addform" method="post" action="goods/goodsAction_addGoods">
			<table style="padding: 0pt 0pt 0pt 5px; width: auto">
				<tr>
					<td valign="top">商品名称：<input name="goodsName"
						class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td valign="top">商品图片地址：<input id="addGoodsImage"
						name="goodsPic" /></td>
				</tr>
				<tr>
					<td valign="top">商品类别：<input id="goodsCatCode"
						name="goodsCatCode" class="easyui-combobox"
						data-options="valueField:'goodsCatCode',textField:'goodsCatName',url:'goods/goodsAction_queryGoodsSecondLevelCat'" />
					</td>
				</tr>
				<tr>
					<td valign="top">
						<div id="addTab" class="easyui-tabs"
							style="width:640px;height:300px;">
							<div title="商品简介" style="padding:20px;">
								<textarea id="goodsDigestAdd" name="goodsDigest" rows="120"
									cols="80" style="width: 100%"></textarea>
							</div>
							<div title="产品参数" style="padding:20px;">
								<textarea id="goodsParamAdd" name="goodsParam" rows="120"
									cols="80" style="width: 100%"></textarea>
							</div>
							<div title="产品意念" style="padding:20px;">
								<textarea id="goodsYinianAdd" name="goodsYinian" rows="120"
									cols="80" style="width: 100%"></textarea>
							</div>
						</div>
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
			onclick="deletegoods()">删除</a>

	</div>

</body>
</html>
