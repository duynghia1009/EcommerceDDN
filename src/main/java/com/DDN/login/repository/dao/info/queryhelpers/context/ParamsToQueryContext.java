package com.DDN.login.repository.dao.info.queryhelpers.context;

import java.util.HashMap;
import java.util.List;

public class ParamsToQueryContext {
    String sortBy;
    HashMap<Integer, Object> mapParams;
    List<String> conditions;
    String[] pageInfo;

    public ParamsToQueryContext(String sortBy, HashMap<Integer, Object> mapParams,
                                List<String> conditions, String[] pageInfo) {
        this.sortBy = sortBy;
        this.mapParams = mapParams;
        this.conditions = conditions;
        this.pageInfo = pageInfo;
    }
    public ParamsToQueryContext() {}

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public HashMap<Integer, Object> getMapParams() {
        return mapParams;
    }

    public void setMapParams(HashMap<Integer, Object> mapParams) {
        this.mapParams = mapParams;
    }

    public List<String> getConditions() {
        return conditions;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }

    public String[] getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(String[] pageInfo) {
        this.pageInfo = pageInfo;
    }
}
