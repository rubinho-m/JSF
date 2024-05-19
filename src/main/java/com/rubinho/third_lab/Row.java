package com.rubinho.third_lab;


import com.rubinho.third_lab.mbeans.Counter;
import com.rubinho.third_lab.mbeans.CounterMXBean;
import com.rubinho.third_lab.mbeans.Statistics;
import com.rubinho.third_lab.mbeans.StatisticsMXBean;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Row {
    private float R;
    private final DatabaseHandler databaseHandler = new DatabaseHandler();
    private boolean YInitialized = false;
    private String x;
    private String y;
    private MBeanServer mbs;
    CounterMXBean counterMXBean;
    StatisticsMXBean statisticsMXBean;

    public Row() {
        this.mbs = ManagementFactory.getPlatformMBeanServer();
        this.counterMXBean = new Counter();
        this.statisticsMXBean = new Statistics();

        try {
            ObjectName counterName = new ObjectName("com.rubinho.third_lab.mbeans:type=Counter");
            ObjectName statisticsName = new ObjectName("com.rubinho.third_lab.mbeans:type=Statistics");
            mbs.registerMBean(counterMXBean, counterName);
            mbs.registerMBean(statisticsMXBean, statisticsName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Result> getRows() {
        return databaseHandler.getAll();

    }


    public boolean isYInitialized() {
        return YInitialized;
    }

    public void setYInitialized(boolean YInitialized) {
        this.YInitialized = YInitialized;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        YInitialized = true;
        return y;
    }

    public void setY(String y) {
        if (isYInitialized()) this.y = y;
    }

    public float getR() {
        if (R == 0) setR(1);
        return R;
    }

    public void setR(float r) {
        this.R = r;
    }

    public void clear() {
        databaseHandler.clear();
    }

    public void add() {
        long startTime = System.nanoTime();


        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        String msTime = String.format("%.6f", ((System.nanoTime() - startTime) / 1_000_000.0)).replace(',', '.');

        boolean isHit = check(Float.parseFloat(x), Float.parseFloat(y), R);

        resultMBeansHandle(Float.parseFloat(x), Float.parseFloat(y), R, isHit);

        Result newRow = databaseHandler.createRow(Float.parseFloat(x), Float.parseFloat(y), R, formattedDateTime, msTime, isHit);
        databaseHandler.add(newRow);


    }

    private void resultMBeansHandle(float x, float y, float R, boolean isHit) {
        counterMXBean.hitsInc(x, y, R);
        if (!isHit) counterMXBean.missedHitsInc();
        statisticsMXBean.calculateProportion(counterMXBean.getHitsCount(), counterMXBean.getMissedHitsCount());

    }

    private boolean check(float x, float y, float R) {
        return isInCircle(x, y, R) || isInTriangle(x, y, R) || isInRectangle(x, y, R);
    }

    private boolean isInCircle(double x, double y, double r) {
        return x <= 0 && y >= 0 && x * x + y * y <= r / 2 * r / 2;
    }


    private boolean isInRectangle(double x, double y, double r) {
        return x <= 0 && y <= 0 && x >= -r && y >= -r;
    }


    private boolean isInTriangle(double x, double y, double r) {
        return x >= 0 && y >= 0 && y <= r / 2 - x;
    }
}