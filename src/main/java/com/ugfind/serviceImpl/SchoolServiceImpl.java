package com.ugfind.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ugfind.dao.SchoolMapper;
import com.ugfind.dao.SchoolvisitsMapper;
import com.ugfind.dao.cache.RedisDao;
import com.ugfind.model.School;
import com.ugfind.model.Schoolvisits;
import com.ugfind.service.SchoolService;

@Repository
public class SchoolServiceImpl implements SchoolService {

	@Resource
	private SchoolMapper schoolDao;
	@Resource
	private SchoolvisitsMapper schoolVisitsDao;
	@Resource
	private RedisDao redisDao;

	/* (non-Javadoc)
	 * @see com.ugfind.serviceImpl.SchoolService#getSchoolList(java.lang.String, java.lang.String)
	 */
	@Override
	public List<School> getSchoolList(String keyWord, String city) {
		List<School>  schools = null;
		if (keyWord != null && keyWord != "") {
			schools =  schoolDao.getSchoolListByKeyWord(keyWord);
		} else {
			schools = schoolDao.getSchoolListByCity(city);
		}
		return schools;
	}

	@Override
	public void addSchoolVisits(Integer schoolId) {
		schoolVisitsDao.addSchoolVisits(schoolId);
	}

	@Override
	public Schoolvisits selectSchoolVisitsBySchoolId(Integer schoolId) {
		//Schoolvisits schoolvisits = redisDao.getVisitBySchoolId(schoolId);
		
		Schoolvisits schoolvisits = null;
		schoolvisits = schoolVisitsDao.selectSchoolVisitsBySchoolId(schoolId);
		if(schoolvisits == null){
			//说明表中没有该学校,需要在这里建一个学校对应的visits 表
			schoolvisits = new Schoolvisits();
			schoolvisits.setSchoolId(schoolId);
			schoolvisits.setSchoolVisits(1);
			schoolVisitsDao.insertSelective(schoolvisits);
		}
			//redisDao.setSchoolVisit(schoolvisits);
		return schoolvisits;
	}
	
}
