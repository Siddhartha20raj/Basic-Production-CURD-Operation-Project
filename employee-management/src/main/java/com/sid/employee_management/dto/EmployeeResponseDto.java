package com.sid.employee_management.dto;

import java.math.BigDecimal;

public record EmployeeResponseDto (
        Long id,
        String name,
        Integer age,
        BigDecimal salary,
        String department
){



}
