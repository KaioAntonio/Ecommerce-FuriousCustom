package com.FuriousCustom.controller;


import com.FuriousCustom.config.exception.RegraDeNegocioException;
import com.FuriousCustom.config.responses.ResultUtilSucess;
import com.FuriousCustom.domain.dto.user.LoginDTO;
import com.FuriousCustom.domain.dto.user.UserAuthResponse;
import com.FuriousCustom.domain.dto.user.UserCreateDTO;
import com.FuriousCustom.domain.dto.user.UserDTO;
import com.FuriousCustom.domain.service.AuthService;
import com.FuriousCustom.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @Operation(summary = "Efetuar o login do usuário", description = "Efetua o login do usuário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Efetua o login do usuário do banco"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/auth")
    public ResponseEntity<UserAuthResponse> auth(@RequestBody @Valid LoginDTO loginDTO) throws RegraDeNegocioException {
        return authService.getResultUtilSucess(loginDTO);

    }

    @Operation(summary = "Criar um usuário", description = "Criar um usuário")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Criar um usuário no banco de dados"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@RequestBody @Valid UserCreateDTO usuarioCreateDTO){
        return new ResponseEntity<>(userService.create(usuarioCreateDTO), HttpStatus.OK);
    }

}
