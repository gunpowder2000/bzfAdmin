/**
 * 重新加载列表数据
 * @param tableId
 */
function reloadGrid(tableId){
	jQuery("#"+tableId).datagrid('reload');
}
/**
 * 隐藏某一列
 * @param tableId 表的ID
 * @param field 列的ID
 */
function hideField(tableId,field){
	jQuery('#'+tableId).datagrid('hideColumn', field);
}

/**
 * 显示分页数据信息
 * @param tableId table的ID
 */
function displayMsg(tableId){
	jQuery('#'+tableId).datagrid('getPager').pagination({displayMsg:'当前显示从{from}到{to}共{total}记录'});
}
/**
 * 获取用户选择的所有行数据
 * 若是为空，则提示用户选择数据
 * 若是不为空，则返回用户选择的行
 * @param tableId table的ID
 * @param field table的列名，获取用户选择的field列所有的值
 */
function getSelections(tableId,field){
	var rows = jQuery('#'+tableId).datagrid('getSelections');
	if (rows){
		if(rows.length>0){
			if(field){
				var ids = [];
			    for (var i = 0; i < rows.length; i++) {
			        ids.push(rows[i][field]);
			    }
			    return ids;
			}else{
				return rows;
			}
		}
	}
	return;
}

function getSelectedArr() {
    var ids = [];
    var rows = grid.datagrid('getSelections');
    for (var i = 0; i < rows.length; i++) {
        ids.push(rows[i].ID);
    }
    return ids;
}


/**
 * ajax调用后台数据，替换当前页面的内容
 * @param ajaxOptions  ajax请求参数对象
 * @param targetId 要替换的ID
 * @param url 请求后天的URL地址
 * @param params 传到后台的参数
 * @param callback 回调函数
 * @return
 */
function loadAjax(ajaxOptions) {
	jQuery("#" + ajaxOptions.targetId).load(ajaxOptions.url, ajaxOptions.params, ajaxOptions.callback);
}
/**
 * 隐藏
 * @param divId 可以使DIV，table，tr，td等等标签
 */
function hide(divId){
	jQuery("#"+divId).hide();
}
/**
 * 显示
 * @param divId 可以使DIV，table，tr，td等等标签
 */
function show(divId){
	jQuery("#"+divId).show();
}
/**
 * 原始ajax提交。适用于删除元素等操作
 * @param ajaxOptions   ajax请求参数对象
 * @return
 */
function ajax(ajaxOptions) {
	jQuery.ajax({
		type : ajaxOptions.type!=null?ajaxOptions.type:"POST",
		url : ajaxOptions.url,
		dataType : ajaxOptions.dataType!=null?ajaxOptions.dataType:"text",
		data : ajaxOptions.params,
		success : ajaxOptions.success!=null?ajaxOptions.success:ajaxSuccess,
		error : ajaxOptions.error!=null?ajaxOptions.error:ajaxError
	});
}
/**
 * ajax请求成功
 * @param responseText   后台返回文本
 * @param statusText     状态文本，包括"timeout","success", "error", "notmodified" 和 "parsererror"。
 * @param xhr     ajax请求对象
 * @param $form   form对象
 * @return
 */
function ajaxSuccess(responseText, statusText, xhr, $form) {
	if(responseText==null||responseText=='')
		alertEasy('提示',"操作成功!",1);
	else
		alertEasy('提示',responseText,1);
//	window.location.reload();
}
/**
 * ajax请求失败
 * @param XMLHttpRequest  ajax请求对象
 * @param error     ajax error对象
 * @param thrownError      异常信息
 * @return
 */
function ajaxError(XMLHttpRequest, error, thrownError) {
	alertEasy(null,"操作失败，请稍后再试!",1);
}
/**
 * 提示框
 * @param message 信息提示内容
 * @param title 提示框标题
 * @param type 1:alert,2:message,3:confirm
 */
function alertEasy(title,message,type,confirmFun){
	if(title==null)
		title='提示';
	if(type==1){
		jQuery.messager.alert(title,message);
	}else if(type==2){
		jQuery.messager.show({  
	        title:title,  
	        msg:message,  
	        showType:'fade',  
	        style:{  
	            right:'',  
	            bottom:''  
	        }  
	    });  
	}else if(type==3){
		/**
		 * confirmFun是一个函数，eg:function(r){  
	            if (r){  
	                alert('confirmed: '+r);  
	            }  
	        } 
		 */
		jQuery.messager.confirm(title, message, confirmFun);  
	}
}
/**
 * 用于文件上传的格式验证
 * fileType 数组，格式类型
 */
function checkImgType(fileType,fileURL){
	var right_type=fileType;
    var right_typeLen=right_type.length;
    var imgUrl=fileURL.toLowerCase();
    var postfixLen=imgUrl.length;
    var len4=imgUrl.substring(postfixLen-4,postfixLen);
    var len5=imgUrl.substring(postfixLen-5,postfixLen);
    for (i=0;i<right_typeLen;i++)
    {
        if((len4==right_type[i])||(len5==right_type[i]))
        {
            return true;
        }
    }
}
/**
 * 时间格式化
 * @param val 时间值
 * @param type true：显示年月日，时分秒，默认（不传值）只显示年月日
 * @returns
 */
function formatDate(val,type){
	if(val==null||val=='')
		return '';
	var date = new Date(val.time);
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	if(type!=null&&type==true){
		var h=date.getHours();
		var min=date.getMinutes();
		var s=date.getSeconds();
		return y+'-'+formatDateNum(m)+'-'+formatDateNum(d)+" "+formatDateNum(h)+":"+formatDateNum(min)+":"+formatDateNum(s); 
	}else
		return y+'-'+formatDateNum(m)+'-'+formatDateNum(d);
}
/**
 * 时间格式化，将小于10的数字签名加0
 * @param va
 * @returns
 */
function formatDateNum(va){
	if(va==null||va=='')
		return '';
	return va<10?('0'+va):va;
}