package edu.hitwh.medicalinsurancecenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hitwh.medicalinsurancecenter.pojo.Prescription;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrescriptionMapper extends BaseMapper<Prescription> {
}
