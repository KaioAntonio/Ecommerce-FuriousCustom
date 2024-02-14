package com.FuriousCustom.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserCreateDTO {

    @NotBlank
    @Size(min = 4, max = 200)
    @Schema(description = "Nome do Usuário" , example = "usuario")
    private String username;

    @NotBlank
    @Email
    @Schema(description = "Email do Usuário" , example = "usuario@gmail.com")
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    @Schema(description = "Senha do Usuário" , example = "senha1234")
    private String password;

    @Pattern(regexp = "\\d{11}")
    @Schema(description = "Celular do Usuário", example = "11999999999")
    private String phone;

    @NotNull
    @Min(1) @Max(999)
    @Schema(description = "Idade do Usuário", example = "20")
    private Integer age;

}
