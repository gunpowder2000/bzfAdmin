function membermanagesearchmember() {
	$('#membermanagedatagrid').datagrid('load', {
		keyword : $('#membermanagekey').attr('value')
	});
}

function membermanageopeneditwindow(rowIndex, rowData) {

	$('#membermanageeditwindow').window('open');
	$('#membermanageeditform').form('load', rowData);
	$('#membermanagecity').combobox('reload',
			'district/district_getCity?disid=' + rowData.province);
	$('#membermanagedistrict').combobox('reload',
			'district/district_getDistrict?disid=' + rowData.city);

}

function membermanageresetForm() {
	$('#membermanageeditform').form('load',
			$('#membermanagedatagrid').datagrid('getSelected'));
}
function membermanagesubmitForm() {
	$('#membermanageeditform').form('submit', {
		success : function(data) {
			var data = eval('(' + data + ')');
			alert(data.msg);

		}
	});
}

function membermanagecloseForm() {
	$('#membermanageeditform').form('load',
			$('#membermanagedatagrid').datagrid('getSelected'));
	$('#membermanageeditwindow').window('close');
}