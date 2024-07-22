package edu.hitwh.medicalinsurancecenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hitwh.medicalinsurancecenter.mapper.DiagnosisMapper;
import edu.hitwh.medicalinsurancecenter.pojo.Diagnosis;
import edu.hitwh.medicalinsurancecenter.service.DiagnosisService;
import org.springframework.stereotype.Service;

@Service
public class DiagnosisServiceImpl extends ServiceImpl<DiagnosisMapper, Diagnosis> implements DiagnosisService {
}
