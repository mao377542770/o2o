package test;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {
	// 指定发件人邮箱
	public static final String FROM = "mail.ugfind.com";
	//指定发件人smtp 地址
	public static final String SMTPFROM = "smtp.mxhichina.com";
	// 发件人用户名
	private static final String USERNAME = "service@ugfind.com";
	// 发件人密码
	private static final String PASSWORD = "UGyunnan0871";

	// 获取系统属性
	public static Properties properties = System.getProperties();

	static {
		properties.setProperty("mail.stmp.host",SMTPFROM);
		//properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");
	}

	// 获取默认session对象
	public static Session session = Session.getDefaultInstance(properties, new Authenticator() {
		 public PasswordAuthentication getPasswordAuthentication()
		    {
		     return new PasswordAuthentication(USERNAME,PASSWORD); //发件人邮件用户名、密码
		    }
	});
	
	
	// 这里指定收件人的邮箱
	private String to;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public JavaMailUtil() throws Exception {
		// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(true);
	}

	/**
	 * 发送邮件的方法
	 * @param to 指定收件人地址
	 * @param subject 指定主题
	 * @param msg 指定html 格式内容
	 * @throws Exception
	 */
	public void sendMail(String to,String subject,String msg)
			throws Exception {
		// 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		// 指明邮件的发件人
		message.setFrom(new InternetAddress(FROM));
		// 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(
				to));
		// 邮件的标题
		message.setSubject(subject);
		// 邮件的文本内容
		message.setContent(msg, "text/html;charset=UTF-8");
		// 返回创建好的邮件对象
		// 5、发送邮件
		// 发送消息
        Transport.send(message);
	}

}
