package com.rubinho.third_lab;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Row {
    private float R;
    private final DatabaseHandler databaseHandler = new DatabaseHandler();
    private boolean YInitialized = false;
    private String x;
    private String y;

//    private List<EntityRow> rows = new ArrayList<>();

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
//        rows.clear();
        databaseHandler.clear();
//        System.out.println("Cleared");
    }

    public void add() {
        long startTime = System.nanoTime();


        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        String msTime = String.format("%.6f", ((System.nanoTime() - startTime) / 1_000_000.0)).replace(',', '.');

        boolean isHit = check(Float.parseFloat(x), Float.parseFloat(y), R);
//        EntityRow newRow = new EntityRow(x, y, String.valueOf(R), formattedDateTime, msTime, String.valueOf(isHit));
//        rows.add(new EntityRow(x, y, String.valueOf(R), formattedDateTime, msTime, String.valueOf(isHit)));

        Result newRow = databaseHandler.createRow(Float.parseFloat(x), Float.parseFloat(y), R, formattedDateTime, msTime, isHit);
        databaseHandler.add(newRow);


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