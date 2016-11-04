package com.ugfind.dao;

import java.util.List;

import com.ugfind.model.LostFound;
import com.ugfind.model.LostFoundConfig;
import com.ugfind.model.LostFoundVo;

public interface LostFoundMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LostFound record);

    int insertSelective(LostFound record);

    LostFound selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LostFound record);

    int updateByPrimaryKeyWithBLOBs(LostFound record);

    int updateByPrimaryKey(LostFound record);
    
    //以下是自己所加 
    //获取列表未认领
    List<LostFoundVo> getLostFoundList(LostFoundConfig pageConfig);
    //获取列表已认领
    List<LostFoundVo> getLostFoundListRemark(LostFoundConfig pageConfig);
    //获取数量
    int getLostFoundCount(LostFoundConfig pageConfig);
}