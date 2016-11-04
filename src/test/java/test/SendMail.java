package test;

import java.io.IOException;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class SendMail {
	@Test
	public void test() {
		try {
			send_email();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void send_email() throws IOException, AddressException,
			MessagingException {
		String to = "asfu520@163.com";
		String subject = "给你的一封信";
		String content = "Hello, qkyf123.<br>This is a message from pixiv's Office Department staff.<br><br>Please click on the URL below to verify your pixiv ID.<br><a target='_blank' href='http://www.pixiv.net/mail_authentication.php?12670957_f35b53fba610c8f3690c5d8874f4e182'>http://www.p<wbr>ixiv.net/mai<wbr>l_authentica<wbr>tion.php?126<wbr>70957_f35b53<wbr>fba610c8f369<wbr>0c5d8874f4e1<wbr>82</a><br><br>※After the verification, you will be able to submit your artworks and comments.<br><br>※ Please do not reply to this email address. For support related services, please refer to the<br>help page at <a target='_blank' href='http://www.pixiv.net/help.php'>http://www.p<wbr>ixiv.net/hel<wbr>p.php</a><br>for details. Thank you.<br><br><br>+‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥+<br>pixiv事务局<br><a target='_blank' href='http://www.pixiv.net/'>http://www.p<wbr>ixiv.net/</a><br><br>“不要吹灭你的灵感和你的想象力; 不要成为你的模型的奴隶。”<br>&nbsp; ー文森特・梵高<br>+‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥‥+<br>";
		Properties properties = new Properties();
		// properties.put("mail.smtp.host", "smtp.163.com");
		properties.put("mail.smtp.host", "smtp.sina.com");
		//properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.auth", "true");
		//properties.put("mail.smtp.ssl.enable", "true");
		//properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

		Authenticator authenticator = new Email_Authenticator(
				"qkyf123qq@sina.com", "w6563513");
		javax.mail.Session sendMailSession = javax.mail.Session
				.getDefaultInstance(properties, authenticator);
		sendMailSession.setDebug(true);
		MimeMessage mailMessage = new MimeMessage(sendMailSession);
		mailMessage.setFrom(new InternetAddress("qkyf123qq@sina.com"));
		// Message.RecipientType.TO属性表示接收者的类型为TO
		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(
				to));
		// 邮件的标题
		mailMessage.setSubject(subject);
		// 邮件的文本内容
		mailMessage.setContent(content, "text/html;charset=UTF-8");
		// 返回创建好的邮件对象
		// 5、发送邮件
		// 发送消息
		Transport.send(mailMessage);
	}
}

class Email_Authenticator extends Authenticator {
	String userName = null;
	String password = null;

	public Email_Authenticator() {
	}

	public Email_Authenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}
