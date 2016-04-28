var InstallPackage = {

	"toAdd" : function() {
		window.self.location.href = contextPath + "/installPackage/toAdd.do";
	},

	"toUpdate" : function(installVersion) {
		window.self.location.href = contextPath + "/installPackage/toUpdate.do?installVersion=" + installVersion;
	},

	"batchDelete" : function() {
		var versionArray = new Array();
		var selectedPackage = $("#table").datagrid("getSelections");
		$.each(selectedPackage, function(i, p) {
			versionArray.push(p.installVersion);
		});
		if (versionArray.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的安装包", 'warning');
			return false;
		}
		$.ajax({
			url : contextPath + "/installPackage/batchDelete.do?json",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				versions : versionArray.join(",")
			},
			success : function(data) {
				if (data.code == 200) {
					$("#table").datagrid("load");
					$.messager.alert("系统提示", "删除成功", 'info');
				} else {
					$.messager.alert("系统错误", data.msg, 'error');
				}
			},
			error : function() {
				$.messager.alert("系统错误", "删除失败", 'error');
			}
		});
	},

	"deleteRecord" : function(installVersion) {
		$.ajax({
			url : contextPath + "/installPackage/batchDelete.do?json",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				versions : installVersion
			},
			success : function(data) {
				if (data.code == 200) {
					$("#table").datagrid("load");
					$.messager.alert("系统提示", "删除成功", 'info');
				} else {
					$.messager.alert("系统错误", data.msg, 'error');
				}
			},
			error : function() {
				$.messager.alert("系统错误", "删除失败", 'error');
			}
		});
	},

	"add" : function() {
		var file = $("#file").val();
		if (file == "") {
			$.messager.alert("系统提示", "请选择安装包", 'warning');
			return false;
		}
		var fileType = file.substring(file.lastIndexOf('.') + 1, file.length).toLowerCase();
		if (fileType != "apk") {
			$.messager.alert("系统提示", "请选择正确的安装包", 'warning');
			return false;
		}
		if (!$("#addForm").form("validate")) {
			return false;
		}
		$("#addForm").attr("action", contextPath + "/installPackage/add.do");
		$("#addForm").submit();
	},

	"update" : function() {
		$("#updateForm").form('submit', {
			url : contextPath + "/installPackage/update.do?json",
			onSubmit : function() {
				return $("#updateForm").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					InstallPackage.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"updateNewest" : function(installVersion) {
		$.ajax({
			url : contextPath + "/installPackage/updateNewest.do?json",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				version : installVersion
			},
			success : function(data) {
				if (data.code == 200) {
					$("#table").datagrid("reload");
					$.messager.alert("系统提示", "设置成功", 'info');
				} else {
					$.messager.alert("系统错误", data.msg, 'error');
				}
			},
			error : function() {
				$.messager.alert("系统错误", "设置失败", 'error');
			}
		});
	},

	"toList" : function() {
		window.self.location.href = contextPath + "/installPackage/list.do";
	},

	"end" : null

};