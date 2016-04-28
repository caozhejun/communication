var Permission = {

	"toAdd" : function() {
		window.self.location.href = contextPath + "/permission/toAdd.do";
	},

	"add" : function() {
		$("#permissionForm").form('submit', {
			url : contextPath + "/permission/add.do?json",
			onSubmit : function() {
				return $("#permissionForm").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					Permission.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"batchDelete" : function() {
		var selectedpermissionNames = ExtGridUtil.getValue("checkbox", null, "permissionList", "name");
		if (selectedpermissionNames == "") {
			$.messager.alert("系统提示", "请选择要删除的权限", 'warning');
			return false;
		}
		$.ajax({
			url : contextPath + "/permission/batchDelete.do?json",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				permissionNames : selectedpermissionNames
			},
			success : function(data) {
				if (data.code == 200) {
					ExtGridUtil.refreshStore(null, "permissionList", null, 10);
					$.messager.alert("系统提示", "批量删除成功", 'info');
				} else {
					$.messager.alert("系统错误", data.msg, 'error');
				}
			},
			error : function() {
				$.messager.alert("系统错误", "批量删除失败", 'error');
			}
		});
	},

	"toUpdate" : function(name) {
		window.self.location.href = contextPath + "/permission/toUpdate.do?name=" + encodeURIComponent(encodeURI(name));
	},

	"update" : function() {
		$("#permissionForm").form('submit', {
			url : contextPath + "/permission/update.do?json",
			onSubmit : function() {
				return $("#permissionForm").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					Permission.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"deletePermission" : function(name) {
		$.ajax({
			url : contextPath + "/permission/batchDelete.do?json",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				permissionNames : name
			},
			success : function(data) {
				if (data.code == 200) {
					ExtGridUtil.refreshStore(null, "permissionList", null, 10);
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

	"toList" : function() {
		window.self.location.href = contextPath + "/permission/list.do";
	},

	"end" : null
};