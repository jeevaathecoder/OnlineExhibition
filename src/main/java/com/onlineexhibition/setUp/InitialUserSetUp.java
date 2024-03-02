package com.onlineexhibition.setUp;

import com.onlineexhibition.model.User;
import com.onlineexhibition.request.SignupRequest;
import com.onlineexhibition.serviceImpl.UserServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component

public class InitialUserSetUp {
    private final UserServiceImpl userService;

    public InitialUserSetUp(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void setupInitialUser() {
        // Check if the user doesn't already exist
            // Create a new user with the provided email and password
        SignupRequest req = SignupRequest.builder()
                .email("admin@gmail.com")
                .password("password")
                .firstname("collaborator")
                .lastname("larry")
                .mobile("9849838798")
                .user_type_id(1L)
                .build();
            // Save the user
            User user = userService.createUserNew(req);
            System.out.println(user);
    }
}
