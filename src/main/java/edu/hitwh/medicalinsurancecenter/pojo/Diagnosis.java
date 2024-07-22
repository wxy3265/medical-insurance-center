package edu.hitwh.medicalinsurancecenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diagnosis {

    private long id;
    private String name;
    private short expType;
    private short expLevel;
    private int maxPrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean valid;
    private boolean hosLevel;
    private boolean approvalMark;

}
