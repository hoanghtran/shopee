package com.act.vn.shopeeApplication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(unique = true)
    @NotBlank
    @Size(min = 5, message = "Name must be least 5 char")
    String name;

    @Column(name = "Create_at")
    final LocalDateTime createAt = LocalDateTime.now();
}
