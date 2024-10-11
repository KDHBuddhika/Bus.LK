package com.example.Green_X.controller;


import com.example.Green_X.dto.RegisterDto.UserRegisterDto;
import com.example.Green_X.dto.userLoginDto.UserLoginDto;
import com.example.Green_X.dto.userLoginDto.UserLoginRequestDto;
import com.example.Green_X.service.UserService;
import com.example.Green_X.util.AesEncryptionUtil;
import com.example.Green_X.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.crypto.SecretKey;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AesEncryptionUtil aesEncryptionUtil;

    private SecretKey secretKey;


//----------------------------------------user register-----------------------------------------------
       @PostMapping({"/user-register"})
       public ResponseEntity<StandardResponse> UserRegister(@RequestBody UserRegisterDto userRegisterDto){

         Boolean status = userService.userRegister(userRegisterDto);



          ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                   new StandardResponse(200,"successfully",
                           status), HttpStatus.OK
           );

          return  response;
       }




       @GetMapping({"/notice"})
       public String getNotice(){
           return "hi";
       }




//       @PostConstruct
//       private String user (){
//
//           try {
//               secretKey = aesEncryptionUtil.generateSecretKey();
//               String originalPassword = "myPassword123";
//               String encryptedPassword = aesEncryptionUtil.encrypt(originalPassword, secretKey);
//               String decryptedPassword = aesEncryptionUtil.decrypt(encryptedPassword, secretKey);
//
//               System.out.println("Original Password: " + originalPassword);
//               System.out.println("Encrypted Password: " + encryptedPassword);
//               System.out.println("Decrypted Password: " + decryptedPassword);
//
//           } catch (Exception e) {
//               e.printStackTrace();
//           }
//           return null;
//       }


    //----------------------user login------------------------------------------

    @PostMapping("/user-login")
    private ResponseEntity<StandardResponse> Userlogin(@RequestBody UserLoginRequestDto userLoginRequestDto){

           UserLoginDto userLoginDto1 = userService.userLogin(userLoginRequestDto.getEmail(),userLoginRequestDto.getPassword());

        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"successfully",
                        userLoginDto1), HttpStatus.OK
        );

        return  response;

    }

  



}
