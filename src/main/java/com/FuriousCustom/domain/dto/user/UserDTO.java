package com.FuriousCustom.domain.dto.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"userId", "username", "email", "password", "phone", "age"})
public class UserDTO extends UserCreateDTO{

    private Integer userId;

}
