package edu.hitwh.medicalinsurancecenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Visit {

    private Long id;
    private Long personId;
    private String hospitalNumber;
    private String institutionId;
    private short medicalCategory;
    private LocalDate admissionDate;
    private LocalDate dischargeDate;
    private String diseaseCode;
    private short hospitalLevel;
    private String admissionCode;
    private String diagnosisName;
    private String dischargeReason;
    private String settlementFlag;

}
