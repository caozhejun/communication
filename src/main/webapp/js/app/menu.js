var Menu = {

	"toAdd" : function() {
		window.self.location.href = contextPath + "/menu/toAdd.do?parentID=" + $("#parentID").val();
	},

	"batchDelete" : function() {
		var idArray = new Array();
		var selectedMenus = $("#menuTable").datagrid("getSelections");
		$.each(selectedMenus, function(i, menu) {
			idArray.push(menu.id);
		});
		if (idArray.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的菜单", 'warning');
			return false;
		}
		$.ajax({
			url : contextPath + "/menu/batchDelete.do?json",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				id : idArray.join(",")
			},
			success : function(data) {
				if (data.code == 200) {
					$("#menuTable").datagrid("load", {
						id : $("#parentID").val()
					});
					var parentNode = $("#menuTree").tree("find", $("#parentID").val());
					$("#menuTree").tree("reload", parentNode.target);
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

	"selectMenu" : function(node) {
		var id = node.id;
		$("#parentID").val(id);
		$("#menuTable").datagrid("load", {
			id : id
		});
		var name = node.text + "--子菜单";
		$(".layout-panel-center .panel-title").html(name);
	},

	"toUpdate" : function(id) {
		window.self.location.href = contextPath + "/menu/toUpdate.do?id=" + id;
	},

	"deleteMenu" : function(id) {
		$.ajax({
			url : contextPath + "/menu/delete.do?json",
			type : "post",
			dataType : "json",
			async : true,
			data : {
				id : id
			},
			success : function(data) {
				if (data.code == 200) {
					$("#menuTable").datagrid("load", {
						id : $("#parentID").val()
					});
					var parentNode = $("#menuTree").tree("find", $("#parentID").val());
					$("#menuTree").tree("reload", parentNode.target);
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
		$("#menuForm").form('submit', {
			url : contextPath + "/menu/add.do?json",
			onSubmit : function() {
				return $("#menuForm").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					Menu.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"update" : function() {
		$("#menuForm").form('submit', {
			url : contextPath + "/menu/update.do?json",
			onSubmit : function() {
				return $("#menuForm").form("validate");
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.code == 200) {
					Menu.toList();
				} else {
					$.messager.alert("系统错误", data.msg, "error");
				}
			}
		});
	},

	"toList" : function() {
		window.self.location.href = contextPath + "/menu/list.do";
	},

	"end" : null

};