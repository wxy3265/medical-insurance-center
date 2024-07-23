package edu.hitwh.medicalinsurancecenter.controller;

import edu.hitwh.medicalinsurancecenter.common.R;
import edu.hitwh.medicalinsurancecenter.pojo.SpecialApproval;
import edu.hitwh.medicalinsurancecenter.service.SpecialApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpecialApprovalController {

    @Autowired
    SpecialApprovalService specialApprovalService;

    /**
     * 获取全部特检特治信息
     * @return 全部特检特治信息
     */
    @GetMapping
    public R list() {
        return R.success();
    }

    /**
     * 新增特检特治信息
     * @param specialApproval 要新增的特检特治信息
     * @return 成功信息
     */
    @PostMapping
    public R add(@RequestBody SpecialApproval specialApproval) {
        specialApprovalService.save(specialApproval);
        return R.success();
    }

    /**
     * 修改特检特治信息
     * @param specialApproval 修改后的特检特治信息
     * @return 成功信息
     */
    @PutMapping
    public R update(@RequestBody SpecialApproval specialApproval) {
        specialApprovalService.updateById(specialApproval);
        return R.success();
    }

}
