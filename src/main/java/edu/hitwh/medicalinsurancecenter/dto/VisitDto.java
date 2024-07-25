package edu.hitwh.medicalinsurancecenter.dto;

import edu.hitwh.medicalinsurancecenter.pojo.Prescription;
import edu.hitwh.medicalinsurancecenter.pojo.Visit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class VisitDto extends Visit {

    private List<Prescription> prescriptions;

}
