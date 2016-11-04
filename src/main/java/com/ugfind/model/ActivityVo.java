package com.ugfind.model;

import java.util.List;


public class ActivityVo extends Activity{
    
    private String schoolName;
    
    private List<Voteoption> voteList;

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public List<Voteoption> getVoteList() {
		return voteList;
	}

	public void setVoteList(List<Voteoption> voteList) {
		this.voteList = voteList;
	}

}