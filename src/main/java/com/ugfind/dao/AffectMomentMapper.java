package com.ugfind.dao;

import java.util.List;

import com.ugfind.model.AffectMoment;
import com.ugfind.model.PageVo;

public interface AffectMomentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AffectMoment record);

    int insertSelective(AffectMoment record);

    AffectMoment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AffectMoment record);

    int updateByPrimaryKeyWithBLOBs(AffectMoment record);

    int updateByPrimaryKey(AffectMoment record);
    
    /**
     * 前台页面获取感动瞬间
     * @param pageInfo
     * @return
     */
    List<AffectMoment> getAffectMomentByPage(PageVo pageVo);
    /**
     * 获取前台页面获取感动瞬间数量
     * @return
     */
    int getAffectMomentCount();
  //增加阅览数
    int addViewCount(Integer id);
}