package edu.hitwh.medicalinsurancecenter.controller;

import edu.hitwh.medicalinsurancecenter.common.R;
import edu.hitwh.medicalinsurancecenter.pojo.Institution;
import edu.hitwh.medicalinsurancecenter.service.InstitutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/institution")
public class InstitutionController {

    @Autowired
    InstitutionService institutionService;

    /**
     * 获取全部定点医疗机构信息
     * @return 全部定点医疗机构信息
     */
    @GetMapping
    public R list() {
        return R.success(institutionService.list());
    }

    /**
     * 添加定点医疗机构信息
     * @param institution 要添加的定点医疗机构
     * @return 成功信息
     */
    @PostMapping
    public R add(@RequestBody Institution institution) {
        institutionService.save(institution);
        return R.success();
    }

    /**
     * 修改定点医疗机构信息
     * @param institution 修改后的定点医疗机构信息
     * @return 成功信息
     */
    @PutMapping
    public R update(@RequestBody Institution institution) {
        institutionService.updateById(institution);
        return R.success();
    }

    /**
     * 批量删除定点医疗机构信息
     * @param ids 删除的定点医疗机构id
     * @return 成功信息
     */
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable List<Long> ids) {
        institutionService.removeBatchByIds(ids);
        return R.success();
    }

}
