package com.rubinho.third_lab.mbeans;

public interface StatisticsMXBean {
    float calculateProportion(int allHits, int missedHits);
    float getProportion();
}
