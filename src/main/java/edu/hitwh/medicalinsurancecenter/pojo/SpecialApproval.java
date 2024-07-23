package edu.hitwh.medicalinsurancecenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialApproval {

    private Long id;
    private Long personId;
    private short approvalCategory;
    private LocalDate startDate;
    private LocalDate terminationDate;
    private String medicineCode;
    private String approvalOpinions;
    private String approver;
    private LocalDate approvalDate;
    private String approvalFlag;

}
