package com.ugfind.service;

import java.util.List;

import com.ugfind.model.Boardroom;

public interface BoardroomService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Boardroom record);

    Boardroom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Boardroom record);
    
    //获取本校的会室
    List<Boardroom> getAllBoardroom(Integer schoolId);
}