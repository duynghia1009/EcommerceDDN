package com.DDN.login.model.images;

import com.DDN.login.model.categories.ProductBrandCategory;

import javax.persistence.*;

@Entity
public class BrandImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String imageLocalPath;
    private String imageURL;

    @ManyToOne(cascade = CascadeType.ALL, fetch =FetchType.LAZY)
    @JoinColumn(name="brand_id", referencedColumnName = "id")
    private ProductBrandCategory productBrandCategory;

    public BrandImages() {}

    public BrandImages(String title, String imageLocalPath, String imageURL) {
        this.title = title;
        this.imageLocalPath = imageLocalPath;
        this.imageURL = imageURL;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageLocalPath() {
        return imageLocalPath;
    }

    public void setImageLocalPath(String imageLocalPath) {
        this.imageLocalPath = imageLocalPath;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public ProductBrandCategory getProductBrandCategory() {
        return productBrandCategory;
    }

    public void setProductBrandCategory(ProductBrandCategory productBrandCategory) {
        this.productBrandCategory = productBrandCategory;
    }
}
