package org.secondtask.redismap;

import org.junit.jupiter.api.Test;
import org.secondtask.redismap.exception.InvalidParametersException;

import static org.junit.jupiter.api.Assertions.*;

class RedisMapTest {

    private final RedisMap redisMap = new RedisMap();

    @Test
    void testGetAndGetOnSuccess() {
        redisMap.put("key1", "value1");

        String expected = "value1";

        String actual = redisMap.get("key1");

        assertEquals(expected, actual);
    }

    @Test
    void testRemoveOnSuccess() {
        String expected = null;

        redisMap.remove("key1");

        String actual = redisMap.get("key1");

        assertEquals(expected, actual);
    }

    @Test
    void testPutOnFailed() {
        assertThrows(InvalidParametersException.class, () -> redisMap.put("", ""));
    }

    @Test
    void testRemoveOnFailed() {
        assertThrows(InvalidParametersException.class, () -> redisMap.remove(123));
    }

    @Test
    void testGetOnFailed() {
        assertThrows(InvalidParametersException.class, () -> redisMap.get(123));
    }
}