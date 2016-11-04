package test;

import java.sql.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ugfind.dao.ContactMapper;
import com.ugfind.model.Contact;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml","classpath:spring-mvc.xml"})
public class TestContact {
	
	@Resource
	private ContactMapper contactDao;
	
	
	@Test
	public void test(){
		Contact contact = new Contact();
		contact.setName("李四");
		contact.setTelphone("1561561615651");
		contact.setContent("fafdafadf");
		//contact.setCreateDate(new Date(System.currentTimeMillis()));
		contactDao.insertSelective(contact);
	}
}
