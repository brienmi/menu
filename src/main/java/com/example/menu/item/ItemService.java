package com.example.menu.item;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
@EnableMapRepositories

public class ItemService {
    private final CrudRepository<Item, Long> repository;

    public ItemService(CrudRepository<Item, Long> repository) {
        this.repository = repository;

        // Used for items always on menu or in stock.
        this.repository.saveAll(defaultItems());
    }

    // Items added to menu
    private static List<Item> defaultItems() {
        return List.of(
                new Item(1L, "Burger", 599L, "Tasty", "https://s3-media0.fl.yelpcdn.com/bphoto/P_CmLdKIqL3zl5RrQtSv0g/348s.jpg"),
                new Item(2L, "Pizza", 299L, "Cheesy", "https://popmenucloud.com/cdn-cgi/image/width%3D1200%2Cheight%3D1200%2Cfit%3Dscale-down%2Cformat%3Dauto%2Cquality%3D60/bhcgpkjt/9c03b953-1de2-48c0-963e-38a63de810df.jpg"),
                new Item( 3L, "Tea", 199L, "Informative", "https://www.verywellhealth.com/thmb/wOpYMxG1V_VxYcp4iJRmxRO4lZc=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/GettyImages-693893647-588d21e413dd411cb1f2b0a0ea3e02da.jpg")
        );
    }

    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();
        Iterable<Item> items = repository.findAll();
        items.forEach(list::add);
        return list;
    }

    public Optional<Item> find(Long id) {
        return repository.findById(id);
    }

    public Item create(Item item) {
        // Adds current time stamp in order to keep the item ID unique.

        Item copy = new Item(
                new Date().getTime(),
                item.getName(),
                item.getPrice(),
                item.getDescription(),
                item.getImage()
        );
        return repository.save(copy);

    }

    public Optional<Item> update( Long id, Item newItem) {
        // Only updates an item if it can be found first.
        return repository.findById(id)
                .map(oldItem -> {
                    Item updated = oldItem.updateWith(newItem);
                    return repository.save(updated);
        });

    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
