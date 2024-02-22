package com.onlineexhibition.services;


import com.onlineexhibition.model.User;
import com.onlineexhibition.request.SignupRequest;
import com.onlineexhibition.request.UserRequest;

import jakarta.validation.Valid;

public interface IUserService {



    User createUser(@Valid SignupRequest request);

    String authorize(@Valid UserRequest request);

    User findByUserId(Long id);

}

