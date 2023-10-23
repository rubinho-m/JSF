package com.rubinho.third_lab;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="points")
public class Result implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "x")
    private String x;
    @Column(name = "y")
    private String y;
    @Column(name = "R")
    private String R;
    @Column(name = "currentTime")
    private String currentTime;
    @Column(name = "executionTime")
    private String executionTime;
    @Column(name = "isHit")
    private String isHit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getR() {
        return R;
    }

    public void setR(String r) {
        R = r;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public String getIsHit() {
        return isHit;
    }

    public void setIsHit(String isHit) {
        this.isHit = isHit;
    }
}
