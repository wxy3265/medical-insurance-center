package edu.hitwh.medicalinsurancecenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hitwh.medicalinsurancecenter.mapper.SelfFundedMapper;
import edu.hitwh.medicalinsurancecenter.pojo.SelfFunded;
import edu.hitwh.medicalinsurancecenter.service.SelfFundedService;
import org.springframework.stereotype.Service;

@Service
public class SelfFundedServiceImpl extends ServiceImpl<SelfFundedMapper, SelfFunded>
        implements SelfFundedService {
}
