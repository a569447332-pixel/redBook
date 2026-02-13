package com.ruoyi.common.utils;

import com.ruoyi.common.config.EmailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class EmailUtil {

    @Autowired
    private EmailProperties emailProperties;

    /**
     * 发送验证码到邮箱
     */
    public void sendVerifyCode(String toEmail, String code) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", emailProperties.getSmtpHost());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", emailProperties.getSmtpPort());
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        emailProperties.getQq(),
                        emailProperties.getCode()
                );
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailProperties.getQq()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("【若依系统】登录验证码");
        message.setText("你的登录验证码是：" + code + "，有效期5分钟，请及时使用。");

        Transport.send(message);
    }
}