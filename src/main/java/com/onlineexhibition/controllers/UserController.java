package com.onlineexhibition.controllers;
import com.onlineexhibition.constants.OnlineExhibitionConstant;
import com.onlineexhibition.request.LoginRequest;
import com.onlineexhibition.request.SignupRequest;
import com.onlineexhibition.model.User;
import com.onlineexhibition.request.UserRequest;
import com.onlineexhibition.serviceImpl.UserServiceImpl;
import com.onlineexhibition.services.IUserService;
import com.onlineexhibition.utiils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {




    private OnlineExhibitionConstant onlineExhibitionConstant;

    @Autowired
    private UserServiceImpl userService;

@Autowired
 private  JwtUtils jwtutils;

    @PostMapping("/login")
    public ResponseEntity<?> register(@Valid @RequestBody LoginRequest loginRequest , HttpServletRequest request ){
        //Get the password entered by the user
        String password = loginRequest.getPassword();
        //Encrypt
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(password);
        //Query the user's password
        String userpwd = userService.findByUserEmail(loginRequest.getEmail());
        //Pare the password
        if(userpwd.equals(password)){
            String token = request.getHeader("token");
            if(jwtutils.verifierToken(token)){
                return  ResponseEntity.ok("login success");
            }
            //token失效处理 如果密码正确 就颁发新的token 否则不
        }

        return  ResponseEntity.badRequest().body("The password is incorrect ");
    }



    @PostMapping("/createuser")
    public ResponseEntity<?> creatUser(@Valid @RequestBody SignupRequest request){
        log.info(request.toString());
        User user	= userService.createUser(request);
        if(user==null) {
            return ResponseEntity.badRequest().body("emailId is already existed or User already existed");
        }
        String abc= user.getEmail()+user.getPassword();
        String token = jwtutils.createToken(abc);
        return ResponseEntity.ok(token);
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
