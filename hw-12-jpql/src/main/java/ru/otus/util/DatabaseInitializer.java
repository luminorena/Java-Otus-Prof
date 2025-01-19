package ru.otus.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class DatabaseInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseInitializer.class);
    private static final String ENTITY_PACKAGE = "ru.otus.entity.";

    private DatabaseInitializer() {
    }

    public static void main(String[] args) {

        LiquibaseRunner.runMigrations();
        LOGGER.info("LiquibaseRunner done.");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SingleUnit");

        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {


            entityManager.getTransaction().begin();

            logTableContents(entityManager, "Address");
            logTableContents(entityManager, "Client");
            logTableContents(entityManager, "Phone");

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            LOGGER.error("An error occurred while loading the entity class.", e);
        } finally {
            entityManagerFactory.close();
        }
    }

    private static void logTableContents(EntityManager entityManager, String entityClassName) {
        try {
            Class<?> entityClass = Class.forName(ENTITY_PACKAGE + entityClassName);

            List<?> resultList = entityManager.createQuery("SELECT e FROM " + entityClassName + " e", entityClass).getResultList();

            LOGGER.info("Table '{}' contains {} records:", entityClassName, resultList.size());

            for (Object entity : resultList) {
                LOGGER.info("{}: {}", entityClassName, entity);
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error("Entity class not found: {}", entityClassName, e);
        }
    }
}
