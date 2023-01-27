package com.DDN.login.repository.dao.info.queryhelpers;

import com.DDN.login.repository.dao.info.queryhelpers.context.ParamsToQueryContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductQueryHelper {
    enum QueryType {
        genders, apparels, brands, prices, category, sortby, page, productname;
    }

    private final int NEWEST = 1;
    private final int POPULARITY = 2;
    private final int LOW_TO_HIGH = 3;
    private final int HIGH_TO_LOW = 4;

    class MapParameterKey{
        private Integer key = 1;

        public void increment() {
            ++key;
        }
        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }
    }

    public void prepareConditionListById(HashMap<Integer,Object> mapParameters, String data, MapParameterKey mapParameterKey,
                                         List<String> conditions, String field){
        List<String> tempList = new ArrayList<>();
        for(String val : data.split(",")) {
            mapParameters.put(mapParameterKey.getKey(),Integer.parseInt(val));
            tempList.add("?" + mapParameterKey.getKey());
            mapParameterKey.increment();
        }
        if(data.length() > 0){
            conditions.add(String.format("(%s IN (%s))", field, String.join(",", tempList)));
        }
    }

    public void prepareConditionListByName(HashMap<Integer,Object> mapParameters, String data,MapParameterKey mapParameterKey,
                                           List<String> conditions, String field){
        List<String> tempList = new ArrayList<>();
        for(String val : data.split(",")){
            mapParameters.put(mapParameterKey.getKey(), val);
            tempList.add("?" + mapParameterKey.getKey());
            mapParameterKey.increment();
        }
        if (data.length() > 0) {
            conditions.add(String.format("(%s IN (%s))", field, String.join(",", tempList)));
        }
    }

    public ParamsToQueryContext getParamsToQueryMap(HashMap<String, String> conditionMap) {
        if(conditionMap == null) {
            return null;
        }

        String[] pagInfo = null;
        List<String> conditions = new ArrayList<>();
        String sortBy = " order by p.ratings desc";
        HashMap<Integer,Object> mapParams = new HashMap<>();
        MapParameterKey mapParameterKey = new MapParameterKey();
        boolean applyFilter = false;

        for(Map.Entry<String, String> entry : conditionMap.entrySet()) {
            switch(QueryType.valueOf(entry.getKey())){
                case genders:
                    prepareConditionListById(mapParams, entry.getValue(), mapParameterKey,
                            conditions, "p.genderCategory.id");
                    applyFilter = true;
                    break;
                case apparels:
                    prepareConditionListById(mapParams, entry.getValue(), mapParameterKey,
                            conditions, "p.apparelCategory.id");
                    applyFilter = true;
                    break;
                case brands:
                    prepareConditionListById(mapParams, entry.getValue(), mapParameterKey,
                            conditions, "p.productBrandCategory.id");
                    applyFilter = true;
                    break;

                case prices:
                    prepareConditionListById(mapParams, entry.getValue(), mapParameterKey,
                            conditions, "p.priceRangeCategory.id");
                    applyFilter = true;
                    break;

                case sortby:
                    switch(Integer.parseInt(entry.getValue())) {
                        case NEWEST:
                            sortBy = " order by p.pulicationDate desc";
                            break;
                        case POPULARITY:
                            sortBy = " order by p.ratings desc";
                            break;
                        case LOW_TO_HIGH:
                            sortBy = " order by p.price asc";
                            break;
                        case HIGH_TO_LOW:
                            sortBy = " order by p.price desc";
                            break;
                    }
                    break;
                case page:
                    pagInfo = entry.getValue().split(",");
                    break;
                case productname:
                    prepareConditionListByName(mapParams,entry.getValue(),mapParameterKey,
                            conditions,"p.name");
                    break;
                default:
                    System.out.println("UnsupportedType");
            }
        }

        if(!applyFilter) {
            conditions.add(String.format(" (1 = ?%d)", mapParameterKey.getKey()));
            mapParams.put(mapParameterKey.getKey(), 1);
            mapParameterKey.increment();
        }

        System.out.println("condition = " + String.join(" AND ", conditions));

        if (conditions.isEmpty()) {
            return null;
        }

        return new ParamsToQueryContext(sortBy, mapParams, conditions, pagInfo);
    }
}
