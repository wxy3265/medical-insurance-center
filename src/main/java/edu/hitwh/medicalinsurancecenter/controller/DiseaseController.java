package edu.hitwh.medicalinsurancecenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.hitwh.medicalinsurancecenter.common.PageBean;
import edu.hitwh.medicalinsurancecenter.common.R;
import edu.hitwh.medicalinsurancecenter.pojo.Disease;
import edu.hitwh.medicalinsurancecenter.service.DiseaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/disease")
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    /**
     * 查询所有病种信息
     * @return 所有病种信息
     */
    @GetMapping
    public R list() {
        return R.success(diseaseService.list());
    }

    /**
     * 病种信息分页查询
     * @param page 当前页数
     * @param pageSize 每页条数
     * @param name 查询条件-姓名
     * @return 查询结果(PageBean)
     */
    @GetMapping("/page")
    public R page(Integer page, Integer pageSize, String name) {

        LambdaQueryWrapper<Disease> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!name.isEmpty(), Disease::getName, name);

        Page<Disease> pageInfo = new Page<>(page, pageSize);
        Page<Disease> pageRes = diseaseService.page(pageInfo, queryWrapper);
        PageBean pageBean = new PageBean(pageRes.getTotal(), pageRes.getRecords().toArray());

        return R.success(pageBean);
    }

    /**
     * 新建病种信息
     * @param disease 需要添加的病种信息
     * @return 成功信息
     */
    @PostMapping
    public R add(@RequestBody Disease disease) {
        diseaseService.save(disease);
        return R.success();
    }

    /**
     * 修改病种信息
     * @param disease 需要修改的病种信息
     * @return 成功信息
     */
    @PutMapping
    public R update(@RequestBody Disease disease) {
        diseaseService.updateById(disease);
        return R.success();
    }

    /**
     * 删除病种信息
     * @param ids 需要删除的病种id
     * @return 成功信息
     */
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable List<Long> ids) {
        diseaseService.removeBatchByIds(ids);
        return R.success();
    }

}
