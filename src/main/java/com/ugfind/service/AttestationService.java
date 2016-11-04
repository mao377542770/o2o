package com.ugfind.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ugfind.model.Attestation;

public interface AttestationService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Attestation record);

    Attestation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attestation record);

    //获取所有认证申请（根据不同的审批状态获取不同的信息）
    List<Attestation> getAllAttestation(Map<String,Integer> map);
    /**
     * 通过申请用户id获取认证信息
     * @param applyUserId
     * @return
     */
    Attestation selectByApplyUserId(@Param("applyUserId")Integer applyUserId);
}