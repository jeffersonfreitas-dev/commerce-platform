package dev.jeffersonfreitas.order.domain.model;

import dev.jeffersonfreitas.order.domain.valueobject.Description;
import dev.jeffersonfreitas.order.domain.valueobject.Identity;
import dev.jeffersonfreitas.order.domain.valueobject.Price;

import java.math.BigDecimal;
import java.time.Instant;

public class Product {
    private final Identity uuid;
    private Description description;
    private Price price;
    private boolean active;
    private final Instant createdAt;
    private Instant updatedAt;


    public Product(String description, BigDecimal price){
        this.uuid = new Identity();
        this.description = new Description(description);
        this.price = new Price(price);
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
        activated();
    }

    public Product(String uuid, String description, BigDecimal price, Instant createdAt, Instant updatedAt){
        this.uuid = new Identity(uuid);
        this.description = new Description(description);
        this.price = new Price(price);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Product(Product updatedProduct){
        this.uuid = updatedProduct.uuid;
        this.description = updatedProduct.description;
        this.price = updatedProduct.price;
        this.createdAt = updatedProduct.createdAt;
        this.updatedAt = Instant.now();
        this.active = updatedProduct.active;
    }


    public void activated(){
        this.active = true;
    }

    public String uuid() {
        return uuid.value();
    }

    public String description() {
        return description.value();
    }

    public BigDecimal price() {
        return price.value();
    }

    public Instant createdAt() {
        return createdAt;
    }

    public Instant updatedAt() {
        return updatedAt;
    }

    public void setDescription(String descriptionInput) {
        this.description = new Description(descriptionInput);
    }

    public void setPrice(BigDecimal price) {
        this.price = new Price(price);
    }
}
