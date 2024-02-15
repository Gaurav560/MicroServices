package com.telusko.Job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    private JobService jobService;


    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll() {
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }


    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job Added Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/Job/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Integer id) {
        Job job = jobService.getJobById(id);
        if (job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Job/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Integer id) {
        if (jobService.deleteJobById(id)) {
            return new ResponseEntity<>("Job Deleted Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Job Id not found", HttpStatus.OK);
        }
    }


    @PutMapping("/Job/{id}")
    public ResponseEntity<Job> updateJobById(@PathVariable Integer id, @RequestBody Job updatedJob) {
        Job job = jobService.updatedJobById(id, updatedJob);
        if (job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}



