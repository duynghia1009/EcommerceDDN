package com.DDN.login.dto.filter;

import java.io.Serializable;

public class FilterAttributesWithTotalItemsDTO implements Serializable {
    Integer id;
    String value;
    Long totalItems;

    public FilterAttributesWithTotalItemsDTO(Integer id, String value, Long totalItems) {
        this.id = id;
        this.value = value;
        this.totalItems = totalItems;
    }

    public FilterAttributesWithTotalItemsDTO() {}

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

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }
}
