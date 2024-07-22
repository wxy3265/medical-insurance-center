package edu.hitwh.medicalinsurancecenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {
    private Long id;
    private String medicId;
    private String name;
    private short expType;
    private short expLevel;
    private String measurement;
    private int maxPrice;
    private boolean approvalMark;
    private short hosLevel;
    private String size;
    private String tradeName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean valid;
    private boolean specialMark;
}
