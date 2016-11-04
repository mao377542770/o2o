package com.ugfind.model;

import java.util.Date;

public class Bespoke {
    private Integer id;

    private Integer roomId;

    private Date startTime;

    private Date endTime;

    private Integer appUser;
    
    private String createTime;
    
    private String nickName;
    
    private String phone;//预约人电话
    
    private Integer state;//删除状态
    
    private Integer deleteUserId;//删除人id
    
    private String deleteTime;

    private String deleteUser;//删除人姓名
    
    private String deletePhone;//删除人联系电话
    
    private String deleteUserName;//删除人账号
    
    private String boardName; //会室
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getAppUser() {
        return appUser;
    }

    public void setAppUser(Integer appUser) {
        this.appUser = appUser;
    }

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getDeleteUserId() {
		return deleteUserId;
	}

	public void setDeleteUserId(Integer deleteUserId) {
		this.deleteUserId = deleteUserId;
	}

	public String getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(String deleteTime) {
		this.deleteTime = deleteTime;
	}

	public String getDeleteUser() {
		return deleteUser;
	}

	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}

	public String getDeletePhone() {
		return deletePhone;
	}

	public void setDeletePhone(String deletePhone) {
		this.deletePhone = deletePhone;
	}

	public String getDeleteUserName() {
		return deleteUserName;
	}

	public void setDeleteUserName(String deleteUserName) {
		this.deleteUserName = deleteUserName;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	
}