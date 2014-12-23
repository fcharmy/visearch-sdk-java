package com.visenze.visearch.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visenze.visearch.*;
import com.visenze.visearch.internal.http.ViSearchHttpClient;
import com.visenze.visearch.internal.http.ViSearchHttpClientImpl;
import com.visenze.visearch.internal.json.ViSenzeModule;

import java.util.List;

public class ViSearchClient implements ViSearch {

    private final DataOperations dataOperations;
    private final SearchOperations searchOperations;

    public ViSearchClient(String accessKey, String secretKey, String dataApiUrl, String searchApiUrl) {
        ViSearchHttpClient viSearchHttpClient = new ViSearchHttpClientImpl(accessKey, secretKey, new ObjectMapper().registerModule(new ViSenzeModule()));
        this.dataOperations = new DataOperationsImpl(viSearchHttpClient, dataApiUrl);
        this.searchOperations = new SearchOperationsImpl(viSearchHttpClient, searchApiUrl);
    }

    @Override
    public InsertTransaction insert(List<Image> imageList) {
        return dataOperations.insert(imageList);
    }

    @Override
    public PagedResult<InsertTransaction> getStatus(Integer page, Integer limit) {
        return dataOperations.getStatus(page, limit);
    }

    @Override
    public InsertTransaction getStatus(String transactionId) {
        return dataOperations.getStatus(transactionId);
    }

    @Override
    public void remove(List<Image> imageList) {
        dataOperations.remove(imageList);
    }

    @Override
    public PagedSearchResult<ImageResult> search(SearchParams searchParams) {
        return searchOperations.search(searchParams);
    }

    @Override
    public PagedSearchResult<ImageResult> colorSearch(ColorSearchParams colorSearchParams) {
        return searchOperations.colorSearch(colorSearchParams);
    }

    @Override
    public PagedSearchResult<ImageResult> uploadSearch(UploadSearchParams uploadSearchParams) {
        return searchOperations.uploadSearch(uploadSearchParams);
    }
}