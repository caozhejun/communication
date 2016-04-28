var Buss = {

	"toAdd" : function() {
		window.self.location.href = contextPath + "/buss/toAdd.do";
	},

	"add" : function() {
		$("#bussForm").form('submit', {
			url : contextPath + "/buss/add.do?json",
			onSubmit : function() {
				return $("#bussForm").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					Buss.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"batchDelete" : function() {
		var selectedBussNames = ExtGridUtil.getValue("checkbox", null, "bussList", "name");
		if (selectedBussNames == "") {
			$.messager.alert("系统提示", "请选择要删除的业务", 'warning');
			return false;
		}
		$.ajax({
			url : contextPath + "/buss/batchDelete.do?json",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				names : selectedBussNames
			},
			success : function(data) {
				if (data.code == 200) {
					ExtGridUtil.refreshStore(null, "bussList", null, 10);
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
		window.self.location.href = contextPath + "/buss/toUpdate.do?name=" + encodeURIComponent(encodeURI(name));
	},

	"update" : function() {
		$("#bussForm").form('submit', {
			url : contextPath + "/buss/update.do?json",
			onSubmit : function() {
				return $("#bussForm").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					Buss.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"deleteBuss" : function(name) {
		$.ajax({
			url : contextPath + "/buss/batchDelete.do?json",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				names : name
			},
			success : function(data) {
				if (data.code == 200) {
					ExtGridUtil.refreshStore(null, "bussList", null, 10);
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

	"showBuss" : function(name) {
		$.ajax({
			url : contextPath + "/buss/get.do?json",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				name : name
			},
			success : function(data) {
				if (data.code == 200) {
					var html = "<textarea rows=\"30\" cols=\"70\" readonly=\"readonly\">" + data.data.script + "</textarea>";
					ExtWindowUtil.openWinwithHtml(html, data.data.name, "scriptWin", 600, 500, true);
				} else {
					$.messager.alert("系统错误", data.msg, 'error');
				}
			},
			error : function() {
				$.messager.alert("系统错误", "获取业务失败", 'error');
			}
		});
	},

	"testBuss" : function(name) {
		$.messager.prompt('测试脚本', '请输入参数：(参数格式为key1=value1&key2=value2)', function(r) {
			$.ajax({
				url : contextPath + "/buss/execute.do?json&scriptName=" + name,
				type : "post",
				dataType : "json",
				async : true,
				data : r,
				success : function(data) {
					if (data.code == 200) {
						$.messager.alert("系统提示", "执行结果为" + data.data, 'info');
					} else {
						$.messager.alert("系统错误", data.msg, 'error');
					}
				},
				error : function() {
					$.messager.alert("系统错误", "执行脚本失败", 'error');
				}
			});
		});
	},

	"toList" : function() {
		window.self.location.href = contextPath + "/buss/list.do";
	},

	"end" : null

};