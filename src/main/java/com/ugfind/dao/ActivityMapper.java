package com.ugfind.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ugfind.model.Activity;
import com.ugfind.model.ActivityConfig;
import com.ugfind.model.ActivityVo;

public interface ActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKeyWithBLOBs(Activity record);

    int updateByPrimaryKey(Activity record);
    //获取列表
    List<Activity> getAllActivity(ActivityConfig acConfig);
    //获取数量
    int getAcCount(ActivityConfig acConfig);
    //增加阅览数
    int addViewCount(Integer id);
    /**
     * 前台页面获取学校活动(投票)信息
     * @param acConfig
     * @return
     */
    List<ActivityVo> getFrontActivity(ActivityConfig acConfig);
    /**
     * 获取前台页面获取学校活动(投票)数量
     * @param acConfig
     * @return
     */
    int getFrontActivityCount(ActivityConfig acConfig);
    /**
     * 前台通过id获取活动详情
     * @param id
     * @param userId 判断当前是否已投
     * @return
     */
    ActivityVo getFrontActivityInfoById(@Param("id")Integer id, @Param("userId")Integer userId);
}