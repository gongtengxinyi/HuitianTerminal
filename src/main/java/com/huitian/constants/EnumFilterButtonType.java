package com.huitian.constants;



/**
 * 即时通讯模式枚举
 * @author Zlyj-B
 *
 */
public enum EnumFilterButtonType {
    SHOUYE_BUTTON("首页"),
    LAST_BUTTON("尾页"),
    NEXT_BUTTON("下一页"),
    PRE_BUTTON("上一页"),
    GOTO_BUTTON("跳转"),
	;

	private String text;

	private EnumFilterButtonType(String text) {
		this.text = text;
	}

}
