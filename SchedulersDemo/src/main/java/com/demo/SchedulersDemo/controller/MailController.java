package com.demo.SchedulersDemo.controller;

import jakarta.mail.Multipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.SchedulersDemo.EmailSender;
import com.demo.SchedulersDemo.request.EmailRequest;
import com.demo.SchedulersDemo.request.MailRequest;
import com.demo.SchedulersDemo.response.CustomMessage;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/mail")
public class MailController {

	@Autowired
	private EmailSender senderService;

	
	
//	public MailController(EmailSender senderService) {
////		super();
//		this.senderService = senderService;
//	}



	@PostMapping("/send-mail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){
		senderService.sendEmail(request.getTo(), request.getSubject(), request.getMessage());
		System.out.println("hello");
		return ResponseEntity.ok(CustomMessage.builder().message("email sent successfully").httpStatus(HttpStatus.OK).success(true).build());
	}

	@PostMapping(value = "send-mail-with-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
	public ResponseEntity<?> sendEmailWithFile( @RequestPart MultipartFile file, MailRequest request){
        try {
            senderService.sendEmailWithFileInputstream(request.getTo(), request.getSubject(), request.getMessage(), file.getInputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("hello");
		return ResponseEntity.ok(CustomMessage.builder().message("email sent successfully").httpStatus(HttpStatus.OK).success(true).build());
	}

}
