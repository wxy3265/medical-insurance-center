package edu.hitwh.medicalinsurancecenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hitwh.medicalinsurancecenter.mapper.DiseaseMapper;
import edu.hitwh.medicalinsurancecenter.pojo.Disease;
import edu.hitwh.medicalinsurancecenter.service.DiseaseService;
import org.springframework.stereotype.Service;

@Service
public class DiseaseServiceImpl extends ServiceImpl<DiseaseMapper, Disease> implements DiseaseService {
}
