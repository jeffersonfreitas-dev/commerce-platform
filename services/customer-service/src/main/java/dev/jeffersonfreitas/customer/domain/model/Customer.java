package dev.jeffersonfreitas.customer.domain.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import dev.jeffersonfreitas.customer.domain.valueobject.BirthDate;
import dev.jeffersonfreitas.customer.domain.valueobject.Email;
import dev.jeffersonfreitas.customer.domain.valueobject.Id;
import dev.jeffersonfreitas.customer.domain.valueobject.Name;

public class Customer {
    private final Id id;
    private final Name name;
    private final BirthDate birthdate;
    private final Email email;
    private final Instant createdAt;
    private final Set<DeliverAddress> addresses;

    
    public Customer(String name, String email, LocalDate birthdate, DeliverAddress address){
        addresses = new HashSet<>();
        this.id = new Id();
        this.name = new Name(name);
        this.email = new Email(email);
        this.birthdate = new BirthDate(birthdate);
        this.addresses.add(address);
        this.createdAt = Instant.now();
    }

    public Customer(Id id, Name name, BirthDate birthdate, Email email, Instant createdAt, Set<DeliverAddress> addresses){
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.createdAt = createdAt;
        this.addresses = addresses;
    }


    public Id getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public BirthDate getBirthdate() {
        return birthdate;
    }

    public Email getEmail() {
        return email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Set<DeliverAddress> getAddresses() {
        return addresses;
    }
}
