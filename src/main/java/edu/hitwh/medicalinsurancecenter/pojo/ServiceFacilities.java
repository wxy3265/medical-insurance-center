package edu.hitwh.medicalinsurancecenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceFacilities {

    private long id;
    private String name;
    private short expType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean valid;

}
