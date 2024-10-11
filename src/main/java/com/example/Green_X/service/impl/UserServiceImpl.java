package com.example.Green_X.service.impl;


import com.example.Green_X.dto.RegisterDto.UserRegisterDto;
import com.example.Green_X.dto.userLoginDto.UserLoginDto;
import com.example.Green_X.entity.User;
import com.example.Green_X.exception.NotFoundException;
import com.example.Green_X.repo.UserRepo;
import com.example.Green_X.service.UserService;
import com.example.Green_X.util.AesEncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService {

  @Autowired
  private UserRepo userRepo;

    @Autowired
    private AesEncryptionUtil aesEncryptionUtil;



    //------------------------------- User register-------------------------------------------------------------

    @Override
    public Boolean userRegister(UserRegisterDto userRegisterDto) {

        User existUser = userRepo.findByEmailEquals(userRegisterDto.getEmail());
        if (existUser != null) {
            throw new NotFoundException("User Already Exist");
        } else {
            try {
                User user = new User();

                // Generate a new secret key
                SecretKey secretKey = aesEncryptionUtil.generateSecretKey();

                // Ensure that password is provided
                String plainPassword = userRegisterDto.getPassword();
                if (plainPassword == null || plainPassword.isEmpty()) {
                    throw new IllegalArgumentException("Password cannot be null or empty");
                }

                // Encrypt the password
                String encryptedPassword = aesEncryptionUtil.encrypt(plainPassword, secretKey);

                // Store the secret key as a Base64 encoded string
                String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

                // Set user details
                user.setUserName(userRegisterDto.getFName() + " " + userRegisterDto.getSName());
                user.setPassword(encryptedPassword);
                user.setEmail(userRegisterDto.getEmail());
                user.setPhoneNumber(userRegisterDto.getPhoneNumber());
                user.setRole("ROLE_" + userRegisterDto.getRole());
                user.setSecretKey(encodedSecretKey);

                // Save user to the database
                userRepo.save(user);

                return true;
            } catch (Exception e) {
                throw new NotFoundException("Registration failed: " + e.getMessage());
            }
        }

    }



    //---------------------------user login----------------------------------------


    @Override
    public UserLoginDto userLogin(String email, String password) {

        try {
            // Retrieve the user based on the provided email
            Optional<User> userOptional = userRepo.findByEmail(email);

            if (userOptional.isPresent()) {
                User user = userOptional.get();

                try {
                    // Decode the stored secret key from Base64
                    byte[] decodedKey = Base64.getDecoder().decode(user.getSecretKey());
                    SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

                    // Decrypt the stored password
                    String decryptedPassword = aesEncryptionUtil.decrypt(user.getPassword(), originalKey);

                    // Check if the provided password matches the decrypted password
                    if (password.equals(decryptedPassword)) {
                        UserLoginDto userLoginDto = new UserLoginDto();
                        userLoginDto.setUserId(user.getUserId());
                        userLoginDto.setUserRole(user.getRole());

                        return userLoginDto;  // Return the login DTO
                    } else {
                        throw new NotFoundException("Invalid password");
                    }
                } catch (Exception e) {
                    System.out.println("Error occurred during decryption: " + e.getMessage());
                    throw new NotFoundException("Login failed: " + e.getMessage());
                }
            } else {
                throw new NotFoundException("User not found");
            }
        } catch (Exception e) {
            System.err.println("Error during user login: " + e.getMessage());
            throw new NotFoundException("Login failed: " + e.getMessage());
        }
    }




}
