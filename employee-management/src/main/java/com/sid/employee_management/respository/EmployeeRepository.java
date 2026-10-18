package com.sid.employee_management.respository;

import com.sid.employee_management.dto.EmployeeResponseDto;
import com.sid.employee_management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("""
    SELECT new com.sid.employee_management.dto.EmployeeResponseDto(
        e.id, e.name, e.age, e.salary, e.department
        )
        FROM Employee e ORDER By e.id
    """)
    List<EmployeeResponseDto> findAllProjected();


    @Query("""
    SELECT new com.sid.employee_management.dto.EmployeeResponseDto(
        e.id, e.name, e.age, e.salary, e.department
        )
        FROM Employee e Where e.id = :id
    """)
    Optional<EmployeeResponseDto> findProjectedById(@Param("id") long id);

}
