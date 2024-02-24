package com.FuriousCustom.domain.service;


import com.FuriousCustom.config.exception.RegraDeNegocioException;
import com.FuriousCustom.domain.dto.user.LoginDTO;
import com.FuriousCustom.domain.dto.user.UserAuthResponse;
import com.FuriousCustom.domain.entity.User;
import com.FuriousCustom.security.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final ObjectMapper objectMapper;

    public ResponseEntity<UserAuthResponse> getResultUtilSucess(LoginDTO loginDTO) throws RegraDeNegocioException {
        try {
            UsernamePasswordAuthenticationToken userAuthDTO = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),
                    loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(userAuthDTO);
            Object principal = authentication.getPrincipal();
            User user = (User) principal;
            UserAuthResponse userAuthResponse = objectMapper.convertValue(user, UserAuthResponse.class);
            userAuthResponse.setToken(tokenService.getToken(user));
            return new ResponseEntity<>(userAuthResponse, HttpStatus.OK);
        }
        catch (Exception e){
            throw new RegraDeNegocioException("Erro ao realizar o login!");
        }
    }
}
