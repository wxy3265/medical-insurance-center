package edu.hitwh.medicalinsurancecenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hitwh.medicalinsurancecenter.mapper.PeopleMapper;
import edu.hitwh.medicalinsurancecenter.pojo.People;
import edu.hitwh.medicalinsurancecenter.service.PeopleService;
import org.springframework.stereotype.Service;

@Service
public class PeopleServiceImpl extends ServiceImpl<PeopleMapper, People>
        implements PeopleService {
}
