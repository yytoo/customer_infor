package com.ci.bean;

/**
 * SysDist entity. @author MyEclipse Persistence Tools
 */

public class SysDist implements java.io.Serializable {

	// Fields

	private String distId;
	private String distName;
	private String level;
	private String plevel;

	// Constructors

	/** default constructor */
	public SysDist() {
	}

	/** full constructor */
	public SysDist(String distName, String level, String plevel) {
		this.distName = distName;
		this.level = level;
		this.plevel = plevel;
	}

	// Property accessors

	public String getDistId() {
		return this.distId;
	}

	public void setDistId(String distId) {
		this.distId = distId;
	}

	public String getDistName() {
		return this.distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPlevel() {
		return this.plevel;
	}

	public void setPlevel(String plevel) {
		this.plevel = plevel;
	}

}