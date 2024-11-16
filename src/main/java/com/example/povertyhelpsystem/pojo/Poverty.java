package com.example.povertyhelpsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poverty {
    private Integer id;
    private String name;
    private Integer age;
    private Integer gender;
    private String povertyId;
    private LocalDate comeDate;
    private String volunteerId;
    private String need;
}
