package com.example.stocktrackerapp.service.serviceimplementation;


import com.example.stocktrackerapp.model.StockWrapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

//This class is help us decide if we want to refresh the price or not
@Service
public class RefreshServiceImpl {
    // we use the scheduler to schedule an execution, will set to true so it refreshed every 15 minute
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private final Map<StockWrapper, Boolean> stocksToRefresh;

    private static final Duration refreshPeriod = Duration.ofSeconds(15);

    public boolean shouldRefresh(final StockWrapper stock) {
        if(!stocksToRefresh.containsKey(stock)) {
            stocksToRefresh.put(stock, false);
            return true;
        }
        return stocksToRefresh.get(stock);
    }

    public RefreshServiceImpl(){
        stocksToRefresh= new HashMap<>();
    }

    private void shouldRefreshEvery5sec() {
        //scheduler takes in runnable
        scheduler.scheduleAtFixedRate(() ->
                stocksToRefresh.forEach((stock, value) -> {
                    if (stock.getLastAccessed().isBefore(LocalDateTime.now().minus(refreshPeriod))) {
                        System.out.println("Setting should refresh " + stock.getStock().getSymbol());
                        stocksToRefresh.remove(stock);
                        stocksToRefresh.put(stock.withLastAccessed(LocalDateTime.now()), true);
                    }
                }), 0, 15, SECONDS);


    }
}
