package com.example.menu.item;

import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.*;

public class Item {

    private final Long id;

    @NotNull(message = "Name is required")
    @Pattern(regexp="^[a-zA-Z ]+$", message = "name must be a string")
    private final String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private final Long price;

    @NotNull(message = "Description is required")
    @Pattern(regexp="^a-zA-z ]+$", message = "Description must be a string")
    private final String description;

    @NotNull(message = "Image is required")
    @URL(message = "Image must be a URL")
    private final String image;

    public Item(
            Long id,
            String name,
            Long price,
            String description,
            String image
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    @Id
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    // Update an existing item with new information
    public Item updateWith(Item item) {
        return new Item(
                this.id,
                item.name,
                item.price,
                item.description,
                item.image
        );
    }
}
