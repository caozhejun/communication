var Common = {

	/**
	 * 获取包含url的iframe的html
	 * 
	 * @param url
	 */
	"getFrameWithUrl" : function(url) {
		return '<iframe style="border-top-width: 0px; border-left-width: 0px; border-bottom-width: 0px;border-right-width: 0px" src=' + url
				+ ' frameborder="0" width="100%" scrolling="auto" height="100%"></iframe>';
	},

	"end" : null
};