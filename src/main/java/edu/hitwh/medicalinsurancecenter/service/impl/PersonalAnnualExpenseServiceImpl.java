package edu.hitwh.medicalinsurancecenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hitwh.medicalinsurancecenter.mapper.PersonalAnnualExpenseMapper;
import edu.hitwh.medicalinsurancecenter.pojo.PersonalAnnualExpense;
import edu.hitwh.medicalinsurancecenter.service.PersonalAnnualExpenseService;
import org.springframework.stereotype.Service;

@Service
public class PersonalAnnualExpenseServiceImpl extends ServiceImpl<PersonalAnnualExpenseMapper,
        PersonalAnnualExpense> implements PersonalAnnualExpenseService {
}
