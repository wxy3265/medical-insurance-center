package edu.hitwh.medicalinsurancecenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.hitwh.medicalinsurancecenter.dto.VisitDto;
import edu.hitwh.medicalinsurancecenter.pojo.Visit;

public interface VisitService extends IService<Visit> {

    void saveWithAmount(VisitDto visitDto);

}
