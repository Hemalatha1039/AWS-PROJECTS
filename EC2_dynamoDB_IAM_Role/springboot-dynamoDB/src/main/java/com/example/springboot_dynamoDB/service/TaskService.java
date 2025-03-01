package com.example.springboot_dynamoDB.service;

import com.example.springboot_dynamoDB.model.Task;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;

import java.util.List;

public interface TaskService {
    // Add task
    public void addTask(Task task);

    // Delete task
    public void deleteTaskByTitle(String title);

    // Update task by id
    public void updateTaskByTitle(String title, Task task);

    // Get all tasks
    public List<Task> getAllTasks();

    // Delete task by id
    public void deleteTask(String title);

    // Get task by page
    Page<Task> getAllTasksPage(int pageNo, int pageSize);
}
