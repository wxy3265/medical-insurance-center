package edu.hitwh.medicalinsurancecenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hitwh.medicalinsurancecenter.mapper.VisitMapper;
import edu.hitwh.medicalinsurancecenter.pojo.Visit;
import edu.hitwh.medicalinsurancecenter.service.VisitService;
import org.springframework.stereotype.Service;

@Service
public class VisitServiceImpl extends ServiceImpl<VisitMapper, Visit> implements VisitService {
}
