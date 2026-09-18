package dev.jeffersonfreitas.customer.infra.out.persistence.customer;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deliver_address")
public class EntityDeliverAddressJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(length = 100)
    private String street;

    @Column(length = 20)
    private String number;

    @Column(length = 50)
    private String neighborhood;

    @Column(length = 50)
    private String city;

    @Column(nullable = false, length = 10)
    private String zipcode;

    @Column(length = 50)
    private String state;

    @Column(length = 50)
    private String reference;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private boolean main;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private EntityCustomerJpa customer;

    public EntityDeliverAddressJpa(String id, String street, String number, String neighborhood, String city, String zipcode, String state, String reference, boolean active, boolean main) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.zipcode = zipcode;
        this.state = state;
        this.reference = reference;
        this.active = active;
        this.main = main;
    }
}
