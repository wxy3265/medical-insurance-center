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
    private double total;
    private double insurance;
    private double selfFund;
    private double annual;
    private double standard;
    private double proportionSelfFund;
    private double topLine;
    private double secondSelfFund;
    private double specialSelfFund;

}
