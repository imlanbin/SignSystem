package cn.edu.ccnu.imd.analysis.vo;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private String id;
	private String userName;
	private String password;
	private String level;
	private String state;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String id, String userName, String password, String level) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.level = level;
	}

	/** full constructor */
	public User(String id, String userName, String password, String level,
			String state) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.level = level;
		this.state = state;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}