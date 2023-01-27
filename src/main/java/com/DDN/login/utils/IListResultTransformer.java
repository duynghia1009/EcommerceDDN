package com.DDN.login.utils;

import org.hibernate.transform.ResultTransformer;

import java.util.List;

@FunctionalInterface
public interface  IListResultTransformer extends ResultTransformer {
//   @Override
//   public Object transformTuple(Object[] var1, String[] var2){
//
//   }

    @Override
    default List transformList(List tuples) {
        return tuples;
    }
}
