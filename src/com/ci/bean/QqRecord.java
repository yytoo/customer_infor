package com.ci.bean;

/**
 * QqRecord entity. @author MyEclipse Persistence Tools
 */

public class QqRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private String mark;
	private String date;
	private String raw;
	private String otherContent;
	private String factory;
	private String qq;
	private String phone;
	private String behaveType;
	private String tradeType;
	private String address;
	private String linkContent1;
	private String linkContent2;
	private Integer markId;
	private String cityId;
	private Integer factoryId;

	// Constructors

	/** default constructor */
	public QqRecord() {
	}

	/** full constructor */
	public QqRecord(String mark, String date, String raw, String otherContent,
			String factory, String qq, String phone, String behaveType,
			String tradeType, String address, String linkContent1,
			String linkContent2, Integer markId, String cityId,
			Integer factoryId) {
		this.mark = mark;
		this.date = date;
		this.raw = raw;
		this.otherContent = otherContent;
		this.factory = factory;
		this.qq = qq;
		this.phone = phone;
		this.behaveType = behaveType;
		this.tradeType = tradeType;
		this.address = address;
		this.linkContent1 = linkContent1;
		this.linkContent2 = linkContent2;
		this.markId = markId;
		this.cityId = cityId;
		this.factoryId = factoryId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRaw() {
		return this.raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public String getOtherContent() {
		return this.otherContent;
	}

	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}

	public String getFactory() {
		return this.factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBehaveType() {
		return this.behaveType;
	}

	public void setBehaveType(String behaveType) {
		this.behaveType = behaveType;
	}

	public String getTradeType() {
		return this.tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkContent1() {
		return this.linkContent1;
	}

	public void setLinkContent1(String linkContent1) {
		this.linkContent1 = linkContent1;
	}

	public String getLinkContent2() {
		return this.linkContent2;
	}

	public void setLinkContent2(String linkContent2) {
		this.linkContent2 = linkContent2;
	}

	public Integer getMarkId() {
		return this.markId;
	}

	public void setMarkId(Integer markId) {
		this.markId = markId;
	}

	public String getCityId() {
		return this.cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public Integer getFactoryId() {
		return this.factoryId;
	}

	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}

}