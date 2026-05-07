package com.sysman.test.backend.service;

import com.sysman.test.backend.dao.TaskDAO;
import com.sysman.test.backend.model.Task;


import java.util.List;

public class TaskService {



    private final TaskDAO taskDAO = new TaskDAO();

    public List<Task> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    public Task getTaskById(Long taskId) {
        return taskDAO.getTaskById(taskId);
    }

    public boolean createTask(Task task) {
        return taskDAO.createTask(task);
    }

    public boolean updateTask(Task task) {
        return taskDAO.updateTask(task);
    }

    public boolean deleteTask(Long taskId) {
        return taskDAO.deleteTask(taskId);
    }
}