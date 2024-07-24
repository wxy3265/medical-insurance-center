package edu.hitwh.medicalinsurancecenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelfFunded {

    private Long id;
    private short type;
    private short personType;
    private short level;
    private int maxAmount;
    private int minAmount;
    private float reimbursement;

}
