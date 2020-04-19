package com.weatherapp.weather.history;

import com.weatherapp.weather.domain.Weather;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections4.queue.CircularFifoQueue;

public class InMemoryHistoryService implements HistoryService {

    private int maxEntriesPerLocation;
    private Map<String, Collection<Weather>> history;

    public InMemoryHistoryService() {
        this(5);
    }

    public InMemoryHistoryService(int maxEntriesPerLocation) {
        this.history = new HashMap<>();
        this.maxEntriesPerLocation = maxEntriesPerLocation;
    }

    @Override public Collection<Weather> list(String location) {
        return history.getOrDefault(location, Collections.emptyList());
    }

    @Override public void add(String location, Weather weather) {
        if (history.containsKey(location)) {
            history.get(location).add(weather);
        } else {
            Collection locationHistory = sizeLimitedCollection();
            locationHistory.add(weather);
            history.put(location, locationHistory);
        }
    }

    private Collection sizeLimitedCollection() {
        // CircularFifoQueue is a first-in first-out queue with a
        // fixed size that replaces its oldest element if full.
        return new CircularFifoQueue(maxEntriesPerLocation);
    }
}
