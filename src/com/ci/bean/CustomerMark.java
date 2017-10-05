package com.ci.bean;

/**
 * CustomerMark entity. @author MyEclipse Persistence Tools
 */

public class CustomerMark implements java.io.Serializable {

	// Fields

	private Integer id;
	private String mark;
	private Integer markId;
	private String factory;
	private Integer factoryId;
	private String qqNum;
	private Integer markCount;

	// Constructors

	/** default constructor */
	public CustomerMark() {
	}

	/** full constructor */
	public CustomerMark(String mark, Integer markId, String factory,
			Integer factoryId, String qqNum, Integer markCount) {
		this.mark = mark;
		this.markId = markId;
		this.factory = factory;
		this.factoryId = factoryId;
		this.qqNum = qqNum;
		this.markCount = markCount;
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

	public Integer getMarkId() {
		return this.markId;
	}

	public void setMarkId(Integer markId) {
		this.markId = markId;
	}

	public String getFactory() {
		return this.factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public Integer getFactoryId() {
		return this.factoryId;
	}

	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}

	public String getQqNum() {
		return this.qqNum;
	}

	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

	public Integer getMarkCount() {
		return this.markCount;
	}

	public void setMarkCount(Integer markCount) {
		this.markCount = markCount;
	}

}