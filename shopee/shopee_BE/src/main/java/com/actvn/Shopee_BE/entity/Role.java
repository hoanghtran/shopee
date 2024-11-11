package com.actvn.Shopee_BE.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "roles")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_id")
    String roleId;

    @ToString.Exclude
    @Enumerated
    @Column(name = "role_name")
    AppRole roleName;

    public Role(AppRole roleName) {
        this.roleName = roleName;
    }
}
