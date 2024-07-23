package edu.hitwh.medicalinsurancecenter.controller;

import edu.hitwh.medicalinsurancecenter.common.R;
import edu.hitwh.medicalinsurancecenter.pojo.CappingLine;
import edu.hitwh.medicalinsurancecenter.service.CappingLineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/topLine")
public class CappingLineController {

    @Autowired
    CappingLineService cappingLineService;

    /**
     * 查询所有封顶线数据
     * @return 所有封顶线数据
     */
    @GetMapping
    public R list() {
        return R.success(cappingLineService.list());
    }

    /**
     * 修改封顶线数据
     * @param cappingLine 修改后的封顶线数据
     * @return 成功信息
     */
    @PutMapping
    public R update(@RequestBody CappingLine cappingLine) {
        cappingLineService.updateById(cappingLine);
        return R.success();
    }

}
