package com.example.spring02.service.email;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.spring02.model.email.EmailDTO;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Inject
	JavaMailSender mailSender;

	@Override
	public void sendMail(EmailDTO dto) {
		try {
			//이메일객체
			MimeMessage msg=mailSender.createMimeMessage();
			//받는 사람 이메일 주소
			msg.addRecipient(RecipientType.TO, 
					new InternetAddress(dto.getReceiveMail()));
			//보내는 사람(이멜주소+이름)
			msg.addFrom(new InternetAddress[] {
					new InternetAddress(dto.getSenderMail(),dto.getSenderName())
			});
			//이메일 제목
			msg.setSubject(dto.getSubject(), "utf-8");
			//이메일 본문
			msg.setText(dto.getMessage(), "utf-8");
			//이멜 보내기
			mailSender.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
