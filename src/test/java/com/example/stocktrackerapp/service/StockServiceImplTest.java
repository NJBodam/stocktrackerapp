package com.example.stocktrackerapp.service;

import com.example.stocktrackerapp.model.StockWrapper;
import com.example.stocktrackerapp.service.serviceimplementation.StockServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

// Draw an application diagram/ application architecture for the app

@SpringBootTest
class StockServiceImplTest {

    //This is just to test if the app works, not a valid test
    @Autowired
    private StockServiceImpl stockService;

    @Test
    void invoke() throws Exception {
        final StockWrapper stock = stockService.findStock("GOOG");
        System.out.println(stock.getStock());

        final BigDecimal price = stockService.findPrice(stock);
        System.out.println(price);

        final BigDecimal change = stockService.findLastChangePercent(stock);
        System.out.println(change);

        final BigDecimal mean200DayPercent = stockService.findChangeFrom200MeanPercent(stock);
        System.out.println(mean200DayPercent);

    }

    @Test
    void multiple() throws Exception {
        final List<StockWrapper> stocks = stockService.findStocks(Arrays.asList("GOOG", "ZM", "AMZN"));

        findPrices(stocks);

        Thread.sleep(16000);

        final StockWrapper aa = stockService.findStock("AA.L");
        stocks.add(aa);

        System.out.println(stockService.findPrice(aa));
    }

    private void findPrices(List<StockWrapper> stocks) {
        stocks.forEach(stock -> {
            try {
                System.out.println(stockService.findPrice(stock));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}