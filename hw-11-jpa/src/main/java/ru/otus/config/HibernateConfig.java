package ru.otus.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.otus.details.CrudOperations;
import ru.otus.entities.Customers;
import ru.otus.entities.Products;
import ru.otus.entities.Purchases;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Collectors;

public class HibernateConfig {
    private static final Log logger = LogFactory.getLog(MethodHandles.lookup().lookupClass());
    private static final CrudOperations crudOperations = new CrudOperations();

    public static SessionFactory getHibernateConfig() {
        try {
            InputStream input = SessionFactory.class.getClassLoader().getResourceAsStream("app.properties");
            Configuration configuration = new Configuration();
            Properties props = new Properties();
            props.load(input);

            props.put("hibernate.connection.driver_class", props.getProperty("db.driver"));
            props.put("hibernate.connection.url", props.getProperty("db.url"));
            props.put("hibernate.connection.username", props.getProperty("db.username"));
            props.put("hibernate.connection.password", props.getProperty("db.password"));
            props.put("hibernate.show_sql", true);
            props.put("hibernate.transaction.jta.platform", "jta");
            props.put("hibernate.current_session_context_class", "thread");
            configuration.setProperties(props);

            configuration.addAnnotatedClass(Customers.class);
            configuration.addAnnotatedClass(Products.class);
            configuration.addAnnotatedClass(Purchases.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            logger.info("Hibernate конфигурация создана успешно");


            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            logger.error("Инициализация SessionFactory завершилась с ошибкой" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void prepareInitialData() {
        Session session = null;
        try {
            SessionFactory factory = getHibernateConfig();

            String sql = Files.lines(Paths.get("shop.sql"))
                    .collect(Collectors.joining(" "));

            session = factory.openSession();
            session.beginTransaction();

            session.createNativeQuery(sql).executeUpdate();

            session.getTransaction().commit();
        } catch (IOException e) {
            logger.error("Ошибка чтения SQL скрипта", e);
        } catch (Exception ex) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            logger.error("Ошибка выполнения SQL запросов", ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public static void main(String[] args) {
        prepareInitialData();
        crudOperations.deleteCustomers(3L);
        crudOperations.deleteProducts(3L);
        crudOperations.findProductsByOneCustomer(1L);
        crudOperations.findProductsByCustomer(1L);
        crudOperations.findClientsByProduct(1L);
    }


}
