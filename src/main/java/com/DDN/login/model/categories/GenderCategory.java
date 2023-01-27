package com.DDN.login.model.categories;

import com.DDN.login.model.images.ApparelImages;
import com.DDN.login.model.info.ProductInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class GenderCategory {
    @Id
    private int id;

    private String type;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "genderCategory")
    @JsonIgnore
    private List<ProductInfo> productInfos;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "genderCategory")
    @JsonIgnore
    private List<ApparelImages> apparelImages;

    public GenderCategory() {}

    public GenderCategory(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ProductInfo> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(List<ProductInfo> productInfos) {
        this.productInfos = productInfos;
    }

    public List<ApparelImages> getApparelImages() {
        return apparelImages;
    }

    public void setApparelImages(List<ApparelImages> apparelImages) {
        this.apparelImages = apparelImages;
    }
}
