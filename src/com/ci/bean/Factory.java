package com.ci.bean;

/**
 * Factory entity. @author MyEclipse Persistence Tools
 */

public class Factory implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String type;
	private String cityId;
	private String cityName;
	private String address;
	private String phone;
	private String fax;
	private String descp;
	private String major;
	private String shortName;
	private String remarks;
	private Integer logTime;
	private String fullName;
	private String country;
	private String logo;
	private String searchKeyword;
	private Integer searchOrder;
	private String largeLogo;
	private Integer updateTimestamp;

	// Constructors

	/** default constructor */
	public Factory() {
	}

	/** minimal constructor */
	public Factory(String name, String type, String cityId, String cityName,
			String address, String phone, String fax, String descp,
			String major, String shortName, Integer logTime) {
		this.name = name;
		this.type = type;
		this.cityId = cityId;
		this.cityName = cityName;
		this.address = address;
		this.phone = phone;
		this.fax = fax;
		this.descp = descp;
		this.major = major;
		this.shortName = shortName;
		this.logTime = logTime;
	}

	/** full constructor */
	public Factory(String name, String type, String cityId, String cityName,
			String address, String phone, String fax, String descp,
			String major, String shortName, String remarks, Integer logTime,
			String fullName, String country, String logo, String searchKeyword,
			Integer searchOrder, String largeLogo, Integer updateTimestamp) {
		this.name = name;
		this.type = type;
		this.cityId = cityId;
		this.cityName = cityName;
		this.address = address;
		this.phone = phone;
		this.fax = fax;
		this.descp = descp;
		this.major = major;
		this.shortName = shortName;
		this.remarks = remarks;
		this.logTime = logTime;
		this.fullName = fullName;
		this.country = country;
		this.logo = logo;
		this.searchKeyword = searchKeyword;
		this.searchOrder = searchOrder;
		this.largeLogo = largeLogo;
		this.updateTimestamp = updateTimestamp;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCityId() {
		return this.cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getDescp() {
		return this.descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Integer logTime) {
		this.logTime = logTime;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getSearchKeyword() {
		return this.searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public Integer getSearchOrder() {
		return this.searchOrder;
	}

	public void setSearchOrder(Integer searchOrder) {
		this.searchOrder = searchOrder;
	}

	public String getLargeLogo() {
		return this.largeLogo;
	}

	public void setLargeLogo(String largeLogo) {
		this.largeLogo = largeLogo;
	}

	public Integer getUpdateTimestamp() {
		return this.updateTimestamp;
	}

	public void setUpdateTimestamp(Integer updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

}