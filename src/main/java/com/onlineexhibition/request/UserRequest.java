package com.onlineexhibition.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class UserRequest {

    @NotNull
    @Email
    private String emailId;

    @NotNull
    private Long userId;

    private Long admin_user_id;
}