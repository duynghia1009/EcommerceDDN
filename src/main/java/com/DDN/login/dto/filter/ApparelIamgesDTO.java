package com.DDN.login.dto.filter;

import java.io.Serializable;

public class ApparelIamgesDTO implements Serializable {
    private String title;

    private String imageLocalPath;

    private String imageURL;

    private ApparelDTO apparelInfo;

    private GenderDTO genderInfo;

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

    public ApparelDTO getApparelInfo() {
        return apparelInfo;
    }

    public void setApparelInfo(ApparelDTO apparelInfo) {
        this.apparelInfo = apparelInfo;
    }

    public GenderDTO getGenderInfo() {
        return genderInfo;
    }

    public void setGenderInfo(GenderDTO genderInfo) {
        this.genderInfo = genderInfo;
    }
}

class ApparelDTO implements Serializable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class GenderDTO implements Serializable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
