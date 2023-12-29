package com.example.extodoapp.dao;

import com.example.extodoapp.model.Job;

import java.util.List;

public interface IJobDao {
    List<Job> selectAllJob();

    void createJob(Job job);

    void updJob(Job job);

    Job findById(int id);
}
