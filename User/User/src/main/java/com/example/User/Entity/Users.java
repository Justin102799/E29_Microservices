package com.example.User.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;

//    @Column(nullable = false)
    private String firstName;
    private String lastName;

//    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private String role;

//    public Users(){
//        this.firstName = users.getFirstName();
//        this.lastName = users.getLastName();
//        this.email = users.getEmail();
//        this.password = users.getPassword();
//        this.role = users.getRole();
        //this.roles = users.getRoles();
}

