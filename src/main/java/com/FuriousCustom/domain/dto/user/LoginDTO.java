package com.FuriousCustom.domain.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDTO {
    @NotNull
    private String email;
    @NotNull
    private String password;
}

