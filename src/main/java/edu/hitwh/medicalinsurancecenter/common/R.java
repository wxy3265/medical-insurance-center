package edu.hitwh.medicalinsurancecenter.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class R {
    private Integer code;
    private String msg;
    private Object data;

    public static R success() {
        return new R(1, "success", null);
    }
    public static R success(Object data) {
        return new R(1, "success", data);
    }
    public static R error(String msg) {
        return new R(0, msg, null);
    }

}
