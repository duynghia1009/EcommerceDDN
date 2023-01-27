package com.DDN.login.dto.filter;

import java.io.Serializable;

public class FilterAttributesDTO implements Serializable {
    Integer id;
    String value;

    public FilterAttributesDTO(Integer id, String value) {
        this.id =id;
        this.value = value;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
