package com.rubinho.third_lab.mbeans;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.io.Serializable;

public class Counter extends NotificationBroadcasterSupport implements CounterMXBean, Serializable {
    private int hitsCount = 0;
    private int missedHitsCount = 0;
    int sequenceNumber = 1;

    @Override
    public int hitsInc(float x, float y, float R) {
        hitsCount++;
        if (!checkIfInVisibleArea(x, y, R)) {
            final Notification notification = new Notification("OutOfVisibleZone", this, sequenceNumber++, "Point of visible zone, don't worry.");
            sendNotification(notification);
        }
        return hitsCount;
    }

    @Override
    public int missedHitsInc() {
        missedHitsCount++;
        return missedHitsCount;
    }

    @Override
    public int getHitsCount() {
        return hitsCount;
    }

    @Override
    public int getMissedHitsCount() {
        return missedHitsCount;
    }

    private boolean checkIfInVisibleArea(float x, float y, float R) {
        return !(Math.abs(x) >= Math.abs(R) * 1.3 || Math.abs(y) >= Math.abs(R) * 1.3); // видно ли точку
    }

}
