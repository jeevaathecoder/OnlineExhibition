package com.onlineexhibition.controllers;
import com.onlineexhibition.constants.OnlineExhibitionConstant;
import com.onlineexhibition.request.LoginRequest;
import com.onlineexhibition.request.SignupRequest;
import com.onlineexhibition.model.User;
import com.onlineexhibition.request.UserRequest;
import com.onlineexhibition.serviceImpl.UserServiceImpl;
import com.onlineexhibition.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {




    private OnlineExhibitionConstant onlineExhibitionConstant;

    @Autowired
    private UserServiceImpl userService;




    @PostMapping("/login")
    public String register(@Valid @RequestBody LoginRequest loginRequest){
//Get the password entered by the user
        String password = loginRequest.getPassword();
        //Encrypt
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(password);
        //Query the user's password
        String userpwd = userService.findByUserEmail(loginRequest.getEmail());
        //Pare the password
        if(userpwd.equals(password)){
            return "login success";
        }

        return "error";
    }



    @PostMapping("/createuser")
    public ResponseEntity<?> creatUser(@Valid @RequestBody SignupRequest request){
        User user	= userService.createUser(request);
        if(user==null) {
            return ResponseEntity.badRequest().body("emailId is already existed or User already existed");
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/authorize")
    public ResponseEntity<?> authorize(@Valid @RequestBody UserRequest request){
        String response = userService.authorize(request);
        if(response.equals(onlineExhibitionConstant.AUTHORIZED_USER)) {
            return ResponseEntity.ok(200);
        }
        return ResponseEntity.badRequest().body(response);
    }


}
