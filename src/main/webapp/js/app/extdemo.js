Ext.onReady(function() {
	Ext.QuickTips.init();
	testPageGrid();
		// testWindow();
	});

function testWindow() {
	ExtWindowUtil.openWinWithIframe("/demo/esbuser/list2.do", "测试弹出window窗口实例",
			"tipWindow");
	// ExtWindowUtil.openWinOnlyHtml("/demo/esbuser/list2.do", "测试弹出window窗口实例",
	// "tipWindow");
}

function renderName(value, cellmeta, record, rowIndex, columnIndex, store) {
	var tip = ExtGridUtil.renderValue(value, cellmeta, record, rowIndex,
			columnIndex, store);
	var html = "<a href='#' onclick='tip(\"" + tip + "\")'>" + value + "</a>";
	return html;
}

function tip(s) {
	alert(s);
}

function oper(value) {
	tip(value);
}

function renderOper(value, cellmeta, record, rowIndex, columnIndex, store) {
	var html = "<input type='button' value='操作' onclick='oper(\""
			+ record.data["id"] + "\")'/>";
	return html;
}

function testPageGrid() {
	var url = contextPath + "/ext/grid.do";
	var pageSize = 10;
	var fields = [{
				name : "id",
				type : "string"
			}, {
				name : "name",
				type : "string"
			}];
	var cmModels = [{
				header : 'ID',
				dataIndex : 'id',
				width : 15,
				sortable : true
			}, {
				header : '姓名',
				dataIndex : 'name',
				width : 30,
				sortable : true,
				renderer : renderName
			}, {
				header : "操作",
				renderer : renderOper
			}];
	var type = "checkbox";
	var id = "gridId";
	var field = "id";
	var tbar = [{
				text : "工具栏"
			}, '->', {
				xtype : 'button',
				text : '获取选中行的值',
				handler : function() {
					alert(ExtGridUtil.getValue(type, null, id, field));
				}
			}];
	ExtGridUtil.loadPageGrid(url, pageSize, fields, cmModels, '人员信息', "panel",
			tbar, 500, id, type, field);

}
