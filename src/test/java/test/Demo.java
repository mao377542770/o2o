package test;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class Demo {
	@Test
    public void test() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        sender.setHost("smtp.qq.com");
        sender.setPort(465);
        sender.setUsername("377542770@qq.com");
        sender.setPassword("vkhgzdxfojfzbiee"); // 这里要用邀请码，不是你登录邮箱的密码

        Properties pro = System.getProperties(); // 下面各项缺一不可
        pro.put("mail.smtp.auth", "true");
        pro.put("mail.smtp.ssl.enable", "true");
        pro.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        sender.setJavaMailProperties(pro);

        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("377542770@qq.com"); // 发送人 
            helper.setTo("15288397286@163.com"); // 收件人  
            helper.setSubject("我只是稍微测试一下能不能用啊"); // 标题
            helper.setText("我只是稍微测试一下能不能用啊"); // 内容
            sender.send(message);
            System.out.println("发送完毕！");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}