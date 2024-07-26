package edu.hitwh.medicalinsurancecenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {

    private Long id;
    private short category;
    private Long itemId;
    private String itemName;
    private int price;
    private int quantity;
    private int amount;
    private Long visitId;
    private String hospitalNumber;

}
