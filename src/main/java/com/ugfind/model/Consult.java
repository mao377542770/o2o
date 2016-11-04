package com.ugfind.model;

import java.util.Date;

public class Consult {
    private Integer id;

    private String question;

    private String answer;

    private Date askTime;

    private Date replyTime;

    private Integer askUser;

    private Integer deptId;

    private Integer schoolId;
    
    private String nickName;
    
    private String telphone;
    
    public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Date getAskTime() {
        return askTime;
    }

    public void setAskTime(Date askTime) {
        this.askTime = askTime;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getAskUser() {
        return askUser;
    }

    public void setAskUser(Integer askUser) {
        this.askUser = askUser;
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

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
}