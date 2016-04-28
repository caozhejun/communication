/**
 * Ext的window工具类
 */
var ExtWindowUtil = {

	/**
	 * 打开window组件(url返回的html页面里的脚本不会执行)
	 * 
	 * @param {}
	 *            url
	 * @param {}
	 *            title
	 * @param {}
	 *            id
	 * @param {}
	 *            width
	 * @param {}
	 *            height
	 */
	"openWinOnlyHtml" : function(url, title, id, width, height, modal) {
		if (!width) {
			width = 500;
		}
		if (!height) {
			height = 500;
		}
		if (!modal) {
			modal = false;
		}
		Ext.Ajax.request({
			url : url,
			method : "post",
			success : function(resp) {
				var tipWindow = new Ext.Window({
					border : true,
					id : id,
					title : title,
					maximizable : false,
					layout : 'fit',
					html : resp.responseText,
					autoScroll : true,
					width : width,
					height : height,
					modal : modal
				}).show();
			}
		});
	},

	/**
	 * 打开winodw组件
	 * 
	 * @param {}
	 *            html
	 * @param {}
	 *            title
	 * @param {}
	 *            id
	 * @param {}
	 *            width
	 * @param {}
	 *            height
	 */
	"openWinwithHtml" : function(html, title, id, width, height, modal) {
		if (!width) {
			width = 500;
		}
		if (!height) {
			height = 500;
		}
		if (!modal) {
			modal = false;
		}
		var tipWindow = new Ext.Window({
			border : true,
			id : id,
			title : title,
			maximizable : false,
			layout : 'fit',
			html : html,
			autoScroll : true,
			width : width,
			height : height,
			modal : modal
		}).show();
	},

	/**
	 * 打开window组件，使用iframe方式嵌套显示url页面
	 * 
	 * @param {}
	 *            url
	 * @param {}
	 *            title
	 * @param {}
	 *            id
	 * @param {}
	 *            width
	 * @param {}
	 *            height
	 */
	"openWinWithIframe" : function(url, title, id, width, height, modal) {
		if (!width) {
			width = 500;
		}
		if (!height) {
			height = 500;
		}
		if (!modal) {
			modal = false;
		}
		new Ext.Window({
			id : id,
			title : title,
			layout : 'fit',
			border : true,
			plain : true,
			maximizable : false,
			autoScroll : true,
			width : width,
			height : height,
			modal : modal,
			items : [ {
				title : title,
				header : false,
				html : Common.getFrameWithUrl(url),
				border : false
			} ]
		}).show();
	},

	/**
	 * 关闭窗口
	 * 
	 * @param id
	 */
	"closeWindow" : function(id) {
		Ext.getCmp(id).close();
	},

	"end" : null
};