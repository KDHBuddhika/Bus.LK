package com.example.Green_X.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String userName;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;

    // Store the secret key as a Base64 encoded string
    private String secretKey;


}
