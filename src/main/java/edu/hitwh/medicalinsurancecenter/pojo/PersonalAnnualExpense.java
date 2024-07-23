package edu.hitwh.medicalinsurancecenter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalAnnualExpense {

    private Long id;
    private Long peopleId;
    private String year;
    private int reimbursementTime;
    private int medicalExpenses;
    private int medicareExpenses;
    private int personalExpenses;

}
