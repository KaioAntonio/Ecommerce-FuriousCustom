package com.FuriousCustom.security;


import com.FuriousCustom.domain.entity.User;
import com.FuriousCustom.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final UserService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userEntity = usuarioService.findByEmail(username);
        return userEntity
                .orElseThrow(() -> new UsernameNotFoundException("Usuário inválido!"));
    }
}