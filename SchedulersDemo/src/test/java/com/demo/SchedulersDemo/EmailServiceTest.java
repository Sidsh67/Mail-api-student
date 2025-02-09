package com.demo.SchedulersDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.inject.Inject;

@SpringBootTest
public class EmailServiceTest {

	@Inject
	EmailSender serviceImpl;

//	@Test
//	void EmailServiceTest() {
//
//		serviceImpl.sendEmail("sidhantbhargav1@gmail.com", "demo mail", "thats my code mail");
//		System.out.println("sending email");
//
//	}

//	@Test
//	void emailServiceWithfileTest() {
//
//		System.out.println("send");
//
//		List<String> sendTo = Arrays.asList("sidhantbhargav1@gmail.com", "akhtarvaseem79@gmail.com",
//				"sumit.mishra@in2ittech.com\r\n" + " ");
//
//		serviceImpl.sendEmailWithFile(sendTo, "file send", "this email contains file", new File(
//				"D:\\JavaSchedulers\\SchedulersDemo\\src\\main\\resources\\static\\pexels-souvenirpixels-417074.jpg"));
//
//	}

//	@Test
//	void emailServiceWithfileTest() {
//		System.out.println("send");
//		serviceImpl.sendEmailWithFile("sidhantbhargav1@gmail.com", "file send", "this email contains file",
//				new File("C:\\Users\\prashant.sharma\\Downloads\\nature1.jpg"));
//
//	}

//	@Test
//	void emailServiceWithfileTestusingInputstream() {
//
//		System.out.println("send");
//
//		File file = new File("D:\\JavaSchedulers\\SchedulersDemo\\src\\main\\resources\\static\\pexels-souvenirpixels-417074.jpg");
//
//		try {
//			InputStream is = new FileInputStream(file);
//
//			List<String> sendTo = Arrays.asList( "akhtarvaseem79@gmail.com",
//					"sumit.mishra@in2ittech.com","emailforgenraluse@gmail.com","aniketkaushik22@gmail.com","sidhantbhargav1@gmail.com\r\n" + " ");
//
//			serviceImpl.sendEmailWithFileInputstream(sendTo, "file send", "this email contains file", is);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	@Test
//	void emailServiceWithHtml() {
//		
//		String html=
//				"<H1 style= 'color : red;'border: 1px solid red;'>Welcome to my page</H1>"+" ";
//		
//		serviceImpl.sendEmailWithHtml("sidhantbhargav1@gmail.com", "thats my subject", html);
//	}

}
