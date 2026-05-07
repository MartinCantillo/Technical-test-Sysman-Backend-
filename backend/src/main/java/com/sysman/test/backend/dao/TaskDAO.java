package com.sysman.test.backend.dao;

import com.sysman.test.backend.model.Task;
import com.sysman.test.backend.util.DatabaseConnection;

import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TaskDAO {

    public List<Task> getAllTasks() {

        List<Task> tasks = new ArrayList<>();

        try (
                Connection connection = DatabaseConnection.getConnection();
                CallableStatement callableStatement = connection.prepareCall(
                        "{ call TASK_PKG.GET_ALL_TASKS(?) }"
                )
        ) {

            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);

            callableStatement.execute();

            ResultSet rs = (ResultSet) callableStatement.getObject(1);

            while (rs.next()) {

                Task task = new Task();

                task.setTaskId(rs.getLong("TASK_ID"));
                task.setTitle(rs.getString("TITLE"));
                task.setDescription(rs.getString("DESCRIPTION"));
                task.setCompleted(rs.getInt("COMPLETED"));
                task.setCreatedAt(rs.getTimestamp("CREATED_AT"));
                task.setUpdatedAt(rs.getTimestamp("UPDATED_AT"));

                tasks.add(task);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tasks;
    }

    public Task getTaskById(Long taskId) {

        Task task = null;

        try (
                Connection connection = DatabaseConnection.getConnection();
                CallableStatement callableStatement = connection.prepareCall(
                        "{ call TASK_PKG.GET_TASK_BY_ID(?, ?) }"
                )
        ) {

            callableStatement.setLong(1, taskId);

            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

            callableStatement.execute();

            ResultSet rs = (ResultSet) callableStatement.getObject(2);

            if (rs.next()) {

                task = new Task();

                task.setTaskId(rs.getLong("TASK_ID"));
                task.setTitle(rs.getString("TITLE"));
                task.setDescription(rs.getString("DESCRIPTION"));
                task.setCompleted(rs.getInt("COMPLETED"));
                task.setCreatedAt(rs.getTimestamp("CREATED_AT"));
                task.setUpdatedAt(rs.getTimestamp("UPDATED_AT"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return task;
    }

    public boolean createTask(Task task) {

        try (
                Connection connection = DatabaseConnection.getConnection();
                CallableStatement callableStatement = connection.prepareCall(
                        "{ call TASK_PKG.CREATE_TASK(?, ?, ?) }"
                )
        ) {

            System.out.println(" title = " + task.getTitle());
            System.out.println(" description = " + task.getDescription());
            System.out.println(" completed = " + task.getCompleted());

            callableStatement.setString(1, task.getTitle());
            callableStatement.setString(2, task.getDescription());
            callableStatement.setInt(3, task.getCompleted());

            callableStatement.execute();

            System.out.println(" Procedure executed OK");

            return true;

        } catch (Exception e) {
            System.out.println(" ERROR REAL:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateTask(Task task) {

        try (
                Connection connection = DatabaseConnection.getConnection();
                CallableStatement callableStatement = connection.prepareCall(
                        "{ call TASK_PKG.UPDATE_TASK(?, ?, ?, ?) }"
                )
        ) {

            callableStatement.setLong(1, task.getTaskId());
            callableStatement.setString(2, task.getTitle());
            callableStatement.setString(3, task.getDescription());
            callableStatement.setInt(4, task.getCompleted());

            callableStatement.execute();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteTask(Long taskId) {

        try (
                Connection connection = DatabaseConnection.getConnection();
                CallableStatement callableStatement = connection.prepareCall(
                        "{ call TASK_PKG.DELETE_TASK(?) }"
                )
        ) {

            callableStatement.setLong(1, taskId);

            callableStatement.execute();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}