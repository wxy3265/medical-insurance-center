package edu.hitwh.medicalinsurancecenter.controller;

import edu.hitwh.medicalinsurancecenter.common.R;
import edu.hitwh.medicalinsurancecenter.pojo.Diagnosis;
import edu.hitwh.medicalinsurancecenter.pojo.Medicine;
import edu.hitwh.medicalinsurancecenter.service.DiagnosisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController {

    @Autowired
    private DiagnosisService diagnosisService;

    /**
     * 获取全部诊疗项目数据
     * @return 成功信息和全部药品数据
     */
    @GetMapping
    public R list() {
        return R.success(diagnosisService.list());
    }

    /**
     * 新建诊疗项目数据
     * @param diagnosis 新建药品信息
     * @return 成功信息
     */
    @PostMapping
    public R add(@RequestBody Diagnosis diagnosis) {
        diagnosisService.save(diagnosis);
        return R.success();
    }

    /**
     * 修改诊疗项目信息
     * @param diagnosis 修改后的药品信息
     * @return 成功信息
     */
    @PutMapping
    public R update(@RequestBody Diagnosis diagnosis) {
        diagnosisService.updateById(diagnosis);
        return R.success();
    }

    /**
     * 批量删除诊疗项目数据
     * @param ids 要删除的药品数据id列表
     * @return 成功信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable List<Long> ids) {
        diagnosisService.removeBatchByIds(ids);
        return R.success();
    }

}
