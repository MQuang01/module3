package com.example.extodoapp.dao;

import com.example.extodoapp.context.DBConnect;
import com.example.extodoapp.model.Job;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class JobDao extends DBConnect implements IJobDao{
    private static final String UPDATE_STATUS = "UPDATE `todoapp`.`jobs` SET `status` = '1' WHERE (`id` = ?);";
    private static final String SELECT_ALL_JOB = "select * from jobs order by status, `time`;";
    private static final String INSERT_JOB = "INSERT INTO `todoapp`.`jobs` (`description`, `time`) VALUES ( ?, ?);";
    private static final String SELECT_JOB = "select * from jobs where ( id = ? )";
    public JobDao(){
    }


    @Override
    public List<Job> selectAllJob() {
        List<Job> jobList = new ArrayList<>();

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_ALL_JOB)){
            ResultSet rs =preparedStatement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String description = rs.getString("description");
                LocalTime time = rs.getTime("time").toLocalTime();
                boolean status = rs.getBoolean("status");

                jobList.add(new Job(id, description, time, status));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jobList;
    }

    @Override
    public void createJob(Job job) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_JOB)){
            preparedStatement.setString(1, job.getDescription());
            preparedStatement.setTime(2, Time.valueOf(job.getTime()));
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updJob(Job job) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_STATUS)){
            preparedStatement.setInt(1 , job.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Job findById(int id) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_JOB)) {
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                int idJob = rs.getInt("id");
                String descriptionJob = rs.getString("description");
                LocalTime timeJob = rs.getTime("time").toLocalTime();
                boolean statusJob = rs.getBoolean("status");

                return new Job(idJob, descriptionJob, timeJob, statusJob);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
