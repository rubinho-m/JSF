package com.rubinho.third_lab;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ResultUnit");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    public Result createRow(String x, String y, String R, String currentTime, String executionTime, String isHit) {
        Result result = new Result();
        result.setX(x);
        result.setY(y);
        result.setR(R);
        result.setCurrentTime(currentTime);
        result.setExecutionTime(executionTime);
        result.setIsHit(isHit);

        return result;

    }

    public void add(Result result) {
        EntityTransaction transaction = entityManager.getTransaction();


        transaction.begin();
        entityManager.persist(result);
        transaction.commit();

    }

    public void clear() {
        EntityTransaction transaction = entityManager.getTransaction();



        transaction.begin();
        entityManager.createQuery("delete from Result").executeUpdate();
        transaction.commit();


    }

    public List<Result> getAll(){
        return entityManager.createQuery("select p from Result p ORDER BY p.id DESC", Result.class).getResultList();
    }
}
