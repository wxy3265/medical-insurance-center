package edu.hitwh.medicalinsurancecenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("edu.hitwh.medicalinsurancecenter.mapper")
public class MedicalInsuranceCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalInsuranceCenterApplication.class, args);
	}

}
