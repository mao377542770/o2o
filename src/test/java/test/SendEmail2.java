package test;

//需要用户名密码邮件发送实例
//文件名 SendEmail2.java
//本实例以QQ邮箱为例，你需要在qq后台设置

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class SendEmail2 {

	@Test
	public void test() {
		// 收件人电子邮箱
		String to = "377542770@qq.com";

		// 发件人电子邮箱
		String from = "15288397286@163.com";

		// 指定发送邮件的主机为 localhost
		String host = "smtp.163.com"; // QQ 邮件服务器

		// 获取系统属性
		Properties properties = new Properties();

		// 设置邮件服务器
		properties.put("mail.stmp.host", host);
		// properties.put("mail.stmp.port",465);
		properties.put("mail.smtp.auth", "true");
		// 获取默认session对象
		Session session = Session.getDefaultInstance(properties,
				new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"15288397286@163.com", "wu6563513"); // 发件人邮件用户名、密码
					}
				});
		session.setDebug(true);

		try {
			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);

			// Set From: 头部头字段
			message.setFrom(new InternetAddress(from));

			// Set To: 头部头字段
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));

			// Set Subject: 头部头字段
			message.setSubject("This is the Subject Line!");

			// 设置消息体
			message.setText("This is actual message");

			// 发送消息
			Transport.send(message);
			System.out
					.println("Sent message successfully....from w3cschool.cc");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}