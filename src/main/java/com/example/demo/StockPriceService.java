package com.example.demo;

import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Optional;

@Service
public class StockPriceService {
    private final StockPriceRepository stockPriceRepository;

    public StockPriceService(StockPriceRepository stockPriceRepository) {
        this.stockPriceRepository = stockPriceRepository;
    }

    public int savePrices(PolygonRequestDto request,String ticker) {

        int insertedCount = 0;
        LocalDate date;

        for (PolygonRequestDto.Result r : request.getResults()) {
            if(!exists(r, ticker)) {

                date = getDate(r.getTimestamp());
                StockPrice stockPrice = new StockPrice(ticker, r.getClose(), date);
                stockPriceRepository.save(stockPrice);
                insertedCount++;
            }
        }
        return insertedCount;
    }

    public boolean deletePrice(String ticker, LocalDate date){
        Optional<StockPrice> existing = stockPriceRepository.findBySymbolAndDate(ticker, date);
        if (existing.isPresent()) {
            stockPriceRepository.delete(existing.get());
            return true;
        }
        return false;
    }

    private boolean exists(PolygonRequestDto.Result r,String ticker) {
        LocalDate date = Instant.ofEpochMilli(r.getTimestamp())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
            return stockPriceRepository.findBySymbolAndDate(ticker, date).isPresent();
    }

    private LocalDate getDate(long timestamp) {
        return Instant.ofEpochMilli(timestamp)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    
}
