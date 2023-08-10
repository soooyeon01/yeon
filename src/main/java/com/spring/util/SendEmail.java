package com.spring.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	
	public static void naverMailSend(String email, String subject, String text) {
        String host = "smtp.naver.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
        String user = "ptiael@naver.com"; // 패스워드
        String password = "yesol1101s2";   
        String fromName = "옥독캣";

        // SMTP 서버 정보를 설정한다.
        Properties props = new Properties();
        
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.naver.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user, fromName));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            // 메일 제목
            message.setSubject(subject);

            // 메일 내용
            message.setText(text);

            // send the message
            Transport.send(message);
            System.out.println("Success Message Send");

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}