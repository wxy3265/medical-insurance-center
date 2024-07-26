package edu.hitwh.medicalinsurancecenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.hitwh.medicalinsurancecenter.common.PageBean;
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

    /**
     * 根据ID/姓名查询人员
     * @param peopleId 人员ID
     * @param peopleName 人员姓名
     * @return 所有ID（严格相同）、姓名（模糊搜索）符合条件的人员信息（表格）
     */
    @GetMapping("/query")
    public R query(Long peopleId, String peopleName) {

        LambdaQueryWrapper<People> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(peopleId != null, People::getPeopleId, peopleId)
                    .like(peopleName != null, People::getName, peopleName);

        return R.success(peopleService.list(queryWrapper));
    }

    /**
     * 人员信息分页查询
     * @param page 当前页数
     * @param pageSize 每页条数
     * @param name 查询条件-姓名
     * @return 查询结果 (PageBean)
     */
    @GetMapping("/page")
    public R page(Integer page, Integer pageSize, String name) {

        LambdaQueryWrapper<People> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!name.isEmpty(), People::getName, name);

        Page<People> pageInfo = new Page<>(page, pageSize);
        Page<People> resultPage = peopleService.page(pageInfo, queryWrapper);
        PageBean pageBean = new PageBean(resultPage.getTotal(), resultPage.getRecords().toArray());

        return R.success(pageBean);
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
