package com.demo.SchedulersDemo;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.swing.text.html.HTML;

public interface EmailSender {

//	For Single person
	void sendEmail(String to, String subject, String message);
	
//	For multiple person
	void sendEmail(String[] to, String subject, String message);
	
//	send Email with file
	void sendEmailWithFile(List<String> to, String subject, String message, File file );
	
//	send Email with file using InputStream
	void sendEmailWithFileInputstream(List<String> to, String subject, String message, InputStream is );
	
//	send email with html
	void sendEmailWithHtml(String to , String subject, String htmlContent);
}
