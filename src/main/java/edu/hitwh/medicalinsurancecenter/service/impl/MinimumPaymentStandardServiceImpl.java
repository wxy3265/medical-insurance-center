package edu.hitwh.medicalinsurancecenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hitwh.medicalinsurancecenter.mapper.MinimumPaymentStandardMapper;
import edu.hitwh.medicalinsurancecenter.pojo.MinimumPaymentStandard;
import edu.hitwh.medicalinsurancecenter.service.MinimumPaymentStandardService;
import org.springframework.stereotype.Service;

@Service
public class MinimumPaymentStandardServiceImpl extends ServiceImpl<MinimumPaymentStandardMapper,
        MinimumPaymentStandard> implements MinimumPaymentStandardService {
}
