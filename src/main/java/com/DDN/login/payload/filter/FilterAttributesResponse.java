package com.DDN.login.payload.filter;

import com.DDN.login.dto.filter.FilterAttributesWithTotalItemsDTO;
import com.DDN.login.model.categories.SortByCategory;

import java.io.Serializable;
import java.util.List;

public class FilterAttributesResponse implements Serializable {
    private List<FilterAttributesWithTotalItemsDTO> brands;
    private List<FilterAttributesWithTotalItemsDTO> genders;
    private List<FilterAttributesWithTotalItemsDTO> apparels;
    private List<SortByCategory> sortby;
    private List<FilterAttributesWithTotalItemsDTO> prices;

    public FilterAttributesResponse(List<FilterAttributesWithTotalItemsDTO> brands, List<FilterAttributesWithTotalItemsDTO> genders,
                                    List<FilterAttributesWithTotalItemsDTO> apparels, List<FilterAttributesWithTotalItemsDTO> prices) {
        this.brands = brands;
        this.genders = genders;
        this.apparels = apparels;
        this.prices = prices;
    }

    public FilterAttributesResponse(){}

    public List<FilterAttributesWithTotalItemsDTO> getBrands() {
        return brands;
    }

    public void setBrands(List<FilterAttributesWithTotalItemsDTO> brands) {
        this.brands = brands;
    }

    public List<FilterAttributesWithTotalItemsDTO> getGenders() {
        return genders;
    }

    public void setGenders(List<FilterAttributesWithTotalItemsDTO> genders) {
        this.genders = genders;
    }

    public List<FilterAttributesWithTotalItemsDTO> getApparels() {
        return apparels;
    }

    public void setApparels(List<FilterAttributesWithTotalItemsDTO> apparels) {
        this.apparels = apparels;
    }

    public List<SortByCategory> getSortby() {
        return sortby;
    }

    public void setSortby(List<SortByCategory> sortby) {
        this.sortby = sortby;
    }

    public List<FilterAttributesWithTotalItemsDTO> getPrices() {
        return prices;
    }

    public void setPrices(List<FilterAttributesWithTotalItemsDTO> prices) {
        this.prices = prices;
    }
}
