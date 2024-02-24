package com.FuriousCustom.domain.dto.user;

import lombok.Data;

@Data
public class UserAuthResponse extends UserDTO{

    private String token;

}
