var ExtTreeUtil = {

	/**
	 * 创建一颗普通的树
	 * 
	 * @param dataUrl
	 *            动态异步获取子节点的url地址，发送请求时，自动在url后传递参数node
	 * @param title
	 *            标题
	 * @param width
	 *            宽度
	 * @param rootNode
	 *            根节点 如{ id : '-1', text : '根' }
	 * @param showRoot
	 *            是否显示根节点
	 * @param region
	 *            布局位置
	 * @param id
	 * @param onClickFunction
	 *            点击事件
	 * @returns {Ext.tree.TreePanel}
	 */
	"createNormalTree" : function(dataUrl, title, width, rootNode, showRoot, region, id, onClickFunction) {
		if (!region) {
			region = "center";
		}
		if (!showRoot) {
			showRoot = false;
		}
		if (!rootNode) {
			rootNode = {
				id : '-1',
				text : '根'
			};
		}
		if (!width) {
			width = 180;
		}
		var tree = new Ext.tree.TreePanel({
			id : id,
			region : region,
			collapsible : true,
			title : title,
			width : width,
			border : false,
			autoScroll : true,
			animate : true,
			rootVisible : showRoot,
			split : true,
			loader : new Ext.tree.TreeLoader({
				dataUrl : dataUrl
			}),
			root : new Ext.tree.AsyncTreeNode(rootNode),
			listeners : {
				click : function(node) {
					if (!!onClickFunction) {
						onClickFunction(node);
					}
				}
			}
		});
		return tree;
	},

	"end" : null
};