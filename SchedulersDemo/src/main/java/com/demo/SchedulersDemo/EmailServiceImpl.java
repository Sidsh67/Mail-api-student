package com.demo.SchedulersDemo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailSender {

	@Autowired
	private JavaMailSender mailSender;

	private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	public EmailServiceImpl(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}

	@Override
	public void sendEmail(String to, String subject, String message) {
		System.out.println(to + " " + subject + " " + message);

		SimpleMailMessage simpleMail = new SimpleMailMessage();
		simpleMail.setTo(to);
		simpleMail.setSubject(subject);
		simpleMail.setText(message);
		simpleMail.setFrom("sidsh67@gmail.com");
		mailSender.send(simpleMail);

		logger.info("message has been sent");

	}

	@Override
	public void sendEmail(String[] to, String subject, String message) {
		SimpleMailMessage simpleMail = new SimpleMailMessage();
		simpleMail.setTo(to);
		simpleMail.setText(message);
		simpleMail.setSubject(subject);
		simpleMail.setFrom("sidsh67@gmail.com");
		mailSender.send(simpleMail);

	}

	@Override
	public void sendEmailWithFile(List<String> to, String subject, String message, File file) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

			for (String sentTo : to) {
				messageHelper.setFrom("sidsh67@gmail.com");
				messageHelper.setSubject(subject);
				System.out.println(sentTo);
				messageHelper.setTo(sentTo);

				messageHelper.setText(message);

				FileSystemResource fileSystem = new FileSystemResource(file);

				messageHelper.addAttachment(fileSystem.getFilename(), file);

				mailSender.send(mimeMessage);
			}
			logger.info("Email send successfull");

		} catch (MessagingException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void sendEmailWithHtml(String to, String subject, String htmlContent) {

		MimeMessage simpleMessage = mailSender.createMimeMessage();

		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(simpleMessage, true, "UTF-8");
			messageHelper.setSubject(subject);
			messageHelper.setTo(to);
			messageHelper.setText(htmlContent, true);

			mailSender.send(simpleMessage);
			logger.info("Email send successfully");

		} catch (MessagingException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void sendEmailWithFileInputstream(List<String> to, String subject, String message, InputStream is) {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

			messageHelper.setFrom("sidsh67@gmail.com");
			messageHelper.setSubject(subject);
			

			messageHelper.setText(message);

			File file = new File("C:\\\\Users\\\\prashant.sharma\\\\Downloads\\\\nature1.jpg");

			Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			FileSystemResource fs=new FileSystemResource(file);
			messageHelper.addAttachment(fs.getFilename(),file);
			for (String sentTo : to) {
				
				
				System.out.println(sentTo);
				messageHelper.setTo(sentTo);
				mailSender.send(mimeMessage);
			}
			logger.info("Email send successfull");

		} catch (MessagingException e) {

			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
