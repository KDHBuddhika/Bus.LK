package com.example.Green_X.dto.RegisterDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegisterDto {


    private String fName;
    private String sName;
    private String email;
    private String phoneNumber;
    private String password;
    private String role;

}
