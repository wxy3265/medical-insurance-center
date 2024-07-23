package edu.hitwh.medicalinsurancecenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hitwh.medicalinsurancecenter.mapper.SpecialApprovalMapper;
import edu.hitwh.medicalinsurancecenter.pojo.SpecialApproval;
import edu.hitwh.medicalinsurancecenter.service.SpecialApprovalService;
import org.springframework.stereotype.Service;

@Service
public class SpecialApprovalServiceImpl extends ServiceImpl<SpecialApprovalMapper, SpecialApproval>
        implements SpecialApprovalService {
}
