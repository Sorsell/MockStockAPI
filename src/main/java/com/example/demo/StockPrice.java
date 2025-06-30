package com.example.demo;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class StockPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symbol;
    private Double price;
    private LocalDate date;

    public StockPrice() {}

    public StockPrice(String symbol, Double price, LocalDate date) {
        this.symbol = symbol;
        this.price = price;
        this.date = date;
    }

    public Long getId() {return id;}
    public String getSymbol() {return symbol;}
    public void setSymbol(String symbol) {this.symbol = symbol;}
    public Double getPrice() {return price;}
    public void setPrice(Double price) {this.price = price;}
    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date) {this.date = date;}

}
