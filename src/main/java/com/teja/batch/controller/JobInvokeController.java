package com.teja.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobInvokeController {

  @Autowired JobLauncher jobLauncher;

  @Autowired Job processJob;

  @RequestMapping("invokeJob")
  public String handle() {
    try {
      JobParameters jobParameters =
          new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
      jobLauncher.run(processJob, jobParameters);
      return "<h2>Job Launched Succesfully..!<h2>";
    } catch (Exception e) {
      return "<h2>Job Launch Failed..!<h2>";
    }
  }
}
