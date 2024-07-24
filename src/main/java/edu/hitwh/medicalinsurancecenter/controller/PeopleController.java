package edu.hitwh.medicalinsurancecenter.controller;

import edu.hitwh.medicalinsurancecenter.common.R;
import edu.hitwh.medicalinsurancecenter.pojo.People;
import edu.hitwh.medicalinsurancecenter.service.PeopleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    PeopleService peopleService;

    /**
     * 获取全部人员信息
     * @return 全部人员信息
     */
    @GetMapping
    public R list() {
        return R.success(peopleService.list());
    }

    @GetMapping("/query")
    public R query(Long peopleId, String peopleName) {

        return R.success();
    }

    /**
     * 新增人员信息
     * @param people 要新增的人员信息
     * @return 成功信息
     */
    @PostMapping
    public R add(@RequestBody People people) {
        peopleService.save(people);
        return R.success();
    }

    /**
     * 修改人员信息
     * @param people 修改后的人员信息
     * @return 成功信息
     */
    @PutMapping
    public R update(@RequestBody People people) {
        peopleService.updateById(people);
        return R.success();
    }

    /**
     * 批量删除人员信息
     * @param ids 要删除的人员id
     * @return 成功信息
     */
    @DeleteMapping("/{ids}")
    public R delete(@PathVariable List<Long> ids) {
        peopleService.removeBatchByIds(ids);
        return R.success();
    }

}
