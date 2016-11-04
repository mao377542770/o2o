package com.ugfind.dao;

import java.util.List;

import com.ugfind.model.PageVo;
import com.ugfind.model.RelaxMoment;

public interface RelaxMomentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RelaxMoment record);

    int insertSelective(RelaxMoment record);

    RelaxMoment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RelaxMoment record);

    int updateByPrimaryKeyWithBLOBs(RelaxMoment record);

    int updateByPrimaryKey(RelaxMoment record);
    /**
     * 前台页面获取轻松时刻
     * @param pageInfo
     * @return
     */
    List<RelaxMoment> getRelaxMomentByPage(PageVo pageVo);
    /**
     * 获取前台页面获取轻松时刻数量
     * @return
     */
    int getRelaxMomentCount();
    //增加阅览数
    int addViewCount(Integer id);
}