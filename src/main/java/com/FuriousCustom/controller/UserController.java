package com.FuriousCustom.controller;


import com.FuriousCustom.config.exception.ServiceException;
import com.FuriousCustom.domain.dto.PageDTO;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @Operation(summary = "Logs the user in", description = "Logs the user in")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Logs the user in"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "500", description = "An exception was generated")
            }
    )
    @PostMapping("/auth")
    public ResponseEntity<UserAuthResponse> auth(@RequestBody @Valid LoginDTO loginDTO) throws ServiceException {
        return authService.getResultUtilSucess(loginDTO);

    }

    @Operation(summary = "Create a User", description = "Create a User")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Create a User"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "500", description = "An exception was generated")
            }
    )
    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@RequestBody @Valid UserCreateDTO usuarioCreateDTO){
        return new ResponseEntity<>(userService.create(usuarioCreateDTO), HttpStatus.OK);
    }

    @Operation(summary = "Find All Users", description = "Find All Users")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Find All Users"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "500", description = "An exception was generated")
            }
    )
    @GetMapping("/findAll")
    public ResponseEntity<PageDTO<UserDTO>> findAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer size
    ){
        return new ResponseEntity<>(userService.findAll(page, size), HttpStatus.OK);
    }

    @Operation(summary = "Update a User", description = "Update a User")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Update a User"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "500", description = "An exception was generated")
            }
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UserCreateDTO usuarioCreateDTO,
                                          @PathVariable("id") Integer id) throws ServiceException {
        return new ResponseEntity<>(userService.update(usuarioCreateDTO, id), HttpStatus.OK);
    }

    @Operation(summary = "Delete a User", description = "Delete a User")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Delete a User"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "500", description = "An exception was generated")
            }
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ServiceException {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
