package edu.hitwh.medicalinsurancecenter.controller;

import edu.hitwh.medicalinsurancecenter.common.R;
import edu.hitwh.medicalinsurancecenter.pojo.Application;
import edu.hitwh.medicalinsurancecenter.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    /**
     * 查询所有定点机构审批信息
     * @return 所有定点机构审批信息
     */
    @GetMapping
    public R list() {
        return R.success(applicationService.list());
    }

    /**
     * 新增定点机构审批信息
     * @param application 要新增的定点机构审批信息
     * @return 成功信息
     */
    @PostMapping
    public R add(@RequestBody Application application) {
        applicationService.save(application);
        return R.success();
    }

    /**
     * 修改定点机构审批信息
     * @param application 修改后的定点机构审批信息
     * @return 成功信息
     */
    @PutMapping
    public R update(@RequestBody Application application) {
        applicationService.updateById(application);
        return R.success();
    }

    /**
     * 批量删除定点机构审批信息
     * @param ids 要删除的定点机构审批信息id
     * @return 成功信息
     */
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable List<Long> ids) {
        applicationService.removeBatchByIds(ids);
        return R.success();
    }

}
