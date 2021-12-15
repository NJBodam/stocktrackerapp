package com.example.stocktrackerapp.service;

import com.example.stocktrackerapp.model.StockWrapper;

public interface RefreshServices {

    boolean shouldRefresh(final StockWrapper stock);

    void shouldRefreshEvery5sec();
}
