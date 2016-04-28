var Advertisement = {

	"toAdd" : function() {
		window.self.location.href = contextPath + "/advertisement/toAdd.do";
	},

	"toUpdate" : function(id) {
		window.self.location.href = contextPath + "/advertisement/toUpdate.do?id=" + id;
	},

	"toApproval" : function(id) {
		window.self.location.href = contextPath + "/advertisement/toApproval.do?id=" + id;
	},

	"toManage" : function() {
		window.self.location.href = contextPath + "/advertisement/manage.do";
	},

	"approval" : function() {
		var content = UE.getEditor('content').getContent();
		if (!content) {
			$.messager.alert("系统提示", "内容不能为空", "warning");
			return false;
		}
		$("#form").form('submit', {
			url : contextPath + "/advertisement/update.do?json",
			onSubmit : function(param) {
				param.content = content;
				return $("#form").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					Advertisement.toManage();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"add" : function() {
		var content = UE.getEditor('content').getContent();
		if (!content) {
			$.messager.alert("系统提示", "内容不能为空", "warning");
			return false;
		}
		$("#form").form('submit', {
			url : contextPath + "/advertisement/add.do?json",
			onSubmit : function(param) {
				param.content = content;
				return $("#form").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					Advertisement.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"update" : function() {
		var content = UE.getEditor('content').getContent();
		if (!content) {
			$.messager.alert("系统提示", "内容不能为空", "warning");
			return false;
		}
		$("#form").form('submit', {
			url : contextPath + "/advertisement/update.do?json",
			onSubmit : function(param) {
				param.content = content;
				return $("#form").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					Advertisement.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"toList" : function() {
		window.self.location.href = contextPath + "/advertisement/listMine.do";
	},

	"backList" : function() {
		var avoid = $("#avoid").val();
		var userAccount = $("#userAccount").val();
		if (avoid == "1") {
			window.self.location.href = contextPath + "/advertisement/view.do";
		} else if (userAccount != "") {
			Advertisement.toList();
		} else {
			window.self.location.href = contextPath + "/advertisement/manage.do";
		}
	},

	"deleteRecord" : function(id) {
		$.ajax({
			url : contextPath + "/advertisement/delete.do?json",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				id : id
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

	"batchDelete" : function() {
		var idArray = new Array();
		var selecteds = $("#table").datagrid("getSelections");
		$.each(selecteds, function(i, record) {
			idArray.push(record.id);
		});
		if (idArray.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的记录", 'warning');
			return false;
		}
		$.ajax({
			url : contextPath + "/advertisement/batchDelete.do?json",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				ids : idArray.join(",")
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

	"show" : function(id) {
		window.self.location.href = contextPath + "/advertisement/show.do?id=" + id + "&=userAccount" + $("#userAccount").val() + "&avoid=" + $("#avoid").val();
	},

	"end" : null
}