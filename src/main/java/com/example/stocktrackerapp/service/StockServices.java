package com.example.stocktrackerapp.service;

import com.example.stocktrackerapp.model.StockWrapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface StockServices {
    StockWrapper findStock(final String ticker);

    List<StockWrapper> findStocks(final List<String> tickers);

    BigDecimal findPrice(final StockWrapper stock) throws Exception;

    BigDecimal findLastChangePercent(final StockWrapper stock) throws Exception;

    BigDecimal findChangeFrom200MeanPercent(final StockWrapper stock) throws IOException;
}
