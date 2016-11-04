package com.ugfind.dao;

import java.util.List;

import com.ugfind.model.Boardroom;

public interface BoardroomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Boardroom record);

    int insertSelective(Boardroom record);

    Boardroom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Boardroom record);

    int updateByPrimaryKey(Boardroom record);
    //获取本校的会室
    List<Boardroom> getAllBoardroom(Integer schoolId);
}