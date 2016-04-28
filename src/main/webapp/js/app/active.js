var Active = {

	"toAdd" : function() {
		window.self.location.href = contextPath + "/active/toAdd.do";
	},

	"toUpdate" : function(id) {
		window.self.location.href = contextPath + "/active/toUpdate.do?id=" + id;
	},

	"add" : function() {
		var content = UE.getEditor('content').getContent();
		if (!content) {
			$.messager.alert("系统提示", "内容不能为空", "warning");
			return false;
		}
		$("#form").form('submit', {
			url : contextPath + "/active/add.do?json",
			onSubmit : function(param) {
				param.content = content;
				return $("#form").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					Active.toList();
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
			url : contextPath + "/active/update.do?json",
			onSubmit : function(param) {
				param.content = content;
				return $("#form").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					Active.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"toList" : function() {
		window.self.location.href = contextPath + "/active/list.do";
	},

	"deleteRecord" : function(id) {
		$.ajax({
			url : contextPath + "/active/delete.do?json",
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
			url : contextPath + "/active/batchDelete.do?json",
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
		window.self.location.href = contextPath + "/active/show.do?id=" + id;
	},

	"showDepart" : function() {
		$('#departDiv').window("open");
	},

	"cancel" : function() {
		$.ajax({
			url : contextPath + "/participation/delete.do?id=" + $("#participationId").val(),
			type : "post",
			async : true,
			dataType : "json",
			success : function(data) {
				if (data.code == 200) {
					$.messager.alert("系统提示", "取消成功", 'info');
					Active.show($("#id").val());
				} else {
					$.messager.alert("系统错误", data.msg, 'error');
				}
			},
			error : function() {
				$.messager.alert("系统错误", "取消失败", 'error');
			}
		});
	},

	"depart" : function() {
		$("#departForm").form('submit', {
			url : contextPath + "/participation/add.do?json",
			onSubmit : function(param) {
				param.activeId = $("#id").val();
				return $("#departForm").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					Active.show($("#id").val());
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"hideDepart" : function() {
		$('#departDiv').window('close');
	},

	"showParticipation" : function() {
		window.self.location.href = contextPath + "/participation/list.do?activeId=" + $("#id").val();
	},

	"exportParticipation" : function() {
		window.self.location.href = contextPath + "/participation/exportParticipation.do?activeId=" + $("#activeId").val();
	},

	"end" : null
}