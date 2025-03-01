package com.example.springboot_dynamoDB.service;

import com.example.springboot_dynamoDB.model.Task;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final DynamoDbTable<Task> taskTable;

    // Constructor Injection for DynamoDbEnhancedClient
    public TaskServiceImpl(DynamoDbClient dynamoDbClient) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        this.taskTable = enhancedClient.table("Task", TableSchema.fromBean(Task.class));
    }

    @Override
    public void addTask(Task task) {
        taskTable.putItem(task);
    }

    @Override
    public void deleteTaskByTitle(String title) {
        Task taskToDelete = new Task();
        taskToDelete.setTitle(title);
        taskTable.deleteItem(taskToDelete);
    }

    @Override
    public void updateTaskByTitle(String title, Task task) {
        task.setTitle(title);
        taskTable.putItem(task); // Overwrites the existing item
    }
    @Override
    public List<Task> getAllTasks() {
        // Perform a scan operation to get all items from the DynamoDB table
        return taskTable.scan()
                .items()  // Extracts the list of items (tasks)
                .stream()  // Stream the items for further processing
                .collect(Collectors.toList());  // Collect the stream into a list
    }

    @Override
    public Page<Task> getAllTasksPage(int pageNo, int pageSize) {
        // Paginated scan request
        ScanEnhancedRequest scanRequest = ScanEnhancedRequest.builder()
                .limit(pageSize)
                .exclusiveStartKey(null)  // You can add a LastEvaluatedKey here to continue scanning after the first page
                .build();

        // Perform the scan operation and return the first page
        return taskTable.scan(scanRequest).iterator().next(); // Get the first page of results
    }

    @Override
    public void deleteTask(String title) {
        deleteTaskByTitle(title);
    }
}
