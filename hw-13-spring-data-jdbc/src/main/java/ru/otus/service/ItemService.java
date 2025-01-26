package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dtos.ItemDTO;
import ru.otus.entities.Item;
import ru.otus.repositories.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAllItems(){
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(long id){
       return itemRepository.findById(id);
    }

    public Item createNewItemIn(ItemDTO itemDTO){
        Item item = new Item(null, itemDTO.price(), itemDTO.discount(),
                itemDTO.shortName(), itemDTO.description());
        return itemRepository.save(item);

    }

    public Item updateItem(ItemDTO itemDTO) {
        if (!itemRepository.existsById(itemDTO.id())) {
            throw new RuntimeException("Элемент с указанным id не найден: " + itemDTO.id());
        }

        Item item = new Item(itemDTO.id(), itemDTO.price(), itemDTO.discount(),
                itemDTO.shortName(), itemDTO.description());

        return itemRepository.save(item);
    }
    public void deleteItemById(long id) {
        itemRepository.deleteById(id);
    }
}
