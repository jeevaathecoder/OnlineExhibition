package com.onlineexhibition.controllers;

import com.onlineexhibition.model.User;
import com.onlineexhibition.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/exhitors")
public class AdminController {

    @Autowired
    private UserServiceImpl userServiceimpl;

    @GetMapping("/bending")
    public List<User> getBendingExhibitors() {
        return userServiceimpl.getBendingExhibitors();
    }

    @GetMapping("/approved")
    public List<User> getApprovedExhibitors() {
        return userServiceimpl.getApprovedExhibitors();
    }
}