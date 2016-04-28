var ExtTabUtil = {

	/**
	 * 创建一个普通的TabPanel
	 * 
	 * @param id
	 *            tab的id
	 * @param region
	 *            布局
	 * @param initItems
	 *            tab里的初始化tab页
	 * @returns {Ext.TabPanel}
	 */
	"createNormalTabPanel" : function(id, region, initItems) {
		var tabs = new Ext.TabPanel({
			id : id,
			region : region,
			border : false,
			buttonAlign : "center",
			resizeTabs : true,
			minTabWidth : 115,
			tabWidth : 135,
			enableTabScroll : true,
			closable : true,
			animScroll : true,
			activeTab : 0,
			animCollapse : true,
			draggable : false,
			defaults : {
				autoScroll : true
			},
			plugins : new Ext.ux.TabCloseMenu(),
			items : initItems
		});
		return tabs;
	},

	/**
	 * tabPanel如果为空，根据tabId获取tabPanel
	 * 
	 * @param tabPanel
	 * @param tabId
	 * @returns
	 */
	"getTabPanel" : function(tabPanel, tabId) {
		if (!tabPanel) {
			tabPanel = Ext.getCmp(tabId);
		}
		return tabPanel;
	},

	/**
	 * 激活tab页
	 * 
	 * @param tabPanel
	 *            tabPanel的容器对象
	 * @param tabId
	 *            tabPanel的id
	 * @param subTabId
	 *            子tab的id
	 */
	"activeTab" : function(tabPanel, tabId, subTabId) {
		tabPanel = ExtTabUtil.getTabPanel(tabPanel, tabId);
		tabPanel.setActiveTab(subTabId + "");
	},

	/**
	 * 判断tabPanel中是否存在subTabId的子tab页
	 * 
	 * @param tabPanel
	 *            tabPanel的容器对象
	 * @param tabId
	 *            tabPanel的id
	 * @param subTabId
	 *            子tab的id
	 */
	"hasTab" : function(tabPanel, tabId, subTabId) {
		tabPanel = ExtTabUtil.getTabPanel(tabPanel, tabId);
		var subTab = tabPanel.getItem(subTabId);
		return !!subTab;
	},

	/**
	 * 新增一个子tab页
	 * 
	 * @param tabPanel
	 *            tabPanel的容器对象
	 * @param tabId
	 *            tabPanel的id
	 * @param subTabId
	 *            子tab的id
	 * @param subTabName
	 *            子tab的name
	 * @param url
	 *            子tab中的iframe的url地址
	 */
	"addTabWithUrl" : function(tabPanel, tabId, subTabId, subTabName, url) {
		tabPanel = ExtTabUtil.getTabPanel(tabPanel, tabId);
		tabPanel.add({
			id : subTabId,
			title : subTabName,
			iconCls : 'tabs',
			html : Common.getFrameWithUrl(url),
			closable : true
		}).show();
	},

	"end" : null
};

// 右键弹出菜单
Ext.ux.TabCloseMenu = function() {
	var tabs, menu, ctxItem;
	this.init = function(tp) {
		tabs = tp;
		tabs.on('contextmenu', onContextMenu);
	};
	function onContextMenu(ts, item, e) {
		if (!menu) {
			menu = new Ext.menu.Menu([ {
				id : tabs.id + '-close',
				text : '关闭当前页',
				handler : function() {
					tabs.remove(ctxItem);
				}
			}, {
				id : tabs.id + '-close-others',
				text : '关闭其它页',
				handler : function() {
					tabs.items.each(function(item) {
						if (item.closable && item != ctxItem) {
							tabs.remove(item);
						}
					});
				}
			}, {
				id : tabs.id + '-close-all',
				text : '关闭所有页',
				handler : function() {
					tabs.items.each(function(item) {
						if (item.closable) {
							tabs.remove(item);
						}
					});
				}
			} ]);
		}
		ctxItem = item;
		var items = menu.items;
		items.get(tabs.id + '-close').setDisabled(!item.closable);
		var disableOthers = true;
		tabs.items.each(function() {
			if (this != item && this.closable) {
				disableOthers = false;
				return false;
			}
		});
		items.get(tabs.id + '-close-others').setDisabled(disableOthers);
		var disableAll = true;
		tabs.items.each(function() {
			if (this.closable) {
				disableAll = false;
				return false;
			}
		});
		items.get(tabs.id + '-close-all').setDisabled(disableAll);
		menu.showAt(e.getPoint());
	}
};