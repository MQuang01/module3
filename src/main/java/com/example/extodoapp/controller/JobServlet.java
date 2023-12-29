package com.example.extodoapp.controller;


import com.example.extodoapp.dao.JobDao;
import com.example.extodoapp.model.Job;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "jobs", value = "/jobs")
public class JobServlet extends HttpServlet {
    private JobDao jobDao;

    @Override
    public void init() {
        jobDao = new JobDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("act");
        if (action == null){
            listJob(req, resp);
        }
        if (Objects.equals(action, "checked")){
            updateStatus(req, resp);
        }
    }

    private void listJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Job> jobList = jobDao.selectAllJob();
        req.setAttribute("jobs", jobList);

        req.getRequestDispatcher("job/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("act");
        if (Objects.equals(action, "create")){
            createJob(req,resp);
        }

    }

    private void updateStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Job job = jobDao.findById(id);
        jobDao.updJob(job);

        resp.sendRedirect("/jobs");
    }

    private void createJob(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Job job = new Job(req.getParameter("description"), LocalTime.parse(req.getParameter("time")));
        jobDao.createJob(job);

        resp.sendRedirect("/jobs");
    }
}
