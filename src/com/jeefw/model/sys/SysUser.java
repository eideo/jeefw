package com.jeefw.model.sys;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.base.Objects;
import com.jeefw.model.sys.param.SysUserParameter;

import core.support.DateSerializer;
import core.support.DateTimeSerializer;

/**
 * 用户的实体类
 *
 */
@Entity
@Table(name = "sys_user")
@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "maxResults", "firstResult", "topCount", "sortColumns", "cmd", "queryDynamicConditions", "sortedConditions", "dynamicProperties", "success", "message", "sortColumnsString", "flag" })
public class SysUser extends SysUserParameter {

	// 各个字段的含义请查阅文档的数据库结构部分
	private static final long serialVersionUID = 822330369002149147L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	@Column(name = "user_name", length = 40, nullable = false)
	private String userName;
	@Column(name = "sex")
	private Short sex;
	@Column(name = "email", length = 30, nullable = false, unique = true)
	private String email;
	@Column(name = "phone", length = 20)
	private String phone;
	@Column(name = "birthday")
	@Temporal(TemporalType.DATE)
	private Date birthday;
	@Column(name = "department_key", length = 20)
	private String departmentKey;
	@Column(name = "password", length = 100, nullable = false)
	private String password;
	@Column(name = "status", nullable = false)
	private Boolean status;
	@Column(name = "last_logintime")
	@Temporal(TemporalType.TIMESTAMP)  //表示时间的精度，可以精确到时分秒。
	private Date lastLoginTime;


    @ManyToMany(fetch = FetchType.EAGER)  //急加载，加载一个实体时，定义急加载的属性会立即从数据库中加载
	@JoinTable(name = "sysuser_role", joinColumns = { @JoinColumn(name = "sysuser_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	@Cache(region = "all", usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Role> roles = new HashSet<Role>();

	public SysUser() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Short getSex() {
		return sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

    //日期转换@JsonSerialize(using=JsonDateSerializer.class)，所以下面的DateSerializer是继承了JsonSerializer
	@JsonSerialize(using = DateSerializer.class)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDepartmentKey() {
		return departmentKey;
	}

	public void setDepartmentKey(String departmentKey) {
		this.departmentKey = departmentKey;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@JsonSerialize(using = DateTimeSerializer.class)
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final SysUser other = (SysUser) obj;
		return Objects.equal(this.id, other.id) && Objects.equal(this.userName, other.userName) && Objects.equal(this.sex, other.sex) && Objects.equal(this.email, other.email) && Objects.equal(this.phone, other.phone)
				&& Objects.equal(this.birthday, other.birthday) && Objects.equal(this.departmentKey, other.departmentKey) && Objects.equal(this.password, other.password) && Objects.equal(this.status, other.status)
				&& Objects.equal(this.lastLoginTime, other.lastLoginTime) && Objects.equal(this.roles, other.roles);
	}

	public int hashCode() {
		return Objects.hashCode(this.id, this.userName, this.sex, this.email, this.phone, this.birthday, this.departmentKey, this.password, this.status, this.lastLoginTime, this.roles);
	}

}