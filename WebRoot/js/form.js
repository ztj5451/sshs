var count = 10;
function CountDown() {
	// 每1秒调用CountDown()函数
	if (count > 0) {
		$("#getPsd_form_button").attr("disabled", true);
		$("#getPsd_form_button").val("请您耐心等待 " + count + " 秒!");
		var countdown = setInterval(CountDown, 1000);
		// 计算器归零则按钮状态还原
	} else {
		$("#getPsd_form_button").val("找回密码").removeAttr("disabled");
		clearInterval(countdown);
	}
	count--;
}
$(function() {
	/**
	 * 注册校验,组号为"1"
	 */
	$.formValidator.initConfig({
		validatorGroup : "1",
		theme : "126", // 插件皮肤
		submitOnce : true,
		formID : "add_form",
		ajaxForm : {
			dataType : "json",
			buttons : $("#button"),
			url : "admin/user!userSave.action", // 按钮的Ajax提交请求
			success : function(data) { // 表单成功回调函数
				if (data == true) {
					$.dialog({
						type : "success",
						content : "注册成功",
						modal : true,
						autoCloseTime : 3000
					});
				} else {
					$.dialog({
						type : "success",
						content : "注册失败",
						modal : true,
						autoCloseTime : 3000
					});
				}
			}
		},
		// 表单提交失败函数
		onError : function(msg, obj, errorlist) {
			$("#errorlist").empty();
			$.map(errorlist, function(msg) {
				$("#errorlist").append("<li>" + msg + "</li>");
			});
			$.dialog({
				type : "error",
				content : msg,
				modal : true,
				autoCloseTime : 5000
			});
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	// 用户名控件配置
	$("#username").formValidator({
		validatorGroup : "1",
		// usernameFixTip层的提示文本
		onShowFixText : "6~12个字符，包括字母、数字、下划线，以字母开头，字母或数字结尾",
		// 进入页面时提示
		onShowText : "请输入用户名",
		// 获得焦点时的提示
		onFocus : "用户名至少1个字符,最多10个字符",
		// 通过验证时提示
		onCorrect : "该用户名可以注册"
	// 长度范围设置
	}).inputValidator({
		min : 1, // 字符串最小长度
		max : 10, // 字符串最大长度
		onError : "你输入的用户名非法,请确认"
	// 正则表达式输入校验
	}).regexValidator({
		// 正则表达式校验
		regExp : "username",
		dataType : "enum",
		onError : function(data) {
			return "用户名[" + data + "]格式不正确";
		}
	// ajax校验
	}).ajaxValidator({
		dataType : "json",
		async : true,
		url : "admin/user!checkUsername.action",
		success : function(data) {
			if (data == true)
				return true; // 显示onCorrect的提示
			return "该用户名不可用，请更换用户名"; // 自定义提示
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("服务器没有返回数据，可能服务器忙，请重试" + errorThrown);
		},
		onError : "注册失败,请检查资料是否填写正确",
		onWait : "正在对用户名进行合法性校验，请稍候..."
	});

	// 密码控件配置
	$("#password1").formValidator({
		validatorGroup : "1",
		onShowFixText : "6~16个字符，包括字母、数字、特殊符号，区分大小写",
		onShow : "请输入密码",
		onFocus : "至少1个长度",
		onCorrect : "密码合法"
	}).inputValidator({
		min : 1,
		empty : {
			leftEmpty : false,
			rightEmpty : false,
			emptyError : "密码两边不能有空符号"
		},
		onError : "密码不能为空,请确认"
	}).passwordValidator({
		compareID : "username"
	});
	$("#password2").formValidator({
		validatorGroup : "1",
		onShowFixText : "请再次输入密码",
		onShow : "输再次输入密码",
		onFocus : "至少1个长度",
		onCorrect : "密码一致"
	}).inputValidator({
		min : 1,
		empty : {
			leftEmpty : false,
			rightEmpty : false,
			emptyError : "重复密码两边不能有空符号"
		},
		onError : "重复密码不能为空,请确认"
	}).compareValidator({
		desID : "password1",
		operateor : "=",
		onError : "2次密码不一致,请确认"
	});

	// 邮箱控件配置
	$("#email").formValidator({
		validatorGroup : "1",
		onShowFixText : "6~18个字符，包括字母、数字、下划线，以字母开头，字母或数字结尾",
		onShow : "请输入邮箱",
		onFocus : "邮箱6-100个字符,输入正确了才能离开焦点",
		onCorrect : "恭喜你,该邮箱可以使用",
		defaultValue : "@"
	}).inputValidator({
		min : 6,
		max : 100,
		onError : "你输入的邮箱长度非法,请确认"
	}).regexValidator({
		regExp : "email",
		dataType : "enum",
		onError : "你输入的邮箱格式不正确"
	}).ajaxValidator({
		dataType : "json",
		async : true,
		url : "admin/user!checkEmail.action",
		success : function(data) {
			if (data == true)
				return true; // 显示onCorrect的提示
			return "该邮箱名已被注册，请更换其他邮箱"; // 自定义提示
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("服务器没有返回数据，可能服务器忙，请重试" + errorThrown);
		},
		onError : "注册失败,请检查资料是否填写正确",
		onWait : "正在对邮箱名进行合法性校验，请稍候..."
	});

	/**
	 * 找回密码校验,组号为3
	 */
	$.formValidator.initConfig({
		validatorGroup : "3",
		theme : "126", // 插件皮肤
		submitOnce : true,
		formID : "getPsd_form",
		ajaxForm : {
			dataType : "json",
			buttons : $("#getPsd_form_button"),
			url : "user_rePassword.action", // 按钮的Ajax提交请求
			success : function(data) { // 表单成功回调函数
				if (data.resultMsg == "1") {
					// jquery对话框
					$.dialog({
						type : "success",
						content : "密码已成功发送至您的邮箱!",
						modal : true,
						autoCloseTime : 3000
					});
					// 调用函数改变按钮当前状态
					CountDown();
				} else {
					$.dialog({
						type : "error",
						content : "请检查用户名或邮箱地址",
						modal : true,
						autoCloseTime : 5000
					});
				}
			}
		},
		// 表单提交失败函数
		onError : function(msg, obj, errorlist) {
			$("#errorlist").empty();
			$.map(errorlist, function(msg) {
				$("#errorlist").append("<li>" + msg + "</li>");
			});
			$.dialog({
				type : "error",
				content : msg,
				modal : true,
				autoCloseTime : 5000
			});
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	// 邮箱控件配置
	$("#get_email").formValidator({
		validatorGroup : "3"
	}).regexValidator({
		regExp : "email",
		dataType : "enum",
		onError : "你输入的邮箱格式不正确"
	});

});
