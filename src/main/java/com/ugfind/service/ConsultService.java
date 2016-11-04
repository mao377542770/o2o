package com.ugfind.service;

import java.util.List;


import com.ugfind.model.Consult;
import com.ugfind.model.ConsultPage;

public interface ConsultService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Consult record);

    Consult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Consult record);
    
    //获取自己的所有咨询
    List<Consult> getMyconsult(Integer userId);
    
    //获取本部门的所有咨询
    List<Consult> getMyDeptConsult(ConsultPage conPage);
    
    //获取咨询数量
    Integer getConsultCount(ConsultPage conPage);

	Consult getConsultInfo(Integer id);

	int saveConsult(Consult consult);
}