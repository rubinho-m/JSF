package com.rubinho.third_lab.mbeans;

public interface CounterMXBean {
    int hitsInc(float x, float y, float R);

    int missedHitsInc();

    int getHitsCount();

    int getMissedHitsCount();
}
