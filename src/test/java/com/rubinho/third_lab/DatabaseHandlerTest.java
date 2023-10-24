package com.rubinho.third_lab;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

@Testcontainers
public class DatabaseHandlerTest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.0")
            .withUsername("postgres")
            .withPassword("postgres")
            .withDatabaseName("postgres");
    @BeforeAll
    public static void startContainer() {
        postgres.start();
    }

    @AfterAll
    public static void stopContainer() {
        postgres.stop();
    }

    @Test
    public void addTest(){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        int count = databaseHandler.getAll().size();
        databaseHandler.add(databaseHandler.createRow(1.0f, 2.0f, 3.0f, "15:00:15", "00:00:10", true));
        Assertions.assertEquals(databaseHandler.getAll().size(), count + 1);
    }

    @Test
    public void clearTest(){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        List<Result> results;
        results = databaseHandler.getAll();
        databaseHandler.clear();
        Assertions.assertEquals(databaseHandler.getAll().size(), 0);

        for (Result addResult: results){
            databaseHandler.add(addResult);
        }
    }


}
