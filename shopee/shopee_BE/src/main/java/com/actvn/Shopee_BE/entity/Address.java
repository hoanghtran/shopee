package com.actvn.Shopee_BE.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "addresses")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    String addressId;

    String buildingName;

    String city;

    String country;

    @Column(name = "pin_code")
    String pinCode;

    String state;

    String street;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    List<User> users = new ArrayList<>();

    public Address(String street, String state, String pinCode, String country, String city, String buildingName, String addressId) {
        this.street = street;
        this.state = state;
        this.pinCode = pinCode;
        this.country = country;
        this.city = city;
        this.buildingName = buildingName;
        this.addressId = addressId;
    }
}
