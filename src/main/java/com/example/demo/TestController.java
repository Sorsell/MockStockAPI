package com.example.demo;

import java.time.Instant;
import java.time.ZoneId;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    private final StockPriceRepository stockPriceRepository;
    private final StockPriceService stockPriceService;

    public TestController(StockPriceRepository stockPriceRepository,
                          StockPriceService stockPriceService) {
        this.stockPriceRepository = stockPriceRepository;
        this.stockPriceService = new StockPriceService(stockPriceRepository);
    }

    @PostMapping("/import")
    public String importData(@RequestBody PolygonRequestDto request) {

        if (request.getTicker() == null || request.getResults() == null) {
            return "Invalid request data";
        }
        String ticker = request.getTicker().toUpperCase();
        int insertedCount = stockPriceService.savePrices(request,ticker);

        return "Ticker: " + ticker + ", Added count: " + insertedCount;
    }

    @GetMapping("/getPrice")
    public String getPrice(
            @RequestParam String symbol,
            @RequestParam String date) {
                LocalDate localDate;
                try {
                    localDate = LocalDate.parse(date);
                } catch (Exception e) {
                    return "Invalid date format. Please use YYYY-MM-DD.";
                }
                Optional<StockPrice> result = stockPriceRepository.findBySymbolAndDate(symbol, localDate);
                return result
                .map(p -> "Latest price for " + symbol.toUpperCase() + " is: " + p.getPrice() + " on " + p.getDate())
                .orElse("No price data found for " + symbol.toUpperCase());
    }
}
