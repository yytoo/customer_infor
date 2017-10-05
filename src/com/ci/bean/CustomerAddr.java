package com.ci.bean;

/**
 * CustomerAddr entity. @author MyEclipse Persistence Tools
 */

public class CustomerAddr implements java.io.Serializable {

	// Fields

	private Integer id;
	private String addr;
	private String cityId;
	private Integer addrCount;
	private String qqNum;

	// Constructors

	/** default constructor */
	public CustomerAddr() {
	}

	/** full constructor */
	public CustomerAddr(String addr, String cityId, Integer addrCount,
			String qqNum) {
		this.addr = addr;
		this.cityId = cityId;
		this.addrCount = addrCount;
		this.qqNum = qqNum;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCityId() {
		return this.cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public Integer getAddrCount() {
		return this.addrCount;
	}

	public void setAddrCount(Integer addrCount) {
		this.addrCount = addrCount;
	}

	public String getQqNum() {
		return this.qqNum;
	}

	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

}