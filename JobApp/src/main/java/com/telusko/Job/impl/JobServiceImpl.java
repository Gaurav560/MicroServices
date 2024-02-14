package com.telusko.Job.impl;

import com.telusko.Job.Job;
import com.telusko.Job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private List<Job> jobs = new ArrayList<>();
    private int nextId=1;


    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
job.setId(nextId);
 nextId++;
        jobs.add(job);
    }

}
