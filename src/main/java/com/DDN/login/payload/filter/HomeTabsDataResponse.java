package com.DDN.login.payload.filter;

import com.DDN.login.dto.filter.BrandsAndApparelsDTO;

import java.io.Serializable;

public class HomeTabsDataResponse implements Serializable {
    private BrandsAndApparelsDTO men;
    private BrandsAndApparelsDTO women;
    private BrandsAndApparelsDTO boys;
    private BrandsAndApparelsDTO girls;
    private BrandsAndApparelsDTO essentials;
    private BrandsAndApparelsDTO homeAndLiving;

    public BrandsAndApparelsDTO getMen() {
        return men;
    }

    public void setMen(BrandsAndApparelsDTO men) {
        this.men = men;
    }

    public BrandsAndApparelsDTO getWomen() {
        return women;
    }

    public void setWomen(BrandsAndApparelsDTO women) {
        this.women = women;
    }

    public BrandsAndApparelsDTO getBoys() {
        return boys;
    }

    public void setBoys(BrandsAndApparelsDTO boys) {
        this.boys = boys;
    }

    public BrandsAndApparelsDTO getGirls() {
        return girls;
    }

    public void setGirls(BrandsAndApparelsDTO girls) {
        this.girls = girls;
    }

    public BrandsAndApparelsDTO getEssentials() {
        return essentials;
    }

    public void setEssentials(BrandsAndApparelsDTO essentials) {
        this.essentials = essentials;
    }

    public BrandsAndApparelsDTO getHomeAndLiving() {
        return homeAndLiving;
    }

    public void setHomeAndLiving(BrandsAndApparelsDTO homeAndLiving) {
        this.homeAndLiving = homeAndLiving;
    }
}
