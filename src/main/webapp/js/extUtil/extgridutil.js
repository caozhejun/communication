/**
 * 加入以下代码之后，可以不用按Ctrl键多选，直接点击可多选
 */
Ext.override(Ext.grid.CheckboxSelectionModel, {
	handleMouseDown : function(g, rowIndex, e) {
		if (e.button !== 0 || this.isLocked()) {
			return;
		}
		var view = this.grid.getView();
		if (e.shiftKey && !this.singleSelect && this.last !== false) {
			var last = this.last;
			this.selectRange(last, rowIndex, e.ctrlKey);
			this.last = last;
			view.focusRow(rowIndex);
		} else {
			var isSelected = this.isSelected(rowIndex);
			if (isSelected) {
				this.deselectRow(rowIndex);
			} else if (!isSelected || this.getCount() > 1) {
				this.selectRow(rowIndex, true);
				view.focusRow(rowIndex);
			}
		}
	}
});

/**
 * 
 * 分页Grid工具类
 */
var ExtGridUtil = {

	"pageSize" : 10,

	"getPageSize" : function(pageSize) {
		if (!pageSize || pageSize <= 0) {
			return ExtGridUtil.pageSize;
		}
		return pageSize;
	},

	/**
	 * 使用了Jquery的选择器功能，在type为rowandcheckbox的才有用，用于html形式的checkbox全选
	 */
	"checkAll" : function() {
		var checked = $("#checkAll").is(":checked");
		$('input[name=selectedRow]').each(function() {
			if (checked) {
				$(this).attr("checked", "checked");
			} else {
				$(this).removeAttr("checked");
			}
		});
	},

	/**
	 * 获取grid中选中的值(grid和gridId必须有一个不为空)
	 * 
	 * @param {}
	 *            type 就是loadPageGrid的type
	 * @param {}
	 *            grid grid对象，可为空
	 * @param {}
	 *            gridId grid的id，可为空
	 * @param {}
	 *            field 属性值
	 * @return {} 多个值用，隔开
	 */
	"getValue" : function(type, grid, gridId, field) {
		if (!grid) {
			grid = Ext.getCmp(gridId);
		}
		var valueArray = new Array();
		if (type == "checkbox") {
			var records = grid.getSelectionModel().getSelections();
			for (var i = 0; i < records.length; i++) {
				valueArray.push(records[i].data[field]);
			}
		} else if (type == "radio") {
			valueArray.push(grid.getSelectionModel().getSelected()[field]);
		} else if (type == "rowandradio") {
			valueArray.push($("input[name='selectedRow']:checked").val());
		} else if (type == "rowandcheckbox") {
			$("input[name='selectedRow']:checked").each(function() {
				valueArray.push($(this).val());
			});
		}
		return valueArray.join(",");
	},

	/**
	 * 刷新当前页
	 * 
	 * @param grid
	 * @param gridId
	 */
	"refreshCurrentPage" : function(grid, gridId) {
		if (!grid) {
			grid = Ext.getCmp(gridId);
		}
		var store = grid.getStore();
		store.reload();
	},

	/**
	 * 传入参数重新加载grid
	 * 
	 * @param grid
	 * @param gridId
	 * @param param
	 * @param PageSize
	 */
	"refreshStore" : function(grid, gridId, param, pageSize) {
		if (!grid) {
			grid = Ext.getCmp(gridId);
		}
		var store = grid.getStore();
		store.on("beforeload", function(obj) {
			if (!!param) {
				for ( var o in param) {
					obj.baseParams[o] = param[o];
				}
			}
		});
		store.load({
			params : {
				start : 0,
				limit : ExtGridUtil.getPageSize(pageSize)
			}
		});
	},

	/**
	 * 生成一个分页grid
	 * 
	 * @param {}
	 *            dataUrl 获取数据的url，返回json格式数据,后台使用new JsonResult(new
	 *            ExtPageGrid(list, count)).toJson()生成json数据
	 * @param {}
	 *            pageSize 每页记录数
	 * @param {}
	 *            fields 从后台返回数据中获取显示值的属性列表,如[{ name : "id", type : "string" }, {
	 *            name : "name", type : "string" }]
	 * @param {}
	 *            cmModels 列模型，如 [{ header : 'ID', dataIndex : 'id', width : 15,
	 *            sortable : true }, { header : '姓名', dataIndex : 'name', width :
	 *            30, sortable : true }]
	 * @param {}
	 *            title 标题
	 * @param {}
	 *            renderId 将grid渲染到dom的id，不填则为body
	 * @param {}
	 *            tbar grid上工具栏
	 * @param {}
	 *            height 高度，不填则为350
	 * @param {}
	 *            id grid的id值
	 * @param {}
	 *            type 目前有5中，分别为 row ：只显示行序号；
	 *            rowandradio：显示行序号，且序号旁有html原生radio按钮；
	 *            rowandcheckbox：显示行序号，且序号旁有html原生checkbox按钮； radio：显示行序号和单选；
	 *            checkbox：显示行序号和多选；
	 * @param {}
	 *            valueField
	 *            在type为rowandcheckbox和rowandradio才需要填，是为了生成的radio和checkbox的值
	 * @return {} grid对象
	 */
	"loadPageGrid" : function(dataUrl, pageSize, fields, cmModels, title, renderId, tbar, height, id, type, valueField) {
		if (!height || height <= 0) {
			height = $(document).height() - 20;
		}
		var width = $(document).width();
		if (!type || type == "") {
			type = "row";
		}
		if (!renderId) {
			renderId = Ext.getBody();
		}
		var rowNumberHeader = "序号";
		if (type == "rowandcheckbox") {
			rowNumberHeader = "<input type='checkbox' name='checkAll' id='checkAll' onclick='ExtGridUtil.checkAll();'/><label for='checkAll'>全选</label>";
		}
		var rowNumber = new Ext.grid.RowNumberer({
			header : rowNumberHeader,
			width : 50,
			renderer : function(value, metadata, record, rowIndex) {
				var showNumber = record.store.lastOptions.params.start + 1 + rowIndex;
				var checkboxHtml = "<input type='checkbox' name='selectedRow'  value='" + record[valueField] + "'" + " id='" + type + showNumber + "'/>";
				var raidoHtml = "<input type='radio' name='selectedRow'  value='" + record[valueField] + "'" + " id='" + type + showNumber + "'/>";
				var label = "<label for='" + type + showNumber + "'>" + showNumber + "</label>";
				var html = "";
				if (type == "rowandradio") {
					html = raidoHtml;
				} else if (type == "rowandcheckbox") {
					html = checkboxHtml;
				}
				return html + label;
			}
		});
		var sm = null;
		if (type == "radio") {
			sm = new Ext.grid.CheckboxSelectionModel({
				singleSelect : true
			});
		} else if (type == "checkbox") {
			sm = new Ext.grid.CheckboxSelectionModel({
				singleSelect : false
			});
		}
		cmModels.unshift(rowNumber);
		if (null != sm) {
			cmModels.unshift(sm);
		}
		var proxyData = new Ext.data.HttpProxy({
			url : dataUrl
		});
		var reader = new Ext.data.JsonReader({
			root : "data.list",
			totalProperty : "data.count"
		}, fields);
		var ds = new Ext.data.Store({
			proxy : proxyData,
			reader : reader
		});
		var cm = new Ext.grid.ColumnModel(cmModels);
		cm.defaultSortable = true;
		pageSize = ExtGridUtil.getPageSize(pageSize);
		ds.load({
			params : {
				start : 0,
				limit : pageSize
			}
		});
		var grid = new Ext.grid.GridPanel({
			id : id,
			stripeRows : true,
			sm : sm,
			loadMask : {
				msg : '正在加载数据,请稍等......'
			},
			store : ds,
			layout : 'fit',
			cm : cm,
			height : height,
			width : width,
			renderTo : renderId,
			title : title,
			viewConfig : {
				forceFit : true
			},
			tbar : tbar,
			bbar : new Ext.PagingToolbar({
				pageSize : pageSize,
				store : ds,
				displayInfo : true,
				displayMsg : '当前显示{0} - {1}条，共{2}条数据',
				emptyMsg : "没有记录",
				autoScroll : true,
				buttonAlign : "right",
				beforePageText : "第",
				afterPageText : "/ {0}页",
				firstText : "首页",
				prevText : "上一页",
				nextText : "下一页",
				lastText : "尾页",
				refreshText : "刷新",
				doLoad : function(start) {
					var o = {};
					var pn = this.getParams();
					o[pn.start] = start;
					o[pn.limit] = this.pageSize;
					this.store.load({
						params : o
					});
				}
			})
		});
		return grid;
	},

	/**
	 * ColumnModel的单列渲染显示的内容(只是一个例子，给使用者作为参考)
	 * 
	 * @param {}
	 *            value 值
	 * @param {}
	 *            cellmeta 列对象
	 * @param {}
	 *            record 行记录
	 * @param {}
	 *            rowIndex 行号
	 * @param {}
	 *            columnIndex 列号
	 * @param {}
	 *            store 数据存储
	 */
	"renderValue" : function(value, cellmeta, record, rowIndex, columnIndex, store) {
		var str = "值:" + value + ",";
		str += "单元格配置:{" + "id:" + cellmeta.id + ",css:" + cellmeta.css + "},";
		str += "行:" + rowIndex + ",";
		str += "列:" + columnIndex;
		return str;
	},

	"end" : null

};