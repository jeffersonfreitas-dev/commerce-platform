package dev.jeffersonfreitas.order.domain.model;

import dev.jeffersonfreitas.order.domain.valueobject.*;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class Product {
    private final Identity uuid;
    private Name name;
    private Description description;
    private Price price;
    private Price originalPrice;
    private String urlImage;
    private List<String> imageGalery;
    private ProductCategory category;
    private Quantity stock;
    private double rating;
    private int reviews;
    private List<String> tags;
    private boolean active;
    private final Instant createdAt;
    private Instant updatedAt;


    public Product(String description, String name, BigDecimal price, BigDecimal originalPrice, String image, List<String> galery, String category, BigDecimal stock, List<String> tags){
        this.uuid = new Identity();
        this.name = new Name(name);
        this.description = new Description(description);
        this.price = new Price(price);
        this.originalPrice = new Price(originalPrice);
        this.urlImage = image;
        this.imageGalery = galery;
        this.category = ProductCategory.valueOf(category);
        this.stock = new Quantity(stock);
        this.rating = 0.0;
        this.reviews = 0;
        this.tags = tags;
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
        activated();
    }

    public Product(String uuid, String name, String description, BigDecimal price, BigDecimal originalPrice, String image, List<String> galery, String category, BigDecimal stock,
                   List<String> tags, double rating, int reviews, Instant createdAt, Instant updatedAt){
        this.uuid = new Identity(uuid);
        this.name = new Name(name);
        this.description = new Description(description);
        this.price = new Price(price);
        this.originalPrice = new Price(originalPrice);
        this.urlImage = image;
        this.imageGalery = galery;
        this.category = ProductCategory.valueOf(category);
        this.stock = new Quantity(stock);
        this.rating = rating;
        this.reviews = reviews;
        this.tags = tags;
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

    public BigDecimal originalPrice() {
        return originalPrice.value();
    }

    public String urlImage() {
        return urlImage;
    }

    public List<String> imageGalery() {
        return imageGalery;
    }

    public BigDecimal stock(){
        return stock.value();
    }

    public double rating() {
        return rating;
    }

    public int reviews() {
        return reviews;
    }

    public List<String> tags() {
        return tags;
    }

    public String category(){
        return this.category.getDescription();
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
