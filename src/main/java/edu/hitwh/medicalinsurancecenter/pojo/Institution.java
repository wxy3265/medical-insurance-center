package edu.hitwh.medicalinsurancecenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Institution {

    private Long id;
    private String institutionId;
    private String name;
    private boolean hosLevel;
    private String postcode;
    private String repName;
    private String repPhone;
    private String cntName;
    private String cntPhone;
    private String address;
    private String note;

}
