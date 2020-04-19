package com.weatherapp.weather.history;

import com.weatherapp.weather.domain.Weather;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;

public class InMemoryHistoryServiceTest {

    @Test
    public void listShouldReturnHistory() {
        int maxEntriesPerLocation = 2;
        String searchedLocation = "loc1";

        InMemoryHistoryService history = new InMemoryHistoryService(maxEntriesPerLocation);
        history.add(searchedLocation, new Weather(1, 1, false));
        history.add(searchedLocation, new Weather(2, 2, false));
        history.add(searchedLocation, new Weather(3, 3, false));

        Collection<Weather> lastResults = history.list(searchedLocation);
        Assert.assertEquals(maxEntriesPerLocation, lastResults.size(), 0);
    }

    @Test
    public void listShouldReturnEmptyHistory() {
        String searchedLocation = "loc1";
        InMemoryHistoryService history = new InMemoryHistoryService();

        Collection<Weather> lastResults = history.list(searchedLocation);
        Assert.assertEquals(0, lastResults.size(), 0);
    }
}
