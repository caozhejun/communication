var popup = {

	"alert" : function(content, title, closeTime) {
		if (!title) {
			title = "系统提示";
		}
		if (!closeTime) {
			closeTime = 1000;
		}
		easyDialog.open({
			container : {
				content : content,
				header : title
			},
			autoClose : closeTime
		});
	},

	"confirm" : function(content, yesfunciton, nofunction, title) {
		if (!title) {
			title = "系统提示";
		}
		easyDialog.open({
			container : {
				header : title,
				content : content,
				yesFn : yesfunciton,
				noFn : nofunction
			}
		});
	},

	"close" : function() {
		easyDialog.close();
	},

	"end" : null
};