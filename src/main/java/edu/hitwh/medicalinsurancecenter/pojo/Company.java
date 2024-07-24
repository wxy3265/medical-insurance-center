package edu.hitwh.medicalinsurancecenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private Long id;
    private String companyId;
    private String name;
    private short type;
    private String address;
    private String postcode;
    private String phone;

}
