package edu.hitwh.medicalinsurancecenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hitwh.medicalinsurancecenter.mapper.CappingLineMapper;
import edu.hitwh.medicalinsurancecenter.pojo.CappingLine;
import edu.hitwh.medicalinsurancecenter.service.CappingLineService;
import org.springframework.stereotype.Service;

@Service
public class CappingLineServiceImpl extends ServiceImpl<CappingLineMapper, CappingLine>
        implements CappingLineService {
}
