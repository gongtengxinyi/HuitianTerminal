package com.huitian.pojo;

import java.util.Date;

public class CenterAccount {
	private String cennterId;// 用户属于哪一个加工中心（其值为HT_CENTER表中的id字段）
	private String name1;// 登录用户名
	private String password;// 登录密码
	private String systemPush;//是否是系统推送账号 
	// PK ID
	private String id;
	private Date createDate;
	private String createBy;
	private Date modifyDate;
	private String modifyBy;

	public String getSystemPush() {
		return systemPush;
	}

	public void setSystemPush(String systemPush) {
		this.systemPush = systemPush;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public String getCennterId() {
		return cennterId;
	}

	public void setCennterId(String cennterId) {
		this.cennterId = cennterId;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
