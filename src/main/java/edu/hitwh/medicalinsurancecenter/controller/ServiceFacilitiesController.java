package edu.hitwh.medicalinsurancecenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.hitwh.medicalinsurancecenter.common.PageBean;
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
     * 服务设施分页查询
     * @param page 当前页数
     * @param pageSize 每页条数
     * @param name 查询条件-名称
     * @return 查询结果(PageBean)
     */
    @GetMapping("/page")
    public R page(Integer page, Integer pageSize, String name) {

        LambdaQueryWrapper<ServiceFacilities> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!name.isEmpty(), ServiceFacilities::getName, name);

        Page<ServiceFacilities> pageInfo = new Page<>(page, pageSize);
        Page<ServiceFacilities> pageRes = serviceFacilitiesService.page(pageInfo, queryWrapper);
        PageBean pageBean = new PageBean(pageRes.getTotal(), pageRes.getRecords().toArray());

        return R.success(pageBean);
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

    /**
     * 根据名称和编号查询服务设施
     * @param name 服务设施名称
     * @param serId 服务设施编号
     * @return 查询结果（List）
     */
    @GetMapping("/query")
    public R query(String name, String serId) {
        LambdaQueryWrapper<ServiceFacilities> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, ServiceFacilities::getName, name)
                .like(serId != null, ServiceFacilities::getSerId, serId);
        return R.success(serviceFacilitiesService.list(queryWrapper));
    }

}
