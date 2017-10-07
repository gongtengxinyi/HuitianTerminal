package com.huitian.pojo;

/**
 * 消息传送类
 *
 * @author 丁建磊
 */
public class ChatMessage {
    private String indentList;
    private String indentDto;
    private String timeTag;//时间串
    private String indentId;// 订单ID。有些消息与订单相关
    private String messageMode;// 信息模式： 紧急通知，普通通知，新订单，订单状态，聊天
    private String messageType;// 消息类型：1,文本；2，图片；3，小视频；4，文件；5，地理位置；6，语音；7，视频通话
    private String message;// 文本消息内容
    private String belong;
    private String param1;//
    private String param2;//
    private boolean succ;// 是否成功
    private String suffix;// 二进制文件后缀，如txt,acc,jpg,png,gif等
    private String uuid;//文件名字
    private String binaryAddress;// 二进制文件的存储位置。上传之后，将文件存储在指定的位置，然后将binary清空
    private String binaryBase64;//图片的base64字符串

    public String getIndentList() {
        return indentList;
    }

    public void setIndentList(String indentList) {
        this.indentList = indentList;
    }

    public String getIndentDto() {
        return indentDto;
    }

    public void setIndentDto(String indentDto) {
        this.indentDto = indentDto;
    }

    public String getTimeTag() {
        return timeTag;
    }

    public void setTimeTag(String timeTag) {
        this.timeTag = timeTag;
    }

    public String getIndentId() {
        return indentId;
    }

    public void setIndentId(String indentId) {
        this.indentId = indentId;
    }

    public String getMessageMode() {
        return messageMode;
    }

    public void setMessageMode(String messageMode) {
        this.messageMode = messageMode;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public boolean isSucc() {
        return succ;
    }

    public void setSucc(boolean succ) {
        this.succ = succ;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBinaryAddress() {
        return binaryAddress;
    }

    public void setBinaryAddress(String binaryAddress) {
        this.binaryAddress = binaryAddress;
    }

    public String getBinaryBase64() {
        return binaryBase64;
    }

    public void setBinaryBase64(String binaryBase64) {
        this.binaryBase64 = binaryBase64;
    }


}
