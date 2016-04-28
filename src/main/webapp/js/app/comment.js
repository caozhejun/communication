var Comment = {

	"add" : function() {
		var content = $("#commentContent").val();
		if (!content || content.trim() == "") {
			$.messager.alert("系统提示", "内容不能为空", "warning");
			return false;
		}
		$.ajax({
			url : contextPath + "/comment/add.do",
			data : {
				"content" : content,
				"masterId" : $("#id").val(),
				"type" : $("#type").val()
			},
			dataType : "json",
			type : "post",
			success : function(data) {
				if (data.code == 200) {
					Comment.refreshComment();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"refreshComment" : function() {
		window.self.location.href = contextPath + "/communication/show.do?id=" + $("#id").val();
	},

	"end" : null
}