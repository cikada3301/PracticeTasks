package org.secondtask.redismap;

import org.secondtask.redismap.exception.InvalidParametersException;
import redis.clients.jedis.Jedis;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class RedisMap implements Map<String, String> {

    private final Jedis jedis;

    public RedisMap(String host, int port) {
        this.jedis = new Jedis(host, port);
    }

    @Override
    public String get(Object key) {
        if (!(key instanceof String)) {
            throw new InvalidParametersException("Key must be a String");
        }

        return jedis.get(key.toString());
    }

    @Override
    public String put(String key, String value) {
        if (key.isEmpty() || value.isEmpty()) {
            throw new InvalidParametersException("Key or value must not be a empty");
        }

        jedis.set(key, value);

        return value;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String remove(Object key) {
        if (!(key instanceof String)) {
            throw new InvalidParametersException("Key must be a String");
        }

        jedis.del(key.toString());

        return "";
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<String> values() {
        return null;
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return null;
    }
}
