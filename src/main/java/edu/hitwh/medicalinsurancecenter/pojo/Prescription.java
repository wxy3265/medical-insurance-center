package edu.hitwh.medicalinsurancecenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {

    private Long id;
    private short chargeableItemsCategory;
    private int projectCoding;
    private int projectName;
    private int unitPrice;
    private int quantity;
    private int amount;
    private Long visitId;

}
