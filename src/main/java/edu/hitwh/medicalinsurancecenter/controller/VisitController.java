package edu.hitwh.medicalinsurancecenter.controller;

import edu.hitwh.medicalinsurancecenter.common.R;
import edu.hitwh.medicalinsurancecenter.dto.VisitDto;
import edu.hitwh.medicalinsurancecenter.service.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/insurance")
public class VisitController {

    @Autowired
    VisitService visitService;

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
     * @return 若审批通过，返回成功信息，若审批不通过，返回审批不通过的原因
     */
    @PostMapping
    public R add(@RequestBody VisitDto visitDto) {

        // TODO 报销人员就诊机构审批

        return R.success();
    }

}
