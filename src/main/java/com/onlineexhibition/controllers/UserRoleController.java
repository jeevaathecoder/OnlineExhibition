package com.onlineexhibition.controllers;

import com.onlineexhibition.model.UserRole;
import com.onlineexhibition.services.UserRoleService;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userRoles")
public class UserRoleController {

    @Autowired
    private UserRoleService useroleService;


    @PostMapping("/role")
    public ResponseEntity<UserRole> createRole(@NotNull String role) {
        UserRole user = useroleService.createRole(role);
        if(user==null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

}
