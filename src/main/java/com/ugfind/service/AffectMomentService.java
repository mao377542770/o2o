package com.ugfind.service;

import java.util.List;

import com.ugfind.model.AffectMoment;
import com.ugfind.model.PageVo;

public interface AffectMomentService {
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
    /**
     * 通过id获取感动瞬间详情
     * @param id
     * @return
     */
    AffectMoment getAffectMomentById(Integer id);
    //阅览数增加
    int addViewCount(Integer id);
}