package com.telusko.Job.impl;

import com.telusko.Job.Job;
import com.telusko.Job.JobRepo;
import com.telusko.Job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService
{

    private final JobRepo jobRepo;

    public JobServiceImpl(JobRepo jobRepo) {
        this.jobRepo = jobRepo;
    }


    @Override
    public List<Job> findAll() {
        return jobRepo.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepo.save(job);
    }

    @Override
    public Job getJobById(Integer id) {
        return jobRepo.findById(id).get();
    }

    @Override
    public Boolean deleteJobById(Integer id) {

        jobRepo.deleteById(id);
        return true;
    }


    @Override
    public Job updatedJobById(Integer id, Job updatedJob) {
        Job job = jobRepo.findById(id).get();
        job.setMinSalary(updatedJob.getMinSalary());
        job.setMaxSalary(updatedJob.getMaxSalary());
        job.setLocation(updatedJob.getLocation());
        job.setDescription(updatedJob.getDescription());
        job.setTitle(updatedJob.getTitle());
        jobRepo.save(job);
        return job;

    }

}






