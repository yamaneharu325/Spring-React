package net.javaguides.springboot.repository;

import jakarta.transaction.Transactional;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>{
    List<Task> findByEmployee(Employee employee);

    @Transactional
    void deleteByEmployeeId(long employeeId);

    List<Task> findByEmployeeId(Long employeeId);
}
