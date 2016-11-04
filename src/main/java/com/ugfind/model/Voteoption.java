package com.ugfind.model;

public class Voteoption {
    private Integer id;

    private Integer activityId;

    private String title;
    
    private String optionCount;

    private String optionImg;
    
    private Integer voteCount;
    
    private Integer voteNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOptionCount() {
        return optionCount;
    }

    public void setOptionCount(String optionCount) {
        this.optionCount = optionCount == null ? null : optionCount.trim();
    }

    public String getOptionImg() {
        return optionImg;
    }

    public void setOptionImg(String optionImg) {
        this.optionImg = optionImg == null ? null : optionImg.trim();
    }

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	public Integer getVoteNum() {
		return voteNum;
	}

	public void setVoteNum(Integer voteNum) {
		this.voteNum = voteNum;
	}
	
	
}