package com.ugfind.dao;

import java.util.List;
import java.util.Map;

import com.ugfind.model.Consult;
import com.ugfind.model.ConsultPage;

public interface ConsultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Consult record);

    int insertSelective(Consult record);

    Consult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Consult record);

    int updateByPrimaryKey(Consult record);
    
    //获取自己的所有咨询
    List<Consult> getMyconsult(Integer userId);
    
    //获取本部门的所有咨询
    List<Consult> getMyDeptConsult(ConsultPage conPage);
    
    //获取咨询数量
    Integer getConsultCount(ConsultPage conPage);

	Consult getConsultInfo(Integer id);
}