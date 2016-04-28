var MenuFrame = {

	/**
	 * 初始化菜单框架
	 * 
	 * @param menuDataUrl
	 *            获取菜单数据的url
	 * @param title
	 *            框架标题
	 * @param menuTitle
	 *            菜单树标题
	 * @param firstMenu
	 *            要显示的第一个菜单的url(一般都是一个欢迎页面,可为空)
	 * @returns {Ext.Viewport}
	 */
	"initMenuFrame" : function(menuDataUrl, title, menuTitle, firstMenu) {
		var tree = ExtTreeUtil.createNormalTree(menuDataUrl, menuTitle, 180, null, false, "west", "menuTree", MenuFrame.clickMenu);
		var tab = null;
		if (!!firstMenu) {
			tab = [ {
				id : -1,
				title : "首页",
				iconCls : 'tabs',
				html : Common.getFrameWithUrl(firstMenu),
				closable : true
			} ];
		}
		var tabs = ExtTabUtil.createNormalTabPanel("menuTabs", "center", tab);
		var items = [ tree, tabs ];
		if (!!title) {
			items.push({
				region : "north",
				html : title
			});
		}
		var viewPort = new Ext.Viewport({
			items : items,
			layout : "border"
		});
		return viewPort;
	},

	/**
	 * 点击菜单节点执行函数
	 * 
	 * @param node
	 */
	"clickMenu" : function(node) {
		var url = node.attributes.url;
		if (!url) {
			return false;
		}
		var tabId = "menuTabs";
		var tabPanel = Ext.getCmp(tabId);
		var nodeId = node.attributes.id;
		if (ExtTabUtil.hasTab(tabPanel, tabId, nodeId)) {
			ExtTabUtil.activeTab(tabPanel, tabId, nodeId);
		} else {
			ExtTabUtil.addTabWithUrl(tabPanel, tabId, nodeId, node.attributes.text, url);
		}
	},

	"end" : null
};