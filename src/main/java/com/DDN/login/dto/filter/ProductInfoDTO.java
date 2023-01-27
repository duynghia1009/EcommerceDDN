package com.DDN.login.dto.filter;

import com.DDN.login.model.info.ProductInfo;

import java.io.Serializable;
import java.util.List;

public class ProductInfoDTO implements Serializable {

    private Long totalCount;
    private List<ProductInfo> products;

    public ProductInfoDTO(Long totalCount, List<ProductInfo> productInfos) {
        this.totalCount = totalCount;
        this.products = productInfos;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<ProductInfo> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInfo> products) {
        this.products = products;
    }
}
