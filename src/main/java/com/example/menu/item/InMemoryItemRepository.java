package com.example.menu.item;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 Interface for CRUD operations.
 Item = objects that will be stored
 Long = identifier for a specific Item
*/

@Repository
public interface InMemoryItemRepository extends CrudRepository<Item, Long> {

}
