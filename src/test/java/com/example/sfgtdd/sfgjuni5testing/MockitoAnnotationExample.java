package com.example.sfgtdd.sfgjuni5testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MockitoAnnotationExample {

    @Mock
    Map<String, Object> map;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void test() {
        map.put("key","object");
        assertEquals(0, map.size());
    }
}
