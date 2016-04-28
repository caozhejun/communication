var User = {
		
	"logout" : function() {
		window.self.location.href = contextPath + "/login/logout.do";
	},

	"toAdd" : function() {
		window.self.location.href = contextPath + "/user/toAdd.do";
	},

	"add" : function() {
		$("#userForm").form('submit', {
			url : contextPath + "/user/add.do?json",
			onSubmit : function() {
				return $("#userForm").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					User.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"resetPwd" : function(account) {
		$.ajax({
			url : contextPath + "/user/resetPwd.do?json",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				account : account
			},
			success : function(data) {
				if (data.code == 200) {
					$.messager.alert("系统提示", "重置密码成功", 'info');
				} else {
					$.messager.alert("系统错误", data.msg, 'error');
				}
			},
			error : function() {
				$.messager.alert("系统错误", "重置密码失败", 'error');
			}
		});
	},

	"batchDelete" : function() {
		var selectedAccounts = ExtGridUtil.getValue("checkbox", null, "userList", "account");
		if (selectedAccounts == "") {
			$.messager.alert("系统提示", "请选择要删除的用户", 'warning');
			return false;
		}
		$.ajax({
			url : contextPath + "/user/batchDelete.do?json",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				accounts : selectedAccounts
			},
			success : function(data) {
				if (data.code == 200) {
					ExtGridUtil.refreshStore(null, "userList", null, 10);
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

	"toUpdate" : function(account) {
		window.self.location.href = contextPath + "/user/toUpdate.do?account=" + encodeURIComponent(encodeURI(account));
	},

	"update" : function() {
		$("#userForm").form('submit', {
			url : contextPath + "/user/update.do?json",
			onSubmit : function() {
				return $("#userForm").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					User.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"deleteAccount" : function(account) {
		$.ajax({
			url : contextPath + "/user/batchDelete.do?json",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				accounts : account
			},
			success : function(data) {
				if (data.code == 200) {
					ExtGridUtil.refreshStore(null, "userList", null, 10);
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

	"toAssignRole" : function(account) {
		window.self.location.href = contextPath + "/user/toAssignRole.do?account=" + account;
	},

	"assignRole" : function() {
		$("#roleForm").form('submit', {
			url : contextPath + "/user/assignRole.do?json",
			onSubmit : function() {
				var roleArray = new Array();
				$("input[name='roleName']:checked").each(function() {
					roleArray.push($(this).val());
				});
				if (roleArray.length > 0) {
					return true;
				}
				$.messager.alert("系统提示", "请选择要分配的角色", 'warning');
				return false;
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					User.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"toList" : function() {
		window.self.location.href = contextPath + "/user/list.do";
	},

	"toModifyPwd" : function() {
		ExtWindowUtil.openWinWithIframe(contextPath + "/user/toModifyPwd.do", "修改密码", "modifyPwdWin", 440, 200, true);
	},

	"modifyPwd" : function() {
		$("#userForm").form('submit', {
			url : contextPath + "/user/modifyPwd.do?json",
			onSubmit : function() {
				return $("#userForm").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					User.cancelModifyPwd();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"cancelModifyPwd" : function() {
		parent.ExtWindowUtil.closeWindow("modifyPwdWin");
	},

	"toModifyInfo" : function() {
		ExtWindowUtil.openWinWithIframe(contextPath + "/user/toModifyInfo.do", "修改个人信息", "modifyInfoWin", 400, 240, true);
	},

	"modifyInfo" : function() {
		$("#userForm").form('submit', {
			url : contextPath + "/user/modifyInfo.do?json",
			onSubmit : function() {
				return $("#userForm").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					User.cancelModifyInfo();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"cancelModifyInfo" : function() {
		parent.ExtWindowUtil.closeWindow("modifyInfoWin");
	},

	"cancelRegistry" : function() {
		$('#registryDiv').window('close');
	},

	"registry" : function() {
		var pwd = $("#pwd").val();
		var confirmPwd = $("#confirmPwd").val();
		var account = $("#account").val();
		if (!pwd) {
			$.messager.alert("系统错误", "密码不能为空", "error");
			return false;
		}
		if (!confirmPwd) {
			$.messager.alert("系统错误", "确认密码不能为空", "error");
			return false;
		}
		if (pwd != confirmPwd) {
			$.messager.alert("系统错误", "密码输入不一致，请重新输入", "error");
			return false;
		}
		$.ajax({
			url : contextPath + "/user/checkAccount.do",
			data : {
				"account" : account
			},
			type : "get",
			dataType : "json",
			success : function(data) {
				if (data.data) {
					$.messager.alert("系统错误", "账号已经存在", "error");
					return false;
				} else {
					$("#registryForm").form('submit', {
						url : contextPath + "/user/registry.do?json",
						onSubmit : function() {
							return $("#registryForm").form("validate");
						},
						success : function(data) {
							var data = eval('(' + data + ')');
							if (data.code == 200) {
								$.messager.alert("系统提示", "注册成功，等待管理员审批之后就可以正常登陆，请您耐心等待...", "info");
								User.cancelRegistry();
							} else {
								$.messager.alert("系统错误", data.msg, "error");
							}
						}
					});
				}
			}
		});

	},

	"batchAproved" : function() {
		var selectedAccounts = ExtGridUtil.getValue("checkbox", null, "userList", "account");
		if (selectedAccounts == "") {
			$.messager.alert("系统提示", "请选择要审批同意的用户", 'warning');
			return false;
		}
		$.ajax({
			url : contextPath + "/user/batchUpdateStatus.do?json&status=2",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				accounts : selectedAccounts
			},
			success : function(data) {
				if (data.code == 200) {
					ExtGridUtil.refreshStore(null, "userList", null, 10);
					$.messager.alert("系统提示", "批量审批同意成功", 'info');
				} else {
					$.messager.alert("系统错误", data.msg, 'error');
				}
			},
			error : function() {
				$.messager.alert("系统错误", "批量审批同意失败", 'error');
			}
		});
	},

	"batchRefuse" : function() {
		var selectedAccounts = ExtGridUtil.getValue("checkbox", null, "userList", "account");
		if (selectedAccounts == "") {
			$.messager.alert("系统提示", "请选择要审批拒绝的用户", 'warning');
			return false;
		}
		$.ajax({
			url : contextPath + "/user/batchUpdateStatus.do?json&status=3",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				accounts : selectedAccounts
			},
			success : function(data) {
				if (data.code == 200) {
					ExtGridUtil.refreshStore(null, "userList", null, 10);
					$.messager.alert("系统提示", "批量审批拒绝成功", 'info');
				} else {
					$.messager.alert("系统错误", data.msg, 'error');
				}
			},
			error : function() {
				$.messager.alert("系统错误", "批量审批拒绝失败", 'error');
			}
		});
	},

	"approved" : function(account) {
		$.ajax({
			url : contextPath + "/user/batchUpdateStatus.do?json&status=2",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				accounts : account
			},
			success : function(data) {
				if (data.code == 200) {
					ExtGridUtil.refreshStore(null, "userList", null, 10);
					$.messager.alert("系统提示", "审批同意成功", 'info');
				} else {
					$.messager.alert("系统错误", data.msg, 'error');
				}
			},
			error : function() {
				$.messager.alert("系统错误", "审批同意失败", 'error');
			}
		});
	},

	"refuse" : function(account) {
		$.ajax({
			url : contextPath + "/user/batchUpdateStatus.do?json&status=3",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				accounts : account
			},
			success : function(data) {
				if (data.code == 200) {
					ExtGridUtil.refreshStore(null, "userList", null, 10);
					$.messager.alert("系统提示", "审批拒绝成功", 'info');
				} else {
					$.messager.alert("系统错误", data.msg, 'error');
				}
			},
			error : function() {
				$.messager.alert("系统错误", "审批拒绝失败", 'error');
			}
		});
	},

	"search" : function() {

	},

	"end" : null

};