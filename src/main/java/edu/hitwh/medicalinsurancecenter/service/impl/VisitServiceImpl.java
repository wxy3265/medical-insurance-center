package edu.hitwh.medicalinsurancecenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hitwh.medicalinsurancecenter.dto.VisitDto;
import edu.hitwh.medicalinsurancecenter.mapper.VisitMapper;
import edu.hitwh.medicalinsurancecenter.pojo.Prescription;
import edu.hitwh.medicalinsurancecenter.pojo.Visit;
import edu.hitwh.medicalinsurancecenter.service.PrescriptionService;
import edu.hitwh.medicalinsurancecenter.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitServiceImpl extends ServiceImpl<VisitMapper, Visit> implements VisitService {

    @Autowired
    PrescriptionService prescriptionService;

    @Override
    public void saveWithPrescriptions(VisitDto visitDto) {
        this.save(visitDto);
        Long visitId = visitDto.getId();
        List<Prescription> prescriptions = visitDto.getPrescriptions();
        prescriptions = prescriptions.stream()
                .peek((item) -> item.setVisitId(visitId)).collect(Collectors.toList());
        prescriptionService.saveBatch(prescriptions);
    }

}
