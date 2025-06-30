package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PolygonJsonParserTest {
    
    @Test
    public void testJsonDeserialization() throws Exception {
        String json = """
        {
            "ticker": "AAPL",
            "queryCount": 252,
            "resultsCount": 252,
            "adjusted": true,
            "results": [
                {
                    "v": 81964874,
                    "vw": 185.9465,
                    "o": 187.15,
                    "c": 185.64,
                    "h": 188.44,
                    "l": 183.885,
                    "t": 1704171600000,
                    "n": 1008871
                }
            ],
            "status": "OK",
            "request_id": "884f65a9adacc3e1b103fc1238b66c58",
            "count": 252
        }
        """;

        ObjectMapper objectMapper = new ObjectMapper();
        PolygonRequestDto response = objectMapper.readValue(json, PolygonRequestDto.class);

        assertEquals("AAPL", response.getTicker());
    }
}
