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

<title>新闻信息管理</title>
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
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script>    
<script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
    function deletework(){
    
      var slrow = $('#datagrid').datagrid("getSelected");

		if (slrow != null) {
		  $.messager.confirm('确认', '是否确认删除？', function(r){
		    if (r){
		
		
		    $.ajax({
	          url:"news/newsAction_deleteNews",
	          dataType:"json",
	          data:{'newsId':slrow.newsId},
	          success:function(msg){	       
	            $.messager.alert('提示',msg.msg,'info'); 
	            $('#datagrid').datagrid('reload');
	          },
	          error:function(){
	            $.messager.alert('提示','删除失败','info');
	          }
	        });	
	        }
	         
	        }); 
						
		} else {
		    $.messager.alert('警告','请选择要删除的新闻信息！','info');
			
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
			var editor=UE.getEditor('editor',{zIndex:20000});
			editor.execCommand('cleardoc');
			editor.execCommand('inserthtml',slrow.newsContent);
						
		} else {
		    $.messager.alert('警告','请选择要修改的新闻信息！','info');
			
		}

	}
	function openaddwindow(rowIndex, rowData) {			    
			$('#addwindow').window('open');
			var editor=UE.getEditor('editorAdd',{zIndex:20000});
			editor.execCommand('cleardoc');
	}

	function resetForm(editorparam) {
	    if (editorparam=='editor'){
	      var editor=UE.getEditor(editorparam);
	      editor.destroy();
		  $('#editform').form('load', $('#datagrid').datagrid('getSelected'));
		  UE.getEditor('editor',{zIndex:20000});
		  editor.reset();
		}else{
		  var editor=UE.getEditor(editorparam);
	      editor.destroy();
		  $('#addform').form('reset');
		  UE.getEditor(editorparam,{zIndex:20000});
		  editor.reset();
		
		}
	}
	function submitForm(form,window) {	    
		$('#'+form).form('submit', {
			success : function(data) {
				var data = eval('(' + data + ')');
				$('#datagrid').datagrid('reload');
				$.messager.alert('返回结果',data.msg,'info');				
				$('#'+window).window('close');

			}
		});
	}

	function closeForm(window) {
	    if (window=='editwindow'){
	      var editor=UE.getEditor('editor');
	      editor.destroy();
		  $('#editform').form('load', $('#datagrid').datagrid('getSelected'));
		  UE.getEditor('editor',{zIndex:20000});
		  editor.reset();
		  $('#editwindow').window('close');
		}else{
		  var editor=UE.getEditor('editorAdd');
	      editor.destroy();
		  $('#addform').form('reset');
		  UE.getEditor('editorAdd',{zIndex:20000});
		  editor.reset();
		  $('#addwindow').window('close');
		}
	}	
	
	function setAddFile(obj){	  
	  $('#'+obj).submit();
	
	}
	
	$(document).ready(function() {
        $('#uploadAddImage').ajaxForm({
			beforeSubmit:function(){
				$.messager.progress({text:'正在上传，请稍等……'}); 
			},
            success:function(data){
            $('#addNewsImage').val(data.msg);
            $('#addImagePreview').html("<image height='20' src='"+data.msg+"' border='0'/>");      
            $.messager.progress('close');  			
            }
        });
        $('#uploadEditImage').ajaxForm({
			beforeSubmit:function(){
				$.messager.progress({text:'正在上传，请稍等……'}); 
			},
            success:function(data){
            $('#editNewsImage').val(data.msg);
            $('#editImagePreview').html("<image height='20' src='"+data.msg+"' border='0'/>");
			$.messager.progress('close'); 
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
					href="javascript:void(0)" onclick="javascript:searchadvertisement()">搜索</a>
			</div>

			<div data-options="region:'center'" style="padding:5px">
				<table id="datagrid" class="easyui-datagrid"
					data-options="fit:true,singleSelect:true,collapsible:true,url:'news/newsAction_queryNews',onDblClickRow:openeditwindow,toolbar:'#gridtb',  
                pagination:true,rownumbers:true">
					<thead>
						<tr>
							<th data-options="field:'newsTitle'" align="center">新闻标题</th>
							<th data-options="field:'newsDigestImage',hidden:true" align="center">新闻摘要图地址</th>
							<th data-options="field:'newsDigest'" align="center">新闻摘要</th>
							<th data-options="field:'newsContent'" align="center">新闻内容</th>	
							<th data-options="field:'newsDate'" align="center">新闻时间</th>										
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>

	<div id="editwindow" class="easyui-window" title="修改新闻信息"
		data-options="modal:true,closed:true,iconCls:'icon-save',collapsible:false,minimizable:false,maximizable:false,resizable:false,inline:true"
		style="width:700px;height:400px;padding:10px;">
		<form id="uploadEditImage" method="post" action="upload/uploadPro" enctype="multipart/form-data" style="display:none">
			<input type="file" name="upload"onchange="setAddFile('uploadEditImage')"><span id="editImagePreview"></span>
		</form>
		<form id="editform" method="post" action="news/newsAction_editNews">
		    <input type="hidden" name="newsId"/>
			<table style="padding: 0pt 0pt 0pt 5px; width: auto">
			    <tr>
					<td valign="top">					 
					 新闻标题：<input name="newsTitle" class="easyui-validatebox" data-options="required:true" />
					</td>
				</tr>	
				<tr>
					<td valign="top">					 
					 新闻摘要图地址：<input id="editNewsImage" name="newsDigestImage" />
					</td>
				</tr>
				<tr>
					<td valign="top">					 
					 新闻摘要：<input name="newsDigest" class="easyui-validatebox" />
					</td>
				</tr>	
				<tr>
					<td valign="top">					 
					 新闻内容：<br/><textarea id="editor" name="newsContent"
									 rows="120" cols="80" style="width: 100%"></textarea>
					</td>
				</tr>
			</table>
		</form>
		<div>
			<a id="submitButton" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'" href="javascript:void(0)"
				onclick="javascript:submitForm('editform','editwindow')">提交</a> <a id="resetButton"
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)" onclick="javascript:resetForm('editor')">重填</a> <a
				id="closeButton" class="easyui-linkbutton"
				data-options="iconCls:'icon-back'" href="javascript:void(0)"
				onclick="javascript:closeForm('editwindow')">取消</a>

		</div>
	</div>
	<div id="addwindow" class="easyui-window" title="添加新闻信息"
		data-options="modal:true,closed:true,iconCls:'icon-save',collapsible:false,minimizable:false,maximizable:false,resizable:false,inline:true"
		style="width:700px;height:400px;padding:10px;">
		<form id="uploadAddImage" method="post" action="upload/uploadPro" enctype="multipart/form-data" >
			上传焦点图：<input id="addFile" type="file" name="upload" onchange="setAddFile('uploadAddImage')"><span id="addImagePreview"></span>
		</form>
		<form id="addform" method="post" action="news/newsAction_addNews">		    
			<table style="padding: 0pt 0pt 0pt 5px; width: auto">
			    <tr>
					<td valign="top">					 
					 新闻标题：<input name="newsTitle" class="easyui-validatebox" data-options="required:true" />
					</td>
				</tr>	
				<tr>
					<td valign="top">					 
					 新闻摘要图地址：<input id="addNewsImage" name="newsDigestImage" />					 
					</td>
				</tr>
				<tr>
					<td valign="top">					 
					 新闻摘要：<input name="newsDigest" class="easyui-validatebox" />
					</td>
				</tr>	
				<tr>
					<td valign="top">					 
					 新闻内容：
					 <textarea id="editorAdd" name="newsContent"
									 rows="120" cols="80" style="width: 100%"></textarea>
					</td>
				</tr>			
			</table>
		</form>
		<div>
			<a id="submitButton" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'" href="javascript:void(0)"
				onclick="javascript:submitForm('addform','addwindow')">提交</a> <a id="resetButton"
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)" onclick="javascript:resetForm('editorAdd')">重填</a> <a
				id="closeButton" class="easyui-linkbutton"
				data-options="iconCls:'icon-back'" href="javascript:void(0)"
				onclick="javascript:closeForm('addwindow')">取消</a>

		</div>
	</div>
	<div id="gridtb">
	    <a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true"
			onclick="openaddwindow()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true"
			onclick="openeditwindow()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true"
			onclick="deletework()">删除</a>

	</div>

</body>
</html>
