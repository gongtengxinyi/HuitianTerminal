package com.huitian.constants;



/**
 * 即时通讯模式枚举
 * @author Zlyj-B
 *
 */
public enum EnumMessageMode{
    GET_MORE("在获取一个订单"),
    CENTER_QUEUE_ALLINDENT("加工中心所有的订单返回"),
    HEART_BREAK("心跳"),
  START_MACHINE("开始加工获取订单"),
    CENTER_ALLINDENT("获取一个城市加工店所有的订单"),
  STOP_MACHINE("停止接收订单"),
  PC_START_CONNECT("桌面PC连接"),
  APP_PUSH_INDENT("接口推送订单"),
  WECHAT_PUSH_INDENT("微信推送订单"),
  NO_INDENT("没订单"),
    HAVE_INDENT("有订单"),
	;

	private String text;

	private EnumMessageMode(String text) {
		this.text = text;
	}

}
