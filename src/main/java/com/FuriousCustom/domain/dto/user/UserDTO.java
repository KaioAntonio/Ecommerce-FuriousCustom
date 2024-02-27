package com.FuriousCustom.domain.dto.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonPropertyOrder({"userId", "username", "email", "password", "phone", "age"})
public class UserDTO extends UserCreateDTO{

    private Integer userId;

}
