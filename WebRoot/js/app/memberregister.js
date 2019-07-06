function memberregisterresetForm() {
	$('#memberregisterregistform').form('clear');
}
function memberregistersubmitForm() {
	$('#memberregisterregistform').form('submit', {
		success : function(data) {
			var data = eval('(' + data + ')');
			alert(data.msg);

		}
	});
}