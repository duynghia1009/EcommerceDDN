package com.DDN.login.dto.filter;

import java.io.Serializable;

public class SearchSuggestionForThreeAttrDTO implements Serializable {
    Integer attr1_id;
    String attr1_type;
    Integer attr2_id;
    String attr2_type;
    Integer attr3_id;
    String attr3_type;

    public SearchSuggestionForThreeAttrDTO(Integer attr1_id, String attr1_type, Integer attr2_id, String attr2_type,
                                           Integer attr3_id, String attr3_type){
        this.attr1_id = attr1_id;
        this.attr1_type = attr1_type;
        this.attr2_id = attr2_id;
        this.attr2_type = attr2_type;
        this.attr3_id = attr3_id;
        this.attr3_type = attr3_type;
    }

    public Integer getAttr1_id() {
        return attr1_id;
    }

    public void setAttr1_id(Integer attr1_id) {
        this.attr1_id = attr1_id;
    }

    public String getAttr1_type() {
        return attr1_type;
    }

    public void setAttr1_type(String attr1_type) {
        this.attr1_type = attr1_type;
    }

    public Integer getAttr2_id() {
        return attr2_id;
    }

    public void setAttr2_id(Integer attr2_id) {
        this.attr2_id = attr2_id;
    }

    public String getAttr2_type() {
        return attr2_type;
    }

    public void setAttr2_type(String attr2_type) {
        this.attr2_type = attr2_type;
    }

    public Integer getAttr3_id() {
        return attr3_id;
    }

    public void setAttr3_id(Integer attr3_id) {
        this.attr3_id = attr3_id;
    }

    public String getAttr3_type() {
        return attr3_type;
    }

    public void setAttr3_type(String attr3_type) {
        this.attr3_type = attr3_type;
    }
}