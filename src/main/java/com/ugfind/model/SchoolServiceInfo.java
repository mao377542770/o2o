package com.ugfind.model;

import java.sql.Date;

/**
 * 学校服务详情类
 * @author Administrator
 *
 */
public class SchoolServiceInfo {
	private Integer schoolId;
	private String schoolName;
	private Integer deptId;
	private String deptName;
	private Date serviceTime;
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Date getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(Date serviceTime) {
		this.serviceTime = serviceTime;
	}
	@Override
	public String toString() {
		return "SchoolServiceInfo [schoolId=" + schoolId + ", schoolName="
				+ schoolName + ", deptId=" + deptId + ", deptName=" + deptName
				+ ", serviceTime=" + serviceTime + "]";
	}
	
	
}
