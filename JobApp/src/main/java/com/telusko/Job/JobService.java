    package com.telusko.Job;

    import java.util.List;


    public interface JobService {
    List<Job> findAll();
    void createJob(Job job);

        Job getJobById(Integer id);

        Boolean deleteJobById(Integer id);

        Job updatedJobById(Integer id, Job updatedJob);
    }
