package franc.HRMS.controllers;

import franc.HRMS.models.Job;
import franc.HRMS.services.IJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hrms")
public class JobController {
    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private IJobService jobService;


    @GetMapping("/jobs")
    public List<Job> getJobs() {
        logger.info("JobController getJobs() called.");
        List<Job> jobs = jobService.getAll();
        jobs.forEach(job -> logger.info(job.toString()));
        logger.info("Jobs have been listed successfully.");
        return jobs;
    }
}
