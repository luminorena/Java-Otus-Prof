package ru.otus.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.entities.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long>, ListCrudRepository<Cart, Long> {
}
