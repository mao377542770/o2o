package com.ugfind.service;

import java.util.List;

import com.ugfind.model.PageVo;
import com.ugfind.model.RelaxMoment;

public interface RelaxMomentService {
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
    /**
     * 通过id获取轻松时刻详情
     * @param id
     * @return
     */
    RelaxMoment getRelaxMomentById(Integer id);
    //阅览数增加
    int addViewCount(Integer id);
}