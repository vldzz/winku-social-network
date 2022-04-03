package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private boolean rememberMe = false;
    private char genre;

    @Column(unique = true)
    private String token = UUID.randomUUID().toString();


    public User() {
    }

    public User(String value) {

    }

    public User(String name, String username, String password, String email, char genre) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.genre = genre;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", genre=" + genre +
                ", token='" + token + '\'' +
                '}';
    }
}
