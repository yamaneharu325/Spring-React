package net.javaguides.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Employee employee;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_type")
    private String taskType;

    @Column(name = "task_content")
    private String taskContent;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "task_completeness")
    private int taskCompleteness;

//    private Task task;

    public Task(){

    }

    public Task(String taskName, String taskType, String taskContent, Date startDate, Date endDate, int taskCompleteness){
        super();
        this.taskName = taskName;
        this.taskType = taskType;
        this.taskContent = taskContent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.taskCompleteness = taskCompleteness;
    }

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public String getTaskName(){
        return taskName;
    }
    public void setTaskName(String taskName){
        this.taskName = taskName;
    }
    public String getTaskType(){
        return taskType;
    }
    public void setTaskType(String taskType){
        this.taskType = taskType;
    }
    public String getTaskContent(){
        return taskContent;
    }
    public void setTaskContent(String taskContent){
        this.taskContent = taskContent;
    }
    public Date getStartDate(){
        return startDate;
    }
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }
    public Date getEndDate(){
        return endDate;
    }
    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }
    public int getTaskCompleteness(){
        return taskCompleteness;
    }
    public void setTaskCompleteness(int taskCompleteness){
        this.taskCompleteness = taskCompleteness;
    }

//    public void setTask(Task task){
//        this.task = task;
//    }
    public void setEmployee(Employee employee){
        this.employee = employee;
    }

}
