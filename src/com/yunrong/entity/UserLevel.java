package com.yunrong.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "crm_userLevel")
public class UserLevel extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -6465231559667002605L;

	private String userId;
	private String levelName;
	private LoginUser user;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "userId", updatable = false, insertable = false)
	public LoginUser getUser() {
		return user;
	}

	public void setUser(LoginUser user) {
		this.user = user;
	}

}