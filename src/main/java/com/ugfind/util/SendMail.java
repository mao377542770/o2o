package com.ugfind.util;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;



public class SendMail {
	
	private static final String hostFrom = "ugfind@sina.com";
	private static final String PASSWORD = "0871ugfind";
	
    private static final Authenticator authenticator = new Email_Authenticator(
    		hostFrom, PASSWORD);
    public static Session sendMailSession;
    
    /**
     * 该方法是单纯发送邮件的
     * @param to
     * @param subject
     * @param content
     * @throws IOException
     * @throws AddressException
     * @throws MessagingException
     */
	public static void send_email(String to,String subject,String content) throws IOException, AddressException,
			MessagingException {
		
		//如果需要更换类型,则需要解开注释,根据不同邮箱     设置不同
		Properties properties = new Properties();
		// properties.put("mail.smtp.host", "smtp.163.com");
		properties.put("mail.smtp.host", "smtp.sina.com");
		//properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.auth", "true");
		//properties.put("mail.smtp.ssl.enable", "true");
		//properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

		if(sendMailSession == null){
			sendMailSession = Session
					.getDefaultInstance(properties, authenticator);
		}
		sendMailSession.setDebug(true);  //开启调试模式
		
		MimeMessage mailMessage = new MimeMessage(sendMailSession);
		mailMessage.setFrom(new InternetAddress(hostFrom));
		// Message.RecipientType.TO属性表示接收者的类型为TO
		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		// 邮件的标题
		mailMessage.setSubject(subject);
		// 邮件的文本内容
		mailMessage.setContent(content, "text/html;charset=UTF-8");
		// 返回创建好的邮件对象
		// 5、发送邮件
		// 发送消息
		Transport.send(mailMessage);
	}
	
	
	/**
	 * 验证密码发送邮件
	 * @param session 传入session
	 * @param to 传入收件人地址
	 * @throws IOException 
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public static void send_email(HttpSession session, String to)
			throws IOException, AddressException, MessagingException {

		String code = SendMail.getRandomString(6);
		String content = "您好,"+to+"<br>"+
	 			"根据您的需要,我们已经收到了您的修改密码请求,请在验证码部分填以下验证码:<br>"+
 			    "<h1>"+code+"</h1><br>"+
	 			"<br><br><br><br><br><br>"
	 			+ "-----优超科技有限公司-----";
		

		// 如果需要更换类型,则需要解开注释,根据不同邮箱 设置不同
		Properties properties = new Properties();
		// properties.put("mail.smtp.host", "smtp.163.com");
		properties.put("mail.smtp.host", "smtp.sina.com");
		// properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.auth", "true");
		// properties.put("mail.smtp.ssl.enable", "true");
		// properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

		if (sendMailSession == null) {
			sendMailSession = Session.getDefaultInstance(properties,
					authenticator);
		}
		sendMailSession.setDebug(true); // 开启调试模式

		MimeMessage mailMessage = new MimeMessage(sendMailSession);
		mailMessage.setFrom(new InternetAddress(hostFrom));
		// Message.RecipientType.TO属性表示接收者的类型为TO
		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(
				to));
		// 邮件的标题
		mailMessage.setSubject("修改密码验证");
		// 邮件的文本内容
		mailMessage.setContent(content, "text/html;charset=UTF-8");
		// 返回创建好的邮件对象
		// 5、发送邮件
		// 发送消息
		Transport.send(mailMessage);
		session.setAttribute("changPwdCode", code);//保存好session
	}
	
	
	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
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
