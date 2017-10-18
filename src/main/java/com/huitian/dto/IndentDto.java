package com.huitian.dto;

public class IndentDto {
	private String picName;//图片dxf名字
	private double xLongToZero;//距离x轴距离
	private double yLongToZero;//距离y轴距离
	private double zSpin;//z轴旋转
	private double zoom;//放大倍数
	private String centerAccountId;//加工中心
	private String picType;// 图案类别
	private String picCode;// 图案编码
	private String wordContent;// 文字内容
	private String wordSize;
	private String wordFont;
	private String alignType;// alignType
	private double wordLeanSize;// 倾斜度
	private String picCount;// picCount
	private double spaceSize;// 图案间距
	private String indentId;// 订单id
	private String receiverName;
	private String receiverMobile;

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public double getxLongToZero() {
		return xLongToZero;
	}

	public void setxLongToZero(double xLongToZero) {
		this.xLongToZero = xLongToZero;
	}

	public double getyLongToZero() {
		return yLongToZero;
	}

	public void setyLongToZero(double yLongToZero) {
		this.yLongToZero = yLongToZero;
	}

	public double getzSpin() {
		return zSpin;
	}

	public void setzSpin(double zSpin) {
		this.zSpin = zSpin;
	}

	public double getZoom() {
		return zoom;
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

	public String getCenterAccountId() {
		return centerAccountId;
	}

	public void setCenterAccountId(String centerAccountId) {
		this.centerAccountId = centerAccountId;
	}

	public String getPicType() {
		return picType;
	}
	public void setPicType(String picType) {
		this.picType = picType;
	}
	public String getPicCode() {
		return picCode;
	}
	public void setPicCode(String picCode) {
		this.picCode = picCode;
	}
	public String getWordContent() {
		return wordContent;
	}
	public void setWordContent(String wordContent) {
		this.wordContent = wordContent;
	}
	public String getWordSize() {
		return wordSize;
	}
	public void setWordSize(String wordSize) {
		this.wordSize = wordSize;
	}
	public String getWordFont() {
		return wordFont;
	}
	public void setWordFont(String wordFont) {
		this.wordFont = wordFont;
	}
	public String getAlignType() {
		return alignType;
	}
	public void setAlignType(String alignType) {
		this.alignType = alignType;
	}
	public double getWordLeanSize() {
		return wordLeanSize;
	}
	public void setWordLeanSize(double wordLeanSize) {
		this.wordLeanSize = wordLeanSize;
	}
	public String getPicCount() {
		return picCount;
	}
	public void setPicCount(String picCount) {
		this.picCount = picCount;
	}
	public double getSpaceSize() {
		return spaceSize;
	}
	public void setSpaceSize(double spaceSize) {
		this.spaceSize = spaceSize;
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
	
}
