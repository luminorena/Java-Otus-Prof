package ru.otus.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.entities.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long>, ListCrudRepository<Item, Long> {
}
