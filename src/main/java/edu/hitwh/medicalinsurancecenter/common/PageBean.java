package edu.hitwh.medicalinsurancecenter.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean {

    private Long total;
    private Object[] rows;

}
