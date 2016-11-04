package com.ugfind.model;


public class Attestation {
    private Integer id;

    private String applyName;

    private String applyDept;

    private String applyPhone;

    private String applyDate;

    private String atteDate;

    private Integer atteUserId;

    private Integer deptId;

    private Integer schoolId;

    private Integer atteState;
    
    private String atteUserName;
    
    private Integer applyUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplyName() {
        return applyName;
    }

    public Integer getApplyUserId() {
		return applyUserId;
	}

	public void setApplyUserId(Integer applyUserId) {
		this.applyUserId = applyUserId;
	}

	public void setApplyName(String applyName) {
        this.applyName = applyName == null ? null : applyName.trim();
    }

    public String getApplyDept() {
        return applyDept;
    }

    public void setApplyDept(String applyDept) {
        this.applyDept = applyDept == null ? null : applyDept.trim();
    }

    public String getApplyPhone() {
        return applyPhone;
    }

    public void setApplyPhone(String applyPhone) {
        this.applyPhone = applyPhone == null ? null : applyPhone.trim();
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getAtteDate() {
        return atteDate;
    }

    public void setAtteDate(String atteDate) {
        this.atteDate = atteDate;
    }

    public Integer getAtteUserId() {
        return atteUserId;
    }

    public void setAtteUserId(Integer atteUserId) {
        this.atteUserId = atteUserId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getAtteState() {
        return atteState;
    }

    public void setAtteState(Integer atteState) {
        this.atteState = atteState;
    }

	public String getAtteUserName() {
		return atteUserName;
	}

	public void setAtteUserName(String atteUserName) {
		this.atteUserName = atteUserName;
	}
    
}