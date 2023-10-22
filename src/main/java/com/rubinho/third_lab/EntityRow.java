package com.rubinho.third_lab;



public class EntityRow  {
    private final String x;
    private final String y;
    private final String R;
    private final String currentTime;
    private final String executionTime;
    private final String isHit;


    public EntityRow(String x, String y, String r, String currentTime, String executionTime, String isHit) {
        this.x = x;
        this.y = y;
        R = r;
        this.currentTime = currentTime;
        this.executionTime = executionTime;
        this.isHit = isHit;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getR() {
        return R;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public String getIsHit() {
        return isHit;
    }
}
