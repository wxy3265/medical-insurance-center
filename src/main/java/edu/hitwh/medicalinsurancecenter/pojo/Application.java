package edu.hitwh.medicalinsurancecenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {

    private long id;
    private long personId;
    private LocalDate startDate;
    private LocalDate terminalDate;
    private String medicalInstitutionCode;
    private String approvalOptions;
    private String approver;
    private LocalDate approvalDate;
    private String approvalFlag;

}
