package com.study.student.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class EmailJob extends QuartzJobBean {


	@Override
	protected void executeInternal(JobExecutionContext context) {
		
	};
}
