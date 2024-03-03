package com.onlineexhibition.controllers;
import com.onlineexhibition.configs.JwtBlacklist;
import com.onlineexhibition.constants.OnlineExhibitionConstant;
import com.onlineexhibition.request.LoginRequest;
import com.onlineexhibition.request.SignupRequest;
import com.onlineexhibition.model.User;
import com.onlineexhibition.request.UserRequest;
import com.onlineexhibition.response.AuthenticationResponse;
import com.onlineexhibition.serviceImpl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private OnlineExhibitionConstant onlineExhibitionConstant;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtBlacklist jwtBlacklist;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody  @Valid LoginRequest loginRequest){
        return userService.login(loginRequest);
    }

    @GetMapping("/logout")
    public String getUserById(HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization");
        jwtBlacklist.addToBlacklist(token);
        return "out success";
    }



   @PostMapping("/createuser")
    public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest request){
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
