package com.hust.model.user;

public class User {
	private int userid; // 客服系统序列号
	private String usernumber; // 客服编号
	private String username; // 客服姓名
	private String phone; // 电话
	private String memo; // 备注
	private String state; // 状态 0 失效 1 正常
	private String password; // 密码
	private String jobid; // 职务编号
	private String jobname; //职务名称
	private int roleid;
	private String rolename;
	public User() {
		super();
	}

	public User(int userId, String userNumber, String userName, String phone, String memo, String state, String password, String jobId) {
		super();
		this.userid = userId;
		this.usernumber = userNumber;
		this.username = userName;
		this.phone = phone;
		this.memo = memo;
		this.state = state;
		this.password = password;
		this.jobid = jobId;
	}

	public int getUserid() {
		return userid;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public String getUsernumber() {
		return usernumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setUsernumber(String usernumber) {
		this.usernumber = usernumber;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJobid() {
		return jobid;
	}

	public void setJobid(String jobid) {
		this.jobid = jobid;
	}

	public int getRoleid() {
		return roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	

}
