package com.sid.employee_management.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record EmployeeRequestDto(

        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name cannot exceed 100 characters")
        String name,

        @NotNull(message = "Age is required")
        @Min(value = 18, message = "Age must be at least 18")
        Integer age,

        @NotNull(message = "Salary is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than zero")
        BigDecimal salary,

        @NotBlank(message = "Department is required")
        @Size(max = 100, message = "Department cannot exceed 100 characters")
        String department
){


}
