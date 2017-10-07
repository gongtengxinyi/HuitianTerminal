package com.huitian.constants;



/**
 * 即时通讯模式枚举
 * @author Zlyj-B
 *
 */
public enum EnumFilterTab {
    NO_WORKING_TAB("未加工tab"),
    NO_SENDGOODS_TAB("未发货tab"),
    YES_SENDGOODS_TAB("发货tab"),
    HISTORY_TAB("历史tab"),
	;

	private String text;

	private EnumFilterTab(String text) {
		this.text = text;
	}

}
