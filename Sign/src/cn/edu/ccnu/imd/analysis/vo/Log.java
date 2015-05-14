package cn.edu.ccnu.imd.analysis.vo;

import java.util.Date;

/**
 * Log entity. @author MyEclipse Persistence Tools
 */

public class Log implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String userName;
	private String loginTime;
	private String loginIp;

	// Constructors

	/** default constructor */
	public Log() {
	}

	/** minimal constructor */
	public Log(String id, String userId) {
		this.id = id;
		this.userId = userId;
	}

	/** full constructor */
	public Log(String id, String userId, String userName, String loginTime,
			String loginIp) {
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.loginTime = loginTime;
		this.loginIp = loginIp;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(String string) {
		this.loginTime = string;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

}