package com.example.stocktrackerapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;
import yahoofinance.Stock;

import java.time.LocalDateTime;

@Getter
@With
@AllArgsConstructor
@NoArgsConstructor
public class StockWrapper {
    private Stock stock;
    private LocalDateTime lastAccessed;

    public StockWrapper(final Stock stock){
        this.stock = stock;
        lastAccessed = LocalDateTime.now();
    }
}
