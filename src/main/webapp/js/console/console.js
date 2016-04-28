var Console = {

	"execute" : function() {
		var userName = $("#userName").val();
		var password = $("#password").val();
		var script = $("#script").val();
		if (userName == "") {
			popup.alert("用户名不能为空", "错误提示");
			return false;
		}
		if (password == "") {
			popup.alert("密码不能为空", "错误提示");
			return false;
		}
		if (script == "") {
			popup.alert("脚本不能为空", "错误提示");
			return false;
		}
		$.ajax({
			"url" : contextPath + "/console/execute.do",
			"data" : {
				"userName" : userName,
				"password" : password,
				"script" : script
			},
			"type" : "POST",
			"async" : true,
			"dataType" : "json",
			"success" : function(data) {
				if (data.code == 200) {
					$("#tip").html("执行成功:<br/>" + data.data);
				} else {
					$("#tip").html("执行失败:<br/>" + data.msg);
				}
			},
			"error" : function() {
				popup.alert("脚本执行报错", "错误提示");
			}
		});
	},

	"end" : null
};