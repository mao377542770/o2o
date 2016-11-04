package test;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.ugfind.util.SendMail;

public class Test {
	@org.junit.Test
	public void test() throws AddressException, IOException, MessagingException{
		String code = SendMail.getRandomString(6);
		String content = "您好,15288397286@163.com<br>"+
	 			"根据您的需要,我们已经收到了您的修改密码请求,请在验证码部分填以下验证码:<br>"+
 			    "<h1>"+code+"</h1><br>"+
	 			"<br><br><br><br><br><br>"
	 			+ "-----优超科技有限公司-----";
		SendMail.send_email("15288397286@163.com", "优超科技密码验证", content);
	}
}
