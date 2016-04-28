var Notice = {

	"toAdd" : function() {
		window.self.location.href = contextPath + "/notice/toAdd.do?type=" + $("#type").val();
	},

	"toUpdate" : function(id) {
		window.self.location.href = contextPath + "/notice/toUpdate.do?id=" + id;
	},

	"add" : function() {
		var content = UE.getEditor('content').getContent();
		if (!content) {
			$.messager.alert("系统提示", "内容不能为空", "warning");
			return false;
		}
		$("#form").form('submit', {
			url : contextPath + "/notice/add.do?json",
			onSubmit : function(param) {
				param.content = content;
				return $("#form").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					Notice.toList();
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
			url : contextPath + "/notice/update.do?json",
			onSubmit : function(param) {
				param.content = content;
				return $("#form").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					Notice.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"toList" : function() {
		window.self.location.href = contextPath + "/notice/list.do?type=" + $("#type").val();
	},

	"deleteRecord" : function(id) {
		$.ajax({
			url : contextPath + "/notice/delete.do?json",
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
			url : contextPath + "/notice/batchDelete.do?json",
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
		window.self.location.href = contextPath + "/notice/show.do?id=" + id;
	},

	"end" : null
}