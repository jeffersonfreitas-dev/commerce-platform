package dev.jeffersonfreitas.order.domain.model;

import dev.jeffersonfreitas.order.domain.valueobject.Description;
import dev.jeffersonfreitas.order.domain.valueobject.Identity;
import dev.jeffersonfreitas.order.domain.valueobject.Name;
import dev.jeffersonfreitas.order.domain.valueobject.Price;

import java.math.BigDecimal;
import java.time.Instant;

public class Product {
    private final Identity uuid;
    private Name name;
    private Description description;
    private Price price;
    private boolean active;
    private final Instant createdAt;
    private Instant updatedAt;


    public Product(String description, String name, BigDecimal price){
        this.uuid = new Identity();
        this.name = new Name(name);
        this.description = new Description(description);
        this.price = new Price(price);
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
        activated();
    }

    public Product(String uuid, String name, String description, BigDecimal price, Instant createdAt, Instant updatedAt){
        this.uuid = new Identity(uuid);
        this.name = new Name(name);
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

    public String name(){
        return this.name.value();
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
