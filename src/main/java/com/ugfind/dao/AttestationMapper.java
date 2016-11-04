package com.ugfind.dao;

import java.util.List;
import java.util.Map;

import com.ugfind.model.Attestation;

public interface AttestationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Attestation record);

    int insertSelective(Attestation record);

    Attestation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attestation record);

    int updateByPrimaryKey(Attestation record);
    
    //获取所有认证申请（根据不同的审批状态获取不同的信息）
    List<Attestation> getAllAttestation(Map<String,Integer> map);
    
    /**
     * 通过申请用户id获取认证信息
     * @param applyUserId
     * @return
     */
    Attestation selectByApplyUserId(Integer applyUserId);

}