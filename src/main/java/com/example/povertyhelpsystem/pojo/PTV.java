package com.example.povertyhelpsystem.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PTV {
    private String povertyName;
    private String povertyId;
    private String need;
    private String volunteerName;
    private String volunteerId;
    private String help;

}
