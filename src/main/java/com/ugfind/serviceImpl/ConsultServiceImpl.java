package com.ugfind.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.ConsultMapper;
import com.ugfind.model.Consult;
import com.ugfind.model.ConsultPage;
import com.ugfind.service.ConsultService;

@Service
public class ConsultServiceImpl implements ConsultService {

	private ConsultMapper consultDao;
	
	
	public ConsultMapper getConsultDao() {
		return consultDao;
	}

	@Autowired
	public void setConsultDao(ConsultMapper consultDao) {
		this.consultDao = consultDao;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return consultDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Consult record) {
		return consultDao.insertSelective(record);
	}

	@Override
	public Consult selectByPrimaryKey(Integer id) {
		return consultDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Consult record) {
		return consultDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Consult> getMyconsult(Integer userId) {
		return consultDao.getMyconsult(userId);
	}

	@Override
	public List<Consult> getMyDeptConsult(ConsultPage conPage) {
		return consultDao.getMyDeptConsult(conPage);
	}

	@Override
	public Integer getConsultCount(ConsultPage conPage) {
		return consultDao.getConsultCount(conPage);
	}

	@Override
	public Consult getConsultInfo(Integer id) {		
		return consultDao.getConsultInfo(id);
	}

	@Override
	public int saveConsult(Consult consult) {
		return consultDao.insertSelective(consult);
	}

}
