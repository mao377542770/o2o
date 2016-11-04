package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ugfind.dao.SchoolvisitsMapper;
import com.ugfind.dao.cache.RedisDao;
import com.ugfind.model.Schoolvisits;


/**
 * @author Administrator
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class RedisDaoTest{

	private int schoolId = 76;
	@Autowired
	private RedisDao redisDao;
	@Autowired
	private SchoolvisitsMapper schoolvisitsDao;
	/**
	 * Test method for {@link com.ugfind.dao.cache.RedisDao#getVisitBySchoolId(java.lang.Integer)}.
	 */
	@Test
	public void testGetVisitBySchoolId() {
		Schoolvisits schoolvisits = redisDao.getVisitBySchoolId(schoolId);
		System.out.println(schoolvisits);
		if(schoolvisits == null){
			schoolvisits = schoolvisitsDao.selectSchoolVisitsBySchoolId(schoolId);
			String result = redisDao.setSchoolVisit(schoolvisits);
			System.out.println(result);
			schoolvisits = redisDao.getVisitBySchoolId(schoolId);
			System.out.println(schoolvisits);
		}
	}

	/**
	 * Test method for {@link com.ugfind.dao.cache.RedisDao#setSchoolVisit(com.ugfind.model.Schoolvisits)}.
	 */
	@Test
	public void testSetSchoolVisit() {
		
	}

}
