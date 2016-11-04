package com.ugfind.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ugfind.model.Bespoke;
import com.ugfind.model.BespokeConfig;
import com.ugfind.model.BespokePageConfig;
import com.ugfind.model.BespokeParameter;
import com.ugfind.model.BoardroomVo;

public interface BespokeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bespoke record);

    int insertSelective(Bespoke record);

    Bespoke selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bespoke record);

    int updateByPrimaryKey(Bespoke record);
    //根据会室获取会室当前时间段的预约情况
    List<Bespoke> getBespokeByRoom(BespokeParameter para);
    //查看指定时间段本会室是否经被预约
    int checkBespokeState(BespokeParameter para);
    //后台获取一个会室所有预约情况
    List<Bespoke> getBespokeBack(BespokeConfig config);
    //判断时间是否已经被占用
    int judgeTime(BespokeParameter para);
    /**
     * 获取会室及会室预约情况
     * @param para
     * @return
     */
    List<BoardroomVo> getFrontRoomList(BespokeParameter para);
    
    /**
     * 根据会室id 及 日期 获取会室预约详情
     * @param roomId
     * @param startTime
     * @param endTime
     * @return
     */
    List<Bespoke> getBespokeBackByRoomIdAndDate(@Param("roomId")Integer roomId,@Param("startTime")String startTime, @Param("endTime")String endTime);
    
    //获取删除的记录条数
    int getDeleteCount(Integer schoolId);
    //获取删除的记录
    List<Bespoke> selectDeleteRecord(BespokePageConfig pageConfig);
}