package com.itdom.cloud.sentinel.reponse;

import java.io.Serializable;
import java.util.Map;

public class Result implements Serializable {
    private Integer status;

    private String characterEncoding;
    private Map<String,String> header;
    private String contentType;


}
