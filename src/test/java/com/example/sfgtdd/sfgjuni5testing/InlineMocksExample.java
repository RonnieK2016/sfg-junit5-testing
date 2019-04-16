package com.example.sfgtdd.sfgjuni5testing;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class InlineMocksExample {

    @Test
    void inlineMockTest() {
        Map map = mock(Map.class);

        assertEquals(0, map.size());
    }
}
