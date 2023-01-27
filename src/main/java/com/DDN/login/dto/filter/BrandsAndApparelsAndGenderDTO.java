package com.DDN.login.dto.filter;

import com.DDN.login.model.categories.ApparelCategory;
import com.DDN.login.model.categories.GenderCategory;
import com.DDN.login.model.categories.ProductBrandCategory;

import java.util.List;

public class BrandsAndApparelsAndGenderDTO {

    List<ProductBrandCategory> brands;
    List<ApparelCategory> apparels;
    List<GenderCategory> genders;

    public BrandsAndApparelsAndGenderDTO() {}

    public BrandsAndApparelsAndGenderDTO(List<ProductBrandCategory> brands, List<ApparelCategory> apparels,
                                        List<GenderCategory> genders) {
        this.brands = brands;
        this.apparels = apparels;
        this.genders = genders;
    }

    public List<ProductBrandCategory> getBrands() {
        return brands;
    }

    public void setBrands(List<ProductBrandCategory> brands) {
        this.brands = brands;
    }

    public List<ApparelCategory> getApparels() {
        return apparels;
    }

    public void setApparels(List<ApparelCategory> apparels) {
        this.apparels = apparels;
    }

    public List<GenderCategory> getGenders() {
        return genders;
    }

    public void setGenders(List<GenderCategory> genders) {
        this.genders = genders;
    }
}
