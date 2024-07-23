package edu.hitwh.medicalinsurancecenter.controller;

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
