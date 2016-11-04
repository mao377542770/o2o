package com.ugfind.service;

import java.util.List;

import com.ugfind.model.LostFound;
import com.ugfind.model.LostFoundConfig;
import com.ugfind.model.LostFoundVo;

public interface LostFoundService {
    int delete(Integer id);

    int insert(LostFound record);
    
    int insertSelective(LostFound record);

    int update(LostFound record);
    
    int updateByPrimaryKeySelective(LostFound record);
    //获取列表未认领
    List<LostFoundVo> getLostFoundList(LostFoundConfig pageConfig);
    //获取列表已认领
    List<LostFoundVo> getLostFoundListRemark(LostFoundConfig pageConfig);
    //获取数量
    int getLostFoundCount(LostFoundConfig pageConfig);
}