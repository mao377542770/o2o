package com.ugfind.model;

import java.util.Date;

public class User {
    private Integer id;

    private Integer schoolId;

    private Integer userType;
    
    private Integer roomManager;

    private Integer deptId;

    private String userName;

    private String passWord;

    private String pic;

    private String name;

    private String nickName;

    private Integer gender;

    private String telphone;

    private String email;

    private Date createDate;
    
    private Integer attestationState;

    private Integer valid;
    
    private String schoolName;
    
    private String deptName;

	public Integer getId() {
        return id;
    }

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
    

    public Integer getRoomManager() {
		return roomManager;
	}

	public void setRoomManager(Integer roomManager) {
		this.roomManager = roomManager;
	}

	public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public Integer getAttestationState() {
		return attestationState;
	}

	public void setAttestationState(Integer attestationState) {
		this.attestationState = attestationState;
	}

	public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", schoolId=" + schoolId + ", userType="
				+ userType + ", deptId=" + deptId + ", userName=" + userName
				+ ", passWord=" + passWord + ", pic=" + pic + ", name=" + name
				+ ", nickName=" + nickName + ", gender=" + gender
				+ ", telphone=" + telphone + ", email=" + email
				+ ", createDate=" + createDate + ", valid=" + valid
				+ ", schoolName=" + schoolName + ", deptName=" + deptName + "]";
	}
	
	
}