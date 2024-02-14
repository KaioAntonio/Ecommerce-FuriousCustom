package com.FuriousCustom.domain.service;


import com.FuriousCustom.config.exception.RegraDeNegocioException;
import com.FuriousCustom.config.responses.ResultUtilSucess;
import com.FuriousCustom.domain.dto.user.LoginDTO;
import com.FuriousCustom.domain.entity.User;
import com.FuriousCustom.security.TokenService;
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

    public ResponseEntity<ResultUtilSucess> getResultUtilSucess(LoginDTO loginDTO) throws RegraDeNegocioException {
        try {
            UsernamePasswordAuthenticationToken userAuthDTO = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),
                    loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(userAuthDTO);
            Object principal = authentication.getPrincipal();
            User user = (User) principal;
            return new ResponseEntity<>(new ResultUtilSucess(tokenService.getToken(user)), HttpStatus.OK);
        }
        catch (Exception e){
            throw new RegraDeNegocioException("Erro ao realizar o login!");
        }
    }
}
