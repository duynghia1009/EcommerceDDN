package com.DDN.login.dto.filter;

import java.io.Serializable;
import java.util.List;

public class BrandsAndApparelsDTO implements Serializable {
    List<FilterAttributesDTO> brands;
    List<FilterAttributesDTO> apparels;
    public BrandsAndApparelsDTO() {}
    public BrandsAndApparelsDTO(List<FilterAttributesDTO> brands, List<FilterAttributesDTO> apparels ) {
        this.brands = brands;
        this.apparels = apparels;
    }
    public List<FilterAttributesDTO> getBrands() {
        return brands;
    }

    public void setBrands(List<FilterAttributesDTO> brands) {
        this.brands = brands;
    }

    public List<FilterAttributesDTO> getApparels() {
        return apparels;
    }

    public void setApparels(List<FilterAttributesDTO> apparels) {
        this.apparels = apparels;
    }
}
