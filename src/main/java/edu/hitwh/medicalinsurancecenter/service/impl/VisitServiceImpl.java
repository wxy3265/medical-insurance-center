package edu.hitwh.medicalinsurancecenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hitwh.medicalinsurancecenter.dto.VisitDto;
import edu.hitwh.medicalinsurancecenter.mapper.VisitMapper;
import edu.hitwh.medicalinsurancecenter.pojo.People;
import edu.hitwh.medicalinsurancecenter.pojo.PersonalAnnualExpense;
import edu.hitwh.medicalinsurancecenter.pojo.Visit;
import edu.hitwh.medicalinsurancecenter.service.PeopleService;
import edu.hitwh.medicalinsurancecenter.service.PersonalAnnualExpenseService;
import edu.hitwh.medicalinsurancecenter.service.PrescriptionService;
import edu.hitwh.medicalinsurancecenter.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class VisitServiceImpl extends ServiceImpl<VisitMapper, Visit> implements VisitService {

    @Autowired
    PrescriptionService prescriptionService;
    @Autowired
    PeopleService peopleService;
    @Autowired
    PersonalAnnualExpenseService personalAnnualExpenseService;

    @Override
    public void saveWithAmount(VisitDto visitDto) {

        // 保存报销信息
        this.save(visitDto);

        // 查询报销人员信息
        People people = peopleService.getById(visitDto.getPersonId());

        // 获取当前年份
        Calendar calendar = Calendar.getInstance();
        String year = Integer.toString(calendar.get(Calendar.YEAR));

        // 查询当前年度累计花费
        LambdaQueryWrapper<PersonalAnnualExpense> annualQueryWrapper = new LambdaQueryWrapper<>();
        annualQueryWrapper.eq(PersonalAnnualExpense::getPeopleId, people.getPeopleId())
                .eq(PersonalAnnualExpense::getYear, year);
        PersonalAnnualExpense annualExpense = personalAnnualExpenseService.getOne(annualQueryWrapper);

        // 更新年度报销信息
        annualExpense.setReimbursementTime(annualExpense.getReimbursementTime() + 1);
        annualExpense.setMedicalExpenses(annualExpense.getMedicalExpenses() + visitDto.getTotal());
        annualExpense.setMedicareExpenses(annualExpense.getMedicareExpenses() + visitDto.getInsurance());
        annualExpense.setPersonalExpenses(annualExpense.getPersonalExpenses() + visitDto.getSelfFund());
        personalAnnualExpenseService.updateById(annualExpense);

    }

    @Override
    public void delete(Integer id) {

        ;

    }

}
