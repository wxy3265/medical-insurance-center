package edu.hitwh.medicalinsurancecenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MinimumPaymentStandard {

    private Long id;
    private short medicalCategory;
    private short medicalPersonnelCategory;
    private short hospitalLevel;
    private int minimumPaymentStandard;

}
