package edu.hitwh.medicalinsurancecenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hitwh.medicalinsurancecenter.mapper.InstitutionMapper;
import edu.hitwh.medicalinsurancecenter.pojo.Institution;
import edu.hitwh.medicalinsurancecenter.service.InstitutionService;
import org.springframework.stereotype.Service;

@Service
public class InstitutionServiceImpl extends ServiceImpl<InstitutionMapper, Institution>
        implements InstitutionService {
}
