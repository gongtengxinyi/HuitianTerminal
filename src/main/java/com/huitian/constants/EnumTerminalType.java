package com.huitian.constants;

public enum EnumTerminalType {

	ANDROID_INTERFACE("安卓"),
	PC("微信小程序"),
	WECHAT("微信公众号"),
	WEB("web")
	;

	private String text;

	private EnumTerminalType(String text) {
		this.text = text;
	};
}
