package ru.otus.details;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import ru.otus.config.HibernateConfig;
import ru.otus.entities.Customers;
import ru.otus.entities.Products;
import ru.otus.entities.Purchases;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class CrudOperations {
    private static final Log logger = LogFactory.getLog(MethodHandles.lookup().lookupClass());

    public void deleteCustomers(long deletedCustomerId) {
        try (Session session = HibernateConfig.getHibernateConfig().getCurrentSession()) {
            session.beginTransaction();
            Customers customers = session.get(Customers.class, deletedCustomerId);
            session.delete(customers);
            logger.info("Клиент с id = " + deletedCustomerId + " удалён");
            session.getTransaction().commit();
        }
    }

    public void deleteProducts(long deletedProductId) {
        try (Session session = HibernateConfig.getHibernateConfig().getCurrentSession()) {
            session.beginTransaction();
            Products products = session.get(Products.class, deletedProductId);
            session.delete(products);
            logger.info("Продукт с id = " + deletedProductId + " удалён");
            session.getTransaction().commit();
        }
    }

    public void findProductsByOneCustomer(long id) {
        try (Session session = HibernateConfig.getHibernateConfig().getCurrentSession()) {
            session.beginTransaction();
            Purchases purchases = session.get(Purchases.class, id);
            logger.info("\nтовар: " + purchases.getProduct().getName() +
                    "\nцена товара: " + purchases.getBuyPrice()
                    + "\nвремя покупки: " + purchases.getBuyTime());
            session.getTransaction().commit();
        }
    }

    public void findProductsByCustomer(long id) {
        try (Session session = HibernateConfig.getHibernateConfig().getCurrentSession()) {
            session.beginTransaction();
            Customers customers = session.get(Customers.class, id);
            logger.info("\nклиент: " + customers.getName() +
                    "\nтовары: " + customers.getPurchases());
            session.getTransaction().commit();

        }
    }

    public void findClientsByProduct(long productId) {
        try (Session session = HibernateConfig.getHibernateConfig().getCurrentSession()) {
            session.beginTransaction();

            List<Purchases> purchasesList = session.createQuery("FROM Purchases p WHERE p.product.id = :productId", Purchases.class)
                    .setParameter("productId", productId)
                    .getResultList();

            for (Purchases purchase : purchasesList) {
                logger.info("\nТовар: " + purchase.getProduct().getName() +
                        "\nКлиент: " + purchase.getCustomer().getName() +
                        "\nЦена покупки: " + purchase.getBuyPrice() +
                        "\nВремя покупки: " + purchase.getBuyTime());
            }

            session.getTransaction().commit();
        }
    }


}
