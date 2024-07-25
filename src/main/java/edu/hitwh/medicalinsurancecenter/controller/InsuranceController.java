package edu.hitwh.medicalinsurancecenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.hitwh.medicalinsurancecenter.common.R;
import edu.hitwh.medicalinsurancecenter.dto.VisitDto;
import edu.hitwh.medicalinsurancecenter.pojo.*;
import edu.hitwh.medicalinsurancecenter.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 返回所有报销信息
     * @return 所有报销信息
     */
    @GetMapping
    public R list() {
        return R.success(visitService.list());
    }

    /**
     * 添加医保数据
     * @param visitDto 医保数据传输的Dto
     * @return 若审批通过，返回成功信息，若审批不通过，返回错误信息与审批不通过的原因
     */
    @PostMapping
    public R add(@RequestBody VisitDto visitDto) {

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

        // 特检特治审批
        List<Prescription> prescriptions = visitDto.getPrescriptions();
        for (Prescription prescription : prescriptions) {
            // 如果处方项目是药品
            if (prescription.getChargeableItemsCategory() == 0) {
                // 查询处方药品
                LambdaQueryWrapper<Medicine> medicineLambdaQueryWrapper = new LambdaQueryWrapper<>();
                medicineLambdaQueryWrapper.eq(Medicine::getMedicId, prescription.getProjectCoding());
                Medicine medicine = medicineService.getOne(medicineLambdaQueryWrapper);
                // 如果需要特检
                if (medicine.isSpecialMark()) {
                    // 查询特检审批信息
                    LambdaQueryWrapper<SpecialApproval> specialLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    specialLambdaQueryWrapper.eq(SpecialApproval::getMedicineCode, medicine.getMedicId())
                            .eq(SpecialApproval::getPersonId, people.getPeopleId());
                    SpecialApproval specialApproval = specialApprovalService.getOne(specialLambdaQueryWrapper);
                    if (!specialApproval.isApprovalFlag() || !specialApproval.isApprovalOpinions()) {
                        return R.error("特检特治审批未通过");
                    }
                }
            }
        }

        return R.success();
    }

}
