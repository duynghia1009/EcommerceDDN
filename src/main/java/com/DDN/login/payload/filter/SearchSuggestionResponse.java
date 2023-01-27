package com.DDN.login.payload.filter;

import com.DDN.login.dto.filter.SearchSuggestionForThreeAttrDTO;
import com.DDN.login.dto.filter.SearchSuggestionForTwoAttrDTO;
import com.DDN.login.model.categories.ApparelCategory;
import com.DDN.login.model.categories.GenderCategory;
import com.DDN.login.model.categories.ProductBrandCategory;

import java.io.Serializable;
import java.util.List;

public class SearchSuggestionResponse implements Serializable {
    List<GenderCategory> genderKeywords;
    List<ProductBrandCategory> brandKeywords;
    List<ApparelCategory> apparelKeywords;
    List<SearchSuggestionForTwoAttrDTO> genderApparelKeywords;
    List<SearchSuggestionForTwoAttrDTO> genderBrandKeywords;
    List<SearchSuggestionForTwoAttrDTO> apparelBrandKeywords;
    List<SearchSuggestionForThreeAttrDTO> threeAttrKeywords;
    List<String> productKeywords;

    public  SearchSuggestionResponse(List<GenderCategory> genderKeywords, List<ProductBrandCategory> brandKeywords,
                                     List<ApparelCategory> apparelKeywords, List<SearchSuggestionForTwoAttrDTO> genderApparelKeywords,
                                     List<SearchSuggestionForTwoAttrDTO> genderBrandKeywords, List<SearchSuggestionForTwoAttrDTO> apparelBrandKeywords,
                                     List<SearchSuggestionForThreeAttrDTO> threeAttrKeywords,List<String> productKeywords) {
        this.genderKeywords = genderKeywords;
        this.brandKeywords = brandKeywords;
        this.apparelKeywords = apparelKeywords;
        this.genderApparelKeywords = genderApparelKeywords;
        this.genderBrandKeywords = genderBrandKeywords;
        this.apparelBrandKeywords = apparelBrandKeywords;
        this.threeAttrKeywords = threeAttrKeywords;
        this.productKeywords = productKeywords;

    }

    public List<GenderCategory> getGenderKeywords() {
        return genderKeywords;
    }

    public void setGenderKeywords(List<GenderCategory> genderKeywords) {
        this.genderKeywords = genderKeywords;
    }

    public List<ProductBrandCategory> getBrandKeywords() {
        return brandKeywords;
    }

    public void setBrandKeywords(List<ProductBrandCategory> brandKeywords) {
        this.brandKeywords = brandKeywords;
    }

    public List<ApparelCategory> getApparelKeywords() {
        return apparelKeywords;
    }

    public void setApparelKeywords(List<ApparelCategory> apparelKeywords) {
        this.apparelKeywords = apparelKeywords;
    }

    public List<SearchSuggestionForTwoAttrDTO> getGenderApparelKeywords() {
        return genderApparelKeywords;
    }

    public void setGenderApparelKeywords(List<SearchSuggestionForTwoAttrDTO> genderApparelKeywords) {
        this.genderApparelKeywords = genderApparelKeywords;
    }

    public List<SearchSuggestionForTwoAttrDTO> getGenderBrandKeywords() {
        return genderBrandKeywords;
    }

    public void setGenderBrandKeywords(List<SearchSuggestionForTwoAttrDTO> genderBrandKeywords) {
        this.genderBrandKeywords = genderBrandKeywords;
    }

    public List<SearchSuggestionForTwoAttrDTO> getApparelBrandKeywords() {
        return apparelBrandKeywords;
    }

    public void setApparelBrandKeywords(List<SearchSuggestionForTwoAttrDTO> apparelBrandKeywords) {
        this.apparelBrandKeywords = apparelBrandKeywords;
    }

    public List<SearchSuggestionForThreeAttrDTO> getThreeAttrKeywords() {
        return threeAttrKeywords;
    }

    public void setThreeAttrKeywords(List<SearchSuggestionForThreeAttrDTO> threeAttrKeywords) {
        this.threeAttrKeywords = threeAttrKeywords;
    }

    public List<String> getProductKeywords() {
        return productKeywords;
    }

    public void setProductKeywords(List<String> productKeywords) {
        this.productKeywords = productKeywords;
    }
}
