package com.study.student.web;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.student.job.EmailJob;
import com.study.student.payload.EmailRequest;
import com.study.student.payload.EmailResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/scheduler/email")
public class EmailScedulerController {

	private Scheduler scheduler;
	
	@PostMapping("/post")
	public ResponseEntity<EmailResponse> scheduleEmail(@Valid @RequestBody EmailRequest emailRequest){
		try {
			ZonedDateTime dateTime= ZonedDateTime.of(emailRequest.getDateTime(), emailRequest.getTimeZone());
			if(dateTime.isBefore(ZonedDateTime.now())) {
				EmailResponse emailResponse=new EmailResponse(false, "Delete time must be after current time");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emailResponse);
			}
			JobDetail jobDetail=buildjobDetail(emailRequest);
			Trigger trigger=buildTriggers(jobDetail, dateTime);
			
			scheduler.scheduleJob(jobDetail,trigger);
			
			EmailResponse response=new EmailResponse(true, jobDetail.getKey().getName(), 
					jobDetail.getKey().getGroup(), "Email Schedule Successfully");
			
			return ResponseEntity.ok(response);
		}
		catch (SchedulerException e) {
			EmailResponse emailResponse=new EmailResponse(false, "Error while scheduling email.Please try again later!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emailResponse);
		}
		
	}
	
	private JobDetail buildjobDetail(EmailRequest scheduleEmailRequest) {
		
		JobDataMap jobDataMap=new JobDataMap();
		
		jobDataMap.put("email", scheduleEmailRequest.getEmail());
		jobDataMap.put("body", scheduleEmailRequest.getBody());
		jobDataMap.put("subject", scheduleEmailRequest.getSubject());
		
		return JobBuilder.newJob(EmailJob.class)
				.withIdentity(UUID.randomUUID().toString(),"email-job").
				withDescription("Send Email Job")
				.usingJobData(jobDataMap)
				.storeDurably().
				build();
		
	}
	
	private Trigger buildTriggers(JobDetail jobDetail, ZonedDateTime startAt) {
		
		return TriggerBuilder.newTrigger()
				.forJob(jobDetail)
				.withIdentity(jobDetail.getKey().getName(),"email-trigger")
				.withDescription("Send all triggers")
				.startAt(Date.from(startAt.toInstant()))
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
				.build();
		
	}
}
