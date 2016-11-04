package com.ugfind.model;

public class VoteoptionVo extends Voteoption{
    private Boolean isVote;

    private Integer voteCount;

	public Boolean getIsVote() {
		return isVote;
	}

	public void setIsVote(Boolean isVote) {
		this.isVote = isVote;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

}