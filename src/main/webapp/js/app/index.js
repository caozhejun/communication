var Index = {

	/**
	 * 初始化菜单框架
	 * 
	 * @param userName
	 */
	"initMenuFrame" : function(userName, isAdmin) {
		var title = "<div class=\"titleHeader\">";
		title += "<div class=\"logo\"></div>";
		title += " <div class=\"nav\">";
		title += "<div class=\"loginInf\">欢迎您," + userName + "！</div>";
		title += " <div class=\"button\">";
		if ("true" != isAdmin) {
			title += "<img class=\"icon\" src=\"" + contextPath + "/images/edictPic.png\"/><a href=\"javascript:void(0);\" onclick='User.toModifyPwd();'>修改密码</a><img class=\"fenge\" src=\""
					+ contextPath + "/images/fenge.png\"/>";

			title += "<img class=\"icon\" src=\"" + contextPath + "/images/edictPic.png\"/><a href=\"javascript:void(0);\" onclick='User.toModifyInfo();'>修改个人信息</a><img class=\"fenge\" src=\""
					+ contextPath + "/images/fenge.png\"/>";

		}
		title += "<a id=\"aId\" href=\"javascript:void(0);\" onclick='User.logout();'><img class=\"icon\" id=\"iconId\" src=\"" + contextPath + "/images/powerPic.png\"/>退 出</a>";
		title += "</div></div></div>";
		MenuFrame.initMenuFrame(contextPath + "/menu/listChildrenMenu.do", title, "菜单", contextPath + "/home/home.do");
		if ("true" == isAdmin) {
			$("#iconId").css("paddingLeft", "300px");
			$(".button").css("background", "none");
		}
	},

	"end" : null
};
