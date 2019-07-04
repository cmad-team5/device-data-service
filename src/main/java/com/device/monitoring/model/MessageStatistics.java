package com.device.monitoring.model;

import java.util.Map;

public class MessageStatistics {
    private Map<String, Integer> statsMap;

    public Map<String, Integer> getStatsMap() {
        return statsMap;
    }

    public void setStatsMap(Map<String, Integer> statsMap) {
        this.statsMap = statsMap;
    }
}
