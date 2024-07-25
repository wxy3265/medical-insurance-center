package edu.hitwh.medicalinsurancecenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.hitwh.medicalinsurancecenter.common.PageBean;
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
     * 定点机构信息分页查询
     * @param page 当前页数
     * @param pageSize 每页条数
     * @param name 查询条件-名称
     * @return 查询结果(PageBean)
     */
    @GetMapping("/page")
    public R page(Integer page, Integer pageSize, String name) {

        LambdaQueryWrapper<Institution> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!name.isEmpty(), Institution::getName, name);

        Page<Institution> pageInfo = new Page<>(page, pageSize);
        Page<Institution> pageRes = institutionService.page(pageInfo, queryWrapper);
        PageBean pageBean = new PageBean(pageRes.getTotal(), pageRes.getRecords().toArray());

        return R.success(pageBean);
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
