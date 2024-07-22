package edu.hitwh.medicalinsurancecenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hitwh.medicalinsurancecenter.mapper.ServiceFacilitiesMapper;
import edu.hitwh.medicalinsurancecenter.pojo.ServiceFacilities;
import edu.hitwh.medicalinsurancecenter.service.ServiceFacilitiesService;
import org.springframework.stereotype.Service;

@Service
public class ServiceFacilitiesServiceImpl extends ServiceImpl<ServiceFacilitiesMapper, ServiceFacilities>
        implements ServiceFacilitiesService {
}
