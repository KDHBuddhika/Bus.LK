package com.example.Green_X.service;

import com.example.Green_X.dto.RegisterDto.UserRegisterDto;
import com.example.Green_X.dto.userLoginDto.UserLoginDto;

public interface UserService {

    Boolean userRegister(UserRegisterDto userRegisterDto);


    UserLoginDto userLogin(String email, String password);


}
