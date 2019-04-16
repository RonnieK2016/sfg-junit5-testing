package com.example.sfgtdd.sfgjuni5testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MockitoExtensionExample {

    @Mock
    Map<String, Object> map;

    @Test
    void test() {
        map.put("key","object");
        assertEquals(0, map.size());
    }
}
