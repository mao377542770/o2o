package com.ugfind.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.AttestationMapper;
import com.ugfind.model.Attestation;
import com.ugfind.service.AttestationService;

@Service
public class AttestationServiceImpl implements AttestationService {

	private AttestationMapper attestationDao;
	
	
	public AttestationMapper getAttestationDao() {
		return attestationDao;
	}
	@Autowired
	public void setAttestationDao(AttestationMapper attestationDao) {
		this.attestationDao = attestationDao;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return attestationDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Attestation record) {
		return attestationDao.insertSelective(record);
	}

	@Override
	public Attestation selectByPrimaryKey(Integer id) {
		return attestationDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Attestation record) {
		return attestationDao.updateByPrimaryKeySelective(record);
	}
	@Override
	public List<Attestation> getAllAttestation(Map<String,Integer> map) {
		return attestationDao.getAllAttestation(map);
	}
	@Override
	public Attestation selectByApplyUserId(Integer applyUserId) {
		return attestationDao.selectByApplyUserId(applyUserId);
	}

}
