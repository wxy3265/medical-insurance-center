package edu.hitwh.medicalinsurancecenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hitwh.medicalinsurancecenter.pojo.Disease;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DiseaseMapper extends BaseMapper<Disease> {
}
