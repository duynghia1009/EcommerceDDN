package com.DDN.login.dto;

public class SearchSuggestionKeywordInfo {
    String keyword;
    StringBuilder link;
    Integer rank;

    public SearchSuggestionKeywordInfo() {}
    public SearchSuggestionKeywordInfo(String keyword, StringBuilder link, Integer rank) {
        this.keyword = keyword;
        this.link = new StringBuilder(link);
        this.rank = rank;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public StringBuilder getLink() {
        return link;
    }

    public void setLink(StringBuilder link) {
        this.link = link;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
