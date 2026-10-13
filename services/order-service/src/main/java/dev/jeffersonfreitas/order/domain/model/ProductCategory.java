package dev.jeffersonfreitas.order.domain.model;

public enum ProductCategory {

    ELETRONICO("Eletrônico"),
    MODA("Moda"),
    CASA("Casa"),
    ESPORTES("Esportes");

    private final String description;

    ProductCategory(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

}
