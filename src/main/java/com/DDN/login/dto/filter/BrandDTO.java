package com.DDN.login.dto.filter;

import com.DDN.login.model.categories.ProductBrandCategory;

public class BrandDTO {
    private Integer id;
    private String type;

    public BrandDTO(ProductBrandCategory brandCategory) {
        this.id = brandCategory.getId();
        this.type = brandCategory.getType();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
