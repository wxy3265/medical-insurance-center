package edu.hitwh.medicalinsurancecenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CappingLine {

    private Long id;
    private short medicalPersonalCategory;
    private int cappingLineFee;

}
