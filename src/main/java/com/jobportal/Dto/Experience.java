package com.jobportal.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experience {
    private String title;
    private String company;
    private String location;
    @DateTimeFormat()
    private LocalDate startDate;
    @DateTimeFormat(        )
    private LocalDate endDate;
    private Boolean working;
    private String description;
}
