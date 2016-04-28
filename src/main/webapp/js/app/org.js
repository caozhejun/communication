var Org = {

	"toAdd" : function() {
		window.self.location.href = contextPath + "/org/toAdd.do";
	},

	"toUpdate" : function(id) {
		window.self.location.href = contextPath + "/org/toUpdate.do?id=" + id;
	},

	"add" : function() {
		$("#form").form('submit', {
			url : contextPath + "/org/add.do?json",
			onSubmit : function() {
				return $("#form").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					Org.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"update" : function() {
		$("#form").form('submit', {
			url : contextPath + "/org/update.do?json",
			onSubmit : function() {
				return $("#form").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					Org.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"toList" : function() {
		window.self.location.href = contextPath + "/org/list.do";
	},

	"deleteRecord" : function(id) {
		$.ajax({
			url : contextPath + "/org/delete.do?json",
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
			url : contextPath + "/org/batchDelete.do?json",
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

	"end" : null
}