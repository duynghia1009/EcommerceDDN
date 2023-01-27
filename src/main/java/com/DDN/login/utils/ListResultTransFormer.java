package com.DDN.login.utils;

import com.DDN.login.dto.filter.FilterAttributesDTO;
import com.DDN.login.dto.filter.FilterAttributesWithTotalItemsDTO;
import com.DDN.login.dto.filter.SearchSuggestionForThreeAttrDTO;
import com.DDN.login.dto.filter.SearchSuggestionForTwoAttrDTO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListResultTransFormer {
    public List<FilterAttributesWithTotalItemsDTO>
    getFilterAttributesWithTotalItemsResultTransformer(String queryStr, HashMap<Integer, Object> mapParams,
                                                       EntityManager entityManager) {

        Query query = entityManager.createQuery(queryStr);

        if(mapParams != null) {
            mapParams.forEach(query::setParameter);
        }
        return query.unwrap(org.hibernate.query.Query.class)
                .setResultTransformer((IListResultTransformer)
                        (tuple, aliases) -> new FilterAttributesWithTotalItemsDTO((Integer) tuple[0],
                                (String) tuple[1], (Long) tuple[2]
                        )
                ).getResultList();
    }

    public List<FilterAttributesDTO>
    getFilterAttributesResultTransformer(String queryStr, HashMap<Integer, Integer> mapParams,
                                         EntityManager entityManager) {

        Query query = entityManager.createQuery(queryStr);

        if(mapParams != null) {
            mapParams.forEach(query::setParameter);
        }
        List<FilterAttributesDTO> listResult =  query.unwrap(org.hibernate.query.Query.class)
                .setResultTransformer((IListResultTransformer)
                        (tuple, aliases) -> new FilterAttributesDTO((Integer) tuple[0], (String) tuple[1]
                        )
                ).setMaxResults(10)
                .getResultList();
        return listResult;
    }

    public List<SearchSuggestionForThreeAttrDTO>
    getSearchSuggestionForThreeAttrResultTransformer(String queryStr,
                                                     EntityManager entityManager) {

        Query query = entityManager.createQuery(queryStr);
        List<SearchSuggestionForThreeAttrDTO> listResult =  query.unwrap(org.hibernate.query.Query.class)
                .setResultTransformer((IListResultTransformer)
                        (tuple, aliases) -> new SearchSuggestionForThreeAttrDTO((Integer) tuple[0], (String) tuple[1],
                                (Integer) tuple[2], (String) tuple[3],(Integer) tuple[4], (String) tuple[5])
                ).getResultList();
        return listResult;
    }

    public List<SearchSuggestionForTwoAttrDTO>
    getSearchSuggestionForTwoAttrResultTransformer(String queryStr,
                                                   EntityManager entityManager) {
        Query query = entityManager.createQuery(queryStr);
        List<SearchSuggestionForTwoAttrDTO> listResult = (List<SearchSuggestionForTwoAttrDTO>)query.unwrap(org.hibernate.query.Query.class)
                .setResultTransformer((IListResultTransformer)
                        (tuple, aliases) -> new SearchSuggestionForTwoAttrDTO((Integer) tuple[0], (String) tuple[1],
                                (Integer) tuple[2], (String) tuple[3])
                ).getResultList();
        return listResult;
    }
}
