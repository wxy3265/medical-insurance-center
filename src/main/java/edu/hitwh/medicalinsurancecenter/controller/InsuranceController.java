package edu.hitwh.medicalinsurancecenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.hitwh.medicalinsurancecenter.common.R;
import edu.hitwh.medicalinsurancecenter.dto.VisitDto;
import edu.hitwh.medicalinsurancecenter.pojo.*;
import edu.hitwh.medicalinsurancecenter.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    VisitService visitService;
    @Autowired
    PeopleService peopleService;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    DiseaseService diseaseService;
    @Autowired
    MedicineService medicineService;
    @Autowired
    SpecialApprovalService specialApprovalService;
    @Autowired
    InstitutionService institutionService;
    @Autowired
    DiagnosisService diagnosisService;
    @Autowired
    CappingLineService cappingLineService;
    @Autowired
    SelfFundedService selfFundedService;
    @Autowired
    MinimumPaymentStandardService minimumPaymentStandardService;
    @Autowired
    PersonalAnnualExpenseService personalAnnualExpenseService;

    /**
     * 返回所有报销信息
     * @return 所有报销信息
     */
    @GetMapping
    public R list() {
        return R.success(visitService.list());
    }

    /**
     * 医保审批
     * @param visitDto 医保数据传输的Dto
     * @return 若审批通过，返回成功信息与费用信息，若审批不通过，返回错误信息与审批不通过的原因
     */
    @PostMapping
    public R application(@RequestBody VisitDto visitDto) {

        // 疾病可报销检查
        LambdaQueryWrapper<Disease> diseaseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        diseaseLambdaQueryWrapper.eq(Disease::getDisId, visitDto.getDiseaseCode());
        Disease disease = diseaseService.getOne(diseaseLambdaQueryWrapper);
        if (disease == null) {
            return R.error("该病种不存在");
        }
        if (!disease.isReimbursementStandards()) {
            return R.error("该病种不可报销");
        }

        // 报销人员就诊定点机构审批
        People people = peopleService.getById(visitDto.getPersonId());
        String designateNumber = people.getMedinsId();
        String institutionCode = visitDto.getInstitutionNumber();
        // 不在定点机构
        if (!institutionCode.equals(designateNumber)) {
            // 查询审批信息
            LambdaQueryWrapper<Application> applicationLambdaQueryWrapper = new LambdaQueryWrapper<>();
            applicationLambdaQueryWrapper.eq(Application::getPersonId, people.getId())
                        .eq(Application::getMedicalInstitutionCode, institutionCode)
                        .eq(Application::getApprovalFlag, true)
                        .eq(Application::getApprovalOptions, true);
            if (!applicationService.exists(applicationLambdaQueryWrapper)) {
                return R.error("未获得跨医疗定点机构就诊审批");
            }
        }

        double total = 0, insurance = 0, secondSelfFund = 0, specialSelfFund = 0;
        // 金额计算
        List<Prescription> prescriptions = visitDto.getPrescriptions();
        // 计算可报销金额
        for (Prescription prescription : prescriptions) {
            // 累加金额
            total += prescription.getAmount();
            // 药品
            if (prescription.getChargeableItemsCategory() == 0) {
                // 查询处方药品
                LambdaQueryWrapper<Medicine> medicineLambdaQueryWrapper = new LambdaQueryWrapper<>();
                medicineLambdaQueryWrapper.eq(Medicine::getMedicId, prescription.getProjectCoding());
                Medicine medicine = medicineService.getOne(medicineLambdaQueryWrapper);
                // 特检审批判断
                if (medicine.isSpecialMark()) {
                    // 查询特检审批信息
                    LambdaQueryWrapper<SpecialApproval> specialLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    specialLambdaQueryWrapper.eq(SpecialApproval::getMedicineCode, medicine.getMedicId())
                            .eq(SpecialApproval::getPersonId, people.getPeopleId());
                    SpecialApproval specialApproval = specialApprovalService.getOne(specialLambdaQueryWrapper);
                    // 审批未通过
                    if (!specialApproval.isApprovalFlag() || !specialApproval.isApprovalOpinions()) {
                        specialSelfFund += prescription.getAmount();
                        continue;
                    }
                }
                // 医院等级判断
                if (medicine.getHosLevel() < visitDto.getHospitalGrade()) continue;
                // 最高限价判断
                if (prescription.getAmount() > medicine.getMaxPrice()) {
                    prescription.setAmount(medicine.getMaxPrice());
                }
                // 根据药品类别计算报销金额
                if (medicine.getExpType() == 0) insurance += prescription.getAmount();
                else if (medicine.getExpType() == 1) {
                    insurance += prescription.getAmount() * 0.5;
                    secondSelfFund += prescription.getAmount() * 0.5;
                }
            } else if (prescription.getChargeableItemsCategory() == 1) {
                // 查询诊疗项目
                LambdaQueryWrapper<Diagnosis> diagnosisLambdaQueryWrapper = new LambdaQueryWrapper<>();
                diagnosisLambdaQueryWrapper.eq(Diagnosis::getTreId, prescription.getProjectCoding());
                Diagnosis diagnosis = diagnosisService.getOne(diagnosisLambdaQueryWrapper);
                // 医院等级判断
                if (diagnosis.getHosLevel() < visitDto.getHospitalGrade()) continue;
                // 最高限价判断
                if (prescription.getAmount() > diagnosis.getMaxPrice()) {
                    prescription.setAmount(diagnosis.getMaxPrice());
                }
                // 根据项目类别计算报销金额
                if (diagnosis.getExpType() == 0) insurance += prescription.getAmount();
                else if (diagnosis.getExpType() == 1) {
                    insurance += prescription.getAmount() * 0.5;
                    secondSelfFund += prescription.getAmount() * 0.5;
                }
            } else { //服务设施全额报销
                insurance += prescription.getAmount();
            }
        }

        // 获取起付标准
        LambdaQueryWrapper<MinimumPaymentStandard> minimumPaymentStandardLambdaQueryWrapper
                = new LambdaQueryWrapper<>();
        minimumPaymentStandardLambdaQueryWrapper.eq(MinimumPaymentStandard::getType, visitDto.getMedicalCategory())
                .eq(MinimumPaymentStandard::getPersonType, people.getMedicalPersonnel())
                .eq(MinimumPaymentStandard::getLevel, visitDto.getHospitalGrade());
        MinimumPaymentStandard minimumStandard =
                minimumPaymentStandardService.getOne(minimumPaymentStandardLambdaQueryWrapper);
        if (minimumStandard == null) return R.error("缺少起付标准信息");
        double standard = minimumStandard.getStandard();

        // 获取封顶线
        LambdaQueryWrapper<CappingLine> cappingLineLambdaQueryWrapper = new LambdaQueryWrapper<>();
        cappingLineLambdaQueryWrapper.eq(CappingLine::getPersonType, people.getMedicalPersonnel());
        CappingLine cappingLine = cappingLineService.getOne(cappingLineLambdaQueryWrapper);
        if (cappingLine == null) return R.error("缺少封顶线信息");

        // 计算最终报销金额
        // 获取个人分段自费比例
        LambdaQueryWrapper<SelfFunded> selfFundedLambdaQueryWrapper = new LambdaQueryWrapper<>();
        selfFundedLambdaQueryWrapper.eq(SelfFunded::getType, visitDto.getMedicalCategory())
                .eq(SelfFunded::getLevel, visitDto.getHospitalGrade())
                .eq(SelfFunded::getPersonType, people.getMedicalPersonnel());
        List<SelfFunded> selfFundedList = selfFundedService.list(selfFundedLambdaQueryWrapper);
        if (selfFundedList.isEmpty()) return R.error("缺少个人分段自费比例信息");
        Collections.sort(selfFundedList);
        // 检查分段完整性
        if (selfFundedList.getFirst().getMinAmount() != standard) return R.error("缺少个人分段自费比例信息");
        for (int i = 1; i < selfFundedList.size(); i++) {
            if (selfFundedList.get(i).getMinAmount() != selfFundedList.get(i - 1).getMaxAmount()) {
                return R.error("缺少个人分段自费比例信息");
            }
        }
        // 根据分段计算金额
        insurance = 0;
        double tmpAmount = insurance, proportionSelf = 0;
        tmpAmount -= standard;
        if (tmpAmount < 0) tmpAmount = 0;
        for (SelfFunded fund : selfFundedList) {
            double left = fund.getMinAmount(), right = fund.getMaxAmount(), proportion = fund.getReimbursement();
            if (tmpAmount > right - left) {
                insurance += (right - left) * (1 - proportion);
                proportionSelf += (right - left) * proportion;
                tmpAmount -= right - left;
            } else {
                insurance += tmpAmount * (1 - proportion);
                proportionSelf += tmpAmount * proportion;
                break;
            }
        }

        // 检查封顶线
        // 获取当前年份
        Calendar calendar = Calendar.getInstance();
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        // 查询当前年度累计花费
        LambdaQueryWrapper<PersonalAnnualExpense> annualQueryWrapper = new LambdaQueryWrapper<>();
        annualQueryWrapper.eq(PersonalAnnualExpense::getPeopleId, people.getPeopleId())
                .eq(PersonalAnnualExpense::getYear, year);
        PersonalAnnualExpense annualExpense = personalAnnualExpenseService.getOne(annualQueryWrapper);
        // 封顶线检查
        if (insurance > cappingLine.getTopLine() - annualExpense.getMedicareExpenses()) {
            insurance = cappingLine.getTopLine() - annualExpense.getMedicareExpenses();
        }

        // 将结果封装成DTO
        visitDto.setTotal(total);
        visitDto.setInsurance(insurance);
        visitDto.setSelfFund(total - insurance);
        visitDto.setAnnual(annualExpense.getMedicareExpenses());
        visitDto.setStandard(standard);
        visitDto.setProportionSelfFund(proportionSelf);
        visitDto.setTopLine(cappingLine.getTopLine());
        visitDto.setSecondSelfFund(secondSelfFund);
        visitDto.setSpecialSelfFund(specialSelfFund);

        return R.success(visitDto);
    }

    /**
     * 提交报销结算
     * @param visitDto 报销金额信息
     * @return 成功信息
     */
    @PostMapping("/submit")
    public R submit(@RequestBody VisitDto visitDto) {
        visitService.saveWithAmount(visitDto);
        return R.success();
    }

}
