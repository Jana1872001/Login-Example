package com.example.login_Example.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "App_users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false , length = 100)

    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)

    private String lastName;

    @Column(nullable = false, length = 100)

    private String login;

    @Column(nullable = false, length = 100)

    private String password;
}

