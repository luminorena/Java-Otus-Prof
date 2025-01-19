package ru.otus;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.entity.Client;

import java.util.List;


public class JPAQueries {
    private static final Logger LOGGER = LoggerFactory.getLogger(JPAQueries.class);

    private static final String PERSISTENCE_UNIT_NAME = "SingleUnit";

    public static void main(String[] args) {
        queries();

    }

    public static void queries() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = emf.createEntityManager();

        List<Client> clientList = entityManager
                .createQuery("SELECT DISTINCT C FROM Client C " +
                        "JOIN FETCH C.address A " +
                        "JOIN FETCH C.phones P", Client.class)
                .getResultList();

        entityManager.close();

        LOGGER.info("{}", clientList.size());


    }
}
