package com.ugfind.model;

import org.springframework.stereotype.Repository;

@Repository
public class Result {
	private int status;
	private Object data;
	private String msg;
	
	public Result() {
		super();
	}
	
	public Result(int status, Object data, String msg) {
		super();
		this.status = status;
		this.data = data;
		this.msg = msg;
	}

	//快捷设置result
	public void setResult(int status,Object data,String msg){
		 this.status = status;
		 this.data = data;
		 this.msg = msg;
	};
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Result [status=" + status + ", data=" + data + ", msg=" + msg
				+ "]";
	}
	
	
	
}
