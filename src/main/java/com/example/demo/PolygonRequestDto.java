package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PolygonRequestDto {

    @JsonProperty("ticker")
    private String ticker;

    @JsonProperty("results")
    private List<Result> results;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {

        @JsonProperty("t")
        private long timestamp;

        @JsonProperty("c")
        private double close;

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public double getClose() {
            return close;
        }

        public void setClose(double close) {
            this.close = close;
        }
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
