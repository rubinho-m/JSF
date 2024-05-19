package com.rubinho.third_lab.mbeans;

import java.io.Serializable;

public class Statistics implements StatisticsMXBean, Serializable {
    private float proportion;

    @Override
    public float calculateProportion(int allHits, int missedHits) {
        proportion = (float) (allHits - missedHits) / allHits;
        return proportion;
    }

    @Override
    public float getProportion() {
        return proportion;
    }

}
