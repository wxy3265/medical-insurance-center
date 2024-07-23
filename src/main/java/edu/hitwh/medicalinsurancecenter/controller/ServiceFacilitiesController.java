package edu.hitwh.medicalinsurancecenter.controller;

import edu.hitwh.medicalinsurancecenter.common.R;
import edu.hitwh.medicalinsurancecenter.pojo.ServiceFacilities;
import edu.hitwh.medicalinsurancecenter.service.ServiceFacilitiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/service")
public class ServiceFacilitiesController {

    @Autowired
    ServiceFacilitiesService serviceFacilitiesService;

    /**
     * 获取全部服务设施项目
     * @return 全部服务设施项目
     */
    @GetMapping
    public R list() {
        return R.success(serviceFacilitiesService.list());
    }

    /**
     * 新增服务设施项目数据
     * @param serviceFacilities 新增的服务设施项目信息
     * @return 成功信息
     */
    @PostMapping
    public R add(@RequestBody ServiceFacilities serviceFacilities) {
        serviceFacilitiesService.save(serviceFacilities);
        return R.success();
    }

    /**
     * 更新服务设施项目信息
     * @param serviceFacilities 修改后的服务设施项目
     * @return 成功信息
     */
    @PutMapping
    public R update(@RequestBody ServiceFacilities serviceFacilities) {
        serviceFacilitiesService.updateById(serviceFacilities);
        return R.success();
    }

    /**
     * 批量删除服务设施项目
     * @param ids 需要删除的服务设施项目id
     * @return 成功信息
     */
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable List<Long> ids) {
        serviceFacilitiesService.removeBatchByIds(ids);
        return R.success();
    }

}
