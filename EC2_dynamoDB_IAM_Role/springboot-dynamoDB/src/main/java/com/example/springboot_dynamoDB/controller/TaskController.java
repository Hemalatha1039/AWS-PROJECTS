package com.example.springboot_dynamoDB.controller;

//import org.springframework.data.domain.Page;
import com.example.springboot_dynamoDB.model.Task;
import com.example.springboot_dynamoDB.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;


import java.util.List;

@Controller
@RequestMapping()
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String viewIndexPage() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String viewHome(Model model) {
        model.addAttribute("task", new Task());
        return findPaginated(1, model); // Start at page 1
    }

    // Paginate tasks
    @GetMapping("/home/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 6;

        // Get the paginated tasks from service
        Page<Task> page = taskService.getAllTasksPage(pageNo, pageSize);

        List<Task> tasks = page.items();  // Extract tasks from the page

        // Handle cases when there are no tasks
        if (tasks.isEmpty()) {
            model.addAttribute("noTasks", true);
            if (pageNo > 1) {
                return "redirect:/home/" + (pageNo - 1);  // Redirect to the previous page if no tasks found
            }
        } else {
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPages", getTotalPages(page));  // Calculate the total pages manually
            model.addAttribute("totalItems", tasks.size());
            model.addAttribute("tasks", tasks);

            // Redirect if page number is out of bounds
            if (pageNo > getTotalPages(page)) {
                return "redirect:/home/" + getTotalPages(page);
            }
            if (pageNo < 1) {
                return "redirect:/home";
            }
        }

        return "home";  // The page where tasks are displayed
    }

    // Calculate the total pages based on the number of items and page size
    private int getTotalPages(Page<Task> page) {
        int totalItems = page.items().size();
        int pageSize = 6; // Define your page size
        return (int) Math.ceil((double) totalItems / pageSize);
    }

    // Create a new task using AJAX request
    @PostMapping("/home")
    @ResponseBody
    public ResponseEntity<List<Task>> createTask(@RequestBody Task task) {
        taskService.addTask(task);
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    // Delete a task using AJAX request
    @DeleteMapping("/home/{title}")
    public ResponseEntity<Void> deleteTask(@PathVariable String title) {
        taskService.deleteTask(title);
        return ResponseEntity.ok().build();
    }

    // Error handling page
    @GetMapping("/error")
    public String viewErrorPage() {
        return "error";
    }
}
