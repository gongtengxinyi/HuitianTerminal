package com.huitian.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.fasterxml.jackson.annotation.JsonProperty;


/***
 * 订单信息表
 *
 * @author liurengjie
 *
 */

public class IndentParam extends StringIdPo {


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
