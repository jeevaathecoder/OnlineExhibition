package com.onlineexhibition.model;

import jakarta.persistence.*;
import java.util.*;

import lombok.*;

@Data
@Builder
@Entity
@Table(name = "user")
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name="first_name")
    private String firstname;
    @Column(name="last_name")
    private String lastname;
    @Column(name="user_email",nullable = false)
    private String email;
    @Column(name="user_password")
    private String password;
    @Column(name = "Contact")
    private String mobile;

    @Column(name="user_status")
    private String status;
    @Column(name="user_type")
    private Long user_type_id;






}
