package com.DDN.login.dto.filter;

import java.io.Serializable;

public class BrandImagesDTO implements Serializable {
    private String title;

    private String imageLocalPath;

    private String imageURL;

    private BrandCategoryDTO brandInfo;

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

    public BrandCategoryDTO getBrandInfo() {
        return brandInfo;
    }

    public void setBrandInfo(BrandCategoryDTO brandInfo) {
        this.brandInfo = brandInfo;
    }
}

class BrandCategoryDTO implements Serializable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
