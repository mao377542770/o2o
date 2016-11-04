package com.ugfind.model;

import java.util.Date;

public class BespokeConfig {
	private Integer roomId;
	private Date thisDay;
	
	
	public BespokeConfig() {
		super();
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public Date getThisDay() {
		return thisDay;
	}
	public void setThisDay(Date thisDay) {
		this.thisDay = thisDay;
	}
	
	
}
