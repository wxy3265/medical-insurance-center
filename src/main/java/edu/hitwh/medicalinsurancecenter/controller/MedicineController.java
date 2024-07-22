package edu.hitwh.medicalinsurancecenter.controller;

import edu.hitwh.medicalinsurancecenter.common.R;
import edu.hitwh.medicalinsurancecenter.pojo.Medicine;
import edu.hitwh.medicalinsurancecenter.service.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/medicine")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    /**
     * 获取全部药品数据
     * @return 成功信息和全部药品数据
     */
    @GetMapping
    public R list() {
        return R.success(medicineService.list());
    }

    /**
     * 新建药品数据
     * @param medicine 新建药品信息
     * @return 成功信息
     */
    @PostMapping
    public R add(@RequestBody Medicine medicine) {
        medicineService.save(medicine);
        return R.success();
    }

    /**
     * 修改药品信息
     * @param medicine 修改后的药品信息
     * @return 成功信息
     */
    @PutMapping
    public R update(@RequestBody Medicine medicine) {
        medicineService.updateById(medicine);
        return R.success();
    }

    /**
     * 批量删除药品数据
     * @param ids 要删除的药品数据id列表
     * @return 成功信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable List<Long> ids) {
        medicineService.removeBatchByIds(ids);
        return R.success();
    }

}
