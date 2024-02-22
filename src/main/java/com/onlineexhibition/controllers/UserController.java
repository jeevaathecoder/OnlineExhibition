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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {




    private OnlineExhibitionConstant onlineExhibitionConstant;

    @Autowired
    private UserServiceImpl userService;




    @PostMapping("/login")
    public String register(@Valid @RequestBody LoginRequest loginRequest){
        // we have implement such that it has to send token with it.
        return "We need to implement login functionality here.";
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
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }


}
