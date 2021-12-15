package com.example.stocktrackerapp.service.serviceimplementation;


import com.example.stocktrackerapp.model.StockWrapper;
import com.example.stocktrackerapp.service.StockServices;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


// will interact directly with yahoofinance API
@Service
public class StockServiceImpl implements StockServices {

    private final RefreshServiceImpl refreshServiceImpl;

    public StockServiceImpl(RefreshServiceImpl refreshService) {
        this.refreshServiceImpl = refreshService;
    }
    @Override
    public StockWrapper findStock(final String ticker) {
        try{
            return new StockWrapper(YahooFinance.get(ticker));
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
        return null;
    }
    @Override
    public List<StockWrapper> findStocks(final List<String> tickers) {
        return tickers.stream().map(this::findStock).filter(Objects::nonNull).collect(Collectors.toList());
    }

    // The find price method of the yahoofinance api returns a BigDecimal
    @Override
    public BigDecimal findPrice(final StockWrapper stock) throws Exception {
        //getQuote takes a boolean that represents if we want to refresh to get the latest price
        return stock.getStock().getQuote(refreshServiceImpl.shouldRefresh(stock)).getPrice();
    }

    @Override
    public BigDecimal findLastChangePercent(final StockWrapper stock) throws Exception {
        return stock.getStock().getQuote(refreshServiceImpl.shouldRefresh(stock)).getChangeInPercent();
    }

    @Override
    public BigDecimal findChangeFrom200MeanPercent(final StockWrapper stock) throws IOException {
        return stock.getStock().getQuote(refreshServiceImpl.shouldRefresh(stock)).getChangeFromAvg200InPercent();
    }

   /* public void viewDashboard(Model model) {
        StockWrapper stockWrapper = new StockWrapper();
        List<StockWrapper> stocks = this.findStocks(List<> tickers);
    }
*/

}
