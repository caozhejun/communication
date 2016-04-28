$(document).ready(function() {

	// 全选
	$("#checkAll").click(function() {
		var checked = $("#checkAll").is(":checked");
		$("table").find("[type=checkbox]").not("#checkAll").each(function() {
			if (checked) {
				$(this).attr("checked", "checked");
			} else {
				$(this).removeAttr("checked");
			}
		});
	});

	// 单选行点击事件
	$("table").find("tbody > tr").click(function() {
		$(this).find("[type=radio]").click();
	}).find("input[type=radio]").click(function(event) {
		event.stopPropagation();
	});

	// 复选行点击事件
	$("table").find("tbody > tr").click(function() {
		$(this).find("[type=checkbox]").click();
	}).find("input[type=checkbox]").click(function(event) {
		event.stopPropagation();
	});
});