package com.ci.bean;

/**
 * CustomerInfor entity. @author MyEclipse Persistence Tools
 */

public class CustomerInfor implements java.io.Serializable {

	// Fields

	private Integer id;
	private String qqNum;
	private String name;
	private String fileName;
	private Integer referId;
	private String phone;
	private String compAndName;

	// Constructors

	/** default constructor */
	public CustomerInfor() {
	}

	/** full constructor */
	public CustomerInfor(String qqNum, String name, String fileName,
			Integer referId, String phone, String compAndName) {
		this.qqNum = qqNum;
		this.name = name;
		this.fileName = fileName;
		this.referId = referId;
		this.phone = phone;
		this.compAndName = compAndName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQqNum() {
		return this.qqNum;
	}

	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getReferId() {
		return this.referId;
	}

	public void setReferId(Integer referId) {
		this.referId = referId;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompAndName() {
		return this.compAndName;
	}

	public void setCompAndName(String compAndName) {
		this.compAndName = compAndName;
	}

}