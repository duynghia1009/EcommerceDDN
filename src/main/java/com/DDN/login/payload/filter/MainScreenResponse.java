package com.DDN.login.payload.filter;

import com.DDN.login.dto.filter.ApparelIamgesDTO;
import com.DDN.login.dto.filter.BrandImagesDTO;
import com.DDN.login.model.images.CarouselImages;

import java.io.Serializable;
import java.util.List;

public class MainScreenResponse implements Serializable {

    private List<BrandImagesDTO> brands;
    private List<ApparelIamgesDTO> apparels;
    private List<CarouselImages> carousels;

    public MainScreenResponse(List<BrandImagesDTO> brands,
                              List<ApparelIamgesDTO> apparels,
                              List<CarouselImages> carousels) {
        this.brands = brands;
        this.apparels = apparels;
        this.carousels = carousels;
    }

    public MainScreenResponse() {}

    public List<BrandImagesDTO> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandImagesDTO> brands) {
        this.brands = brands;
    }

    public List<ApparelIamgesDTO> getApparels() {
        return apparels;
    }

    public void setApparels(List<ApparelIamgesDTO> apparels) {
        this.apparels = apparels;
    }

    public List<CarouselImages> getCarousels() {
        return carousels;
    }

    public void setCarousels(List<CarouselImages> carousels) {
        this.carousels = carousels;
    }
}
