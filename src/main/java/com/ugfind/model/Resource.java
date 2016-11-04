package com.ugfind.model;

import java.util.Date;

public class Resource {
    private Integer id;

    private Integer userId;
    
    private String nickName;

    private Integer type;
    
    private String typeName;

    private String title;

    private String fileName;

    private String path;

    private String other;

    private Integer downloadTotal;

    private String introduce;
    
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    

    public Integer getUserId() {
        return userId;
    }
    

	public String getNickName() {
		return nickName;
	}
	

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    public Integer getDownloadTotal() {
        return downloadTotal;
    }

    public void setDownloadTotal(Integer downloadTotal) {
        this.downloadTotal = downloadTotal;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}