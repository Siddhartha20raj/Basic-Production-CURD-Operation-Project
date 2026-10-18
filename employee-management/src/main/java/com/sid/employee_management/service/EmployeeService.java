package com.sid.employee_management.service;

import com.sid.employee_management.dto.EmployeeRequestDto;
import com.sid.employee_management.dto.EmployeeResponseDto;
import com.sid.employee_management.entity.Employee;
import com.sid.employee_management.exception.ResourceNotFoundException;
import com.sid.employee_management.respository.EmployeeRepository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // GET: fetch all employees using DTO projection
    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAllProjected();
    }

    // GET: Fetch one employee using DTO projection
    @Transactional(readOnly = true)
    public EmployeeResponseDto getEmployeeById(Long id) {
        return employeeRepository.findProjectedById(id).orElseThrow(()-> new ResourceNotFoundException(
                "Employee not found with id: " + id
        ));
    }

    // POST: Create employee

    @Transactional
    public EmployeeResponseDto createEmployee(@Valid EmployeeRequestDto request) {
        Employee employee = new Employee(
                request.name(),
                request.age(),
                request.salary(),
                request.department()
        );

        Employee saveEmployee = employeeRepository.save(employee);
        return toResponseDto(employee);
    }

    // PUT: Update employee
    @Transactional
    public EmployeeResponseDto updateEmployee(Long id, @Valid EmployeeRequestDto request) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee not found with id: " + id));
        employee.setName(request.name());
        employee.setAge(request.age());
        employee.setSalary(request.salary());
        employee.setDepartment(request.department());

        Employee updatedEmployee = employeeRepository.save(employee);

        return toResponseDto(updatedEmployee);
    }

    // DELETE: Remove employee

    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }



    // Convert entity to response DTO
    private EmployeeResponseDto toResponseDto(Employee employee) {
        return new EmployeeResponseDto(
                employee.getId(),
                employee.getName(),
                employee.getAge(),
                employee.getSalary(),
                employee.getDepartment()

        );
    }

}
