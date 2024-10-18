package com.study.student.payload;

public class EmailResponse {
	
	private boolean success;
	
	private String message;
	
	private String jobId;
	
	private String jobGroup;
	
	public EmailResponse(Boolean success, String message) {
		this.success=success;
		this.message=message;
	}
	
	public EmailResponse(Boolean success ,String jobId, String jobGroup, String message) {
		this.success=success;
		this.jobId=jobId;
		this.jobGroup=jobGroup;
		this.message=message;
		
		
	}

}
