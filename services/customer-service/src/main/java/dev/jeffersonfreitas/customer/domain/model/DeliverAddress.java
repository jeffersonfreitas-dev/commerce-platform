package dev.jeffersonfreitas.customer.domain.model;

import java.util.Objects;

import dev.jeffersonfreitas.customer.domain.valueobject.Id;
import dev.jeffersonfreitas.customer.domain.valueobject.ZipCode;

public class DeliverAddress {

    private final Id id;
    private final String street;
    private final String number;
    private final String neighborhood;
    private final String city;
    private final ZipCode zipcode;
    private final String state;
    private final String reference;
    private final boolean active;
    private final boolean main;

    public DeliverAddress(String street, String number, String neighborhood, String city, String zipcode, String state, String reference, boolean main){
        this.id = new Id();
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.zipcode = new ZipCode(zipcode);
        this.state = state;
        this.reference = reference;
        this.active = true;
        this.main = main;
    }

    public DeliverAddress(String id, String street, String number, String neighborhood, String city, String zipcode, String state, String reference, boolean active, boolean main){
        this.id = new Id(id);
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.zipcode = new ZipCode(zipcode);
        this.state = state;
        this.reference = reference;
        this.active = active;
        this.main = main;
    }

    public Id getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getState() {
        return state;
    }

    public String getNumber() {
        return number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public ZipCode getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getReference() {
        return reference;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isMain() {
        return main;
    }


    @Override
    public boolean equals(Object o) {
        return this == o ||
            (o instanceof DeliverAddress other &&
            Objects.equals(id, other.id));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
