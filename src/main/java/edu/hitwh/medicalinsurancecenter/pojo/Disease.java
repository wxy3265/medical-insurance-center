package edu.hitwh.medicalinsurancecenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disease {

    private Long id;
    private String disId;
    private String name;
    private short type;
    private boolean reimbursementStandards;
    private String note;

}
