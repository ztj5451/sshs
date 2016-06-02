package com.yunrong.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "crm_user")
public class LoginUser extends BaseEntity implements java.io.Serializable,
		UserDetails {

	private static final long serialVersionUID = -2400769758495080278L;
	private String username; // 用户名
	private String password; // 密码
	private String name;// 姓名
	private String email; // 邮箱
	private Date loginDate;// 最后登录日期
	private String loginIp;// 最后登录IP
	private boolean userAccountNonExpired; // 账号是否未过期
	private boolean userAccountNonLocked; // 账号是否未锁定
	private boolean userCredentialsNonExpired; // 账号凭证是否未过期
	private boolean userEnabled; // 账号是否可用
	private Set<UserLevel> levels;

	// UserDetails的角色资源属性集合
	private Collection<GrantedAuthority> authorities;

	@Column(nullable = false, updatable = false, unique = true)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Column(nullable = false)
	public boolean isUserAccountNonExpired() {
		return userAccountNonExpired;
	}

	public void setUserAccountNonExpired(boolean userAccountNonExpired) {
		this.userAccountNonExpired = userAccountNonExpired;
	}

	@Column(nullable = false)
	public boolean isUserAccountNonLocked() {
		return userAccountNonLocked;
	}

	public void setUserAccountNonLocked(boolean userAccountNonLocked) {
		this.userAccountNonLocked = userAccountNonLocked;
	}

	@Column(nullable = false)
	public boolean isUserCredentialsNonExpired() {
		return userCredentialsNonExpired;
	}

	public void setUserCredentialsNonExpired(boolean userCredentialsNonExpired) {
		this.userCredentialsNonExpired = userCredentialsNonExpired;
	}

	@Column(nullable = false)
	public boolean isUserEnabled() {
		return userEnabled;
	}

	public void setUserEnabled(boolean userEnabled) {
		this.userEnabled = userEnabled;
	}

	// true ，表示账号未过期
	@Transient
	public boolean isAccountNonExpired() {
		return this.userAccountNonExpired;
	}

	// true，表示账号未锁定
	@Transient
	public boolean isAccountNonLocked() {
		return this.userAccountNonLocked;
	}

	// true，表示账号凭证未过期
	@Transient
	public boolean isCredentialsNonExpired() {
		return this.userCredentialsNonExpired;
	}

	// true，表示账号可使用
	@Transient
	public boolean isEnabled() {
		return this.userEnabled;
	}

	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", updatable = false, insertable = false)
	public Set<UserLevel> getLevels() {
		return levels;
	}

	public void setLevels(Set<UserLevel> levels) {
		this.levels = levels;
	}

	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		if (authorities != null) {
			return authorities;
		} else {
			if (levels == null || levels.size() == 0) {
				return null;
			} else {
				authorities = new ArrayList<GrantedAuthority>(levels.size());
				for (UserLevel urlLevel : levels) {
					authorities.add(new SimpleGrantedAuthority(urlLevel
							.getLevelName()));
				}
				return authorities;
			}
		}
	}

	@Transient
	public void clearAuthorities() {
		authorities = null;
	}
}