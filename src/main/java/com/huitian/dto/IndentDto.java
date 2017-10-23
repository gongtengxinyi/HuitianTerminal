package com.huitian.dto;

import java.util.List;

public class IndentDto {
	private String centerAccountId;
	private String indentId;// 订单id
	private String receiverName;
	private String receiverMobile;
	private List<Param> param;

	public String getCenterAccountId() {
		return centerAccountId;
	}

	public void setCenterAccountId(String centerAccountId) {
		this.centerAccountId = centerAccountId;
	}

	public String getIndentId() {
		return indentId;
	}

	public void setIndentId(String indentId) {
		this.indentId = indentId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public List<Param> getParam() {
		return param;
	}

	public void setParam(List<Param> param) {
		this.param = param;
	}
}
