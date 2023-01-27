package com.DDN.login.dto.filter;

import com.DDN.login.model.categories.ApparelCategory;

public class ApparelCategoryDTO {
    private Integer id;
    private String type;

    public ApparelCategoryDTO(ApparelCategory apparelCategory) {
        this.id = apparelCategory.getId();
        this.type = apparelCategory.getType();
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
