package com.backend.fitters.user.response;

import com.backend.fitters.user.dto.SearchDto;

public class SearchResponse {
    private SearchDto data;
    private String message;

    public SearchResponse() {

    }

    public SearchResponse(SearchDto data, String message) {
        this.data = data;
        this.message = message;
    }

    public SearchDto getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setData(SearchDto data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
