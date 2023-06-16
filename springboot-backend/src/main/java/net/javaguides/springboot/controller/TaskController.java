package net.javaguides.springboot.controller;

import java.util.List;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.Task;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost: 3000")
@RestController
@RequestMapping("/api/")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employee/{employeeId}/tasks")
    public ResponseEntity<List<Task>> getAllTasksByEmployee(@PathVariable(value = "employeeId") Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Not found = " + employeeId);
        }

        List<Task> tasks = taskRepository.findByEmployeeId(employeeId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTasksByEmployeeId(@PathVariable(value = "id") Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found task"));
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

//    @PostMapping("/employee/{employeeId}/tasks")
//    public Task createTask(@PathVariable(value = "employeeId") Long employeeId, @RequestBody Task taskRequest){
//        Employee employee =  employeeRepository.findById(employeeId)
//                .orElseThrow(()-> new ResourceNotFoundException("Not found task= " + employeeId));
//        taskRequest.setTask(employee);
//        return taskRepository.save(taskRequest);
//    }

    @PutMapping("/Tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") long id, @RequestBody Task TaskRequest) {
        Task Task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TaskId " + id + "not found"));

        Task.setTaskContent(TaskRequest.getTaskContent());
        return new ResponseEntity<>(taskRepository.save(Task), HttpStatus.OK);
    }

    @DeleteMapping("/Tasks/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") long id) {
        taskRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/employees/{employeeId}/Tasks")
    public ResponseEntity<List<Task>> deleteAllTasksOfEmployee(@PathVariable(value = "employeeId") Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Not found employee with id = " + employeeId);
        }

        taskRepository.deleteByEmployeeId(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/myapplication/{id}")
    public ResponseEntity<Task> onlyOne(@PathVariable Long id){
        Task employee = taskRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Not found"));
        return  ResponseEntity.ok(employee);
    }
}

