var Role = {

	"toAdd" : function() {
		window.self.location.href = contextPath + "/role/toAdd.do";
	},

	"add" : function() {
		$("#roleForm").form('submit', {
			url : contextPath + "/role/add.do?json",
			onSubmit : function() {
				return $("#roleForm").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					Role.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"batchDelete" : function() {
		var selectedroleNames = ExtGridUtil.getValue("checkbox", null, "roleList", "name");
		if (selectedroleNames == "") {
			$.messager.alert("系统提示", "请选择要删除的角色", 'warning');
			return false;
		}
		$.ajax({
			url : contextPath + "/role/batchDelete.do?json",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				roleNames : selectedroleNames
			},
			success : function(data) {
				if (data.code == 200) {
					ExtGridUtil.refreshStore(null, "roleList", null, 10);
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
		window.self.location.href = contextPath + "/role/toUpdate.do?name=" + encodeURIComponent(encodeURI(name));
	},

	"update" : function() {
		$("#roleForm").form('submit', {
			url : contextPath + "/role/update.do?json",
			onSubmit : function() {
				return $("#roleForm").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					Role.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"deleteRole" : function(name) {
		$.ajax({
			url : contextPath + "/role/batchDelete.do?json",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				roleNames : name
			},
			success : function(data) {
				if (data.code == 200) {
					ExtGridUtil.refreshStore(null, "roleList", null, 10);
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

	"toAssignPermission" : function(roleName) {
		window.self.location.href = contextPath + "/role/toAssignPermission.do?roleName=" + roleName;
	},

	"assignPermission" : function() {
		$("#roleForm").form('submit', {
			url : contextPath + "/role/assignPermission.do?json",
			onSubmit : function() {
				var permissionArray = new Array();
				$("input[name='permissionName']:checked").each(function() {
					permissionArray.push($(this).val());
				});
				if (permissionArray.length > 0) {
					return true;
				}
				$.messager.alert("系统提示", "请选择要分配的权限", 'warning');
				return false;
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					Role.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"toList" : function() {
		window.self.location.href = contextPath + "/role/list.do";
	},

	"end" : null
};