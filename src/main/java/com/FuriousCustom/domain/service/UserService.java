package com.FuriousCustom.domain.service;

import com.FuriousCustom.domain.dto.PageDTO;
import com.FuriousCustom.domain.dto.user.UserCreateDTO;
import com.FuriousCustom.domain.dto.user.UserDTO;
import com.FuriousCustom.domain.entity.User;
import com.FuriousCustom.domain.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public UserDTO create(UserCreateDTO userCreateDTO) {
        User user = objectMapper.convertValue(userCreateDTO, User.class);
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        userRepository.save(user);
        UserDTO usuarioDTO = objectMapper.convertValue(user, UserDTO.class);
        usuarioDTO.setUserId(user.getUserId());
        return usuarioDTO;

    }

    public PageDTO<UserDTO> findAll(Integer pagina, Integer tamanho) {

        Pageable solicitacaoPagina = PageRequest.of(pagina, tamanho);
        Page<User> usuario = userRepository.findAll(solicitacaoPagina);
        List<UserDTO> usuarioDTO = usuario.getContent().stream()
                .map(x -> objectMapper.convertValue(x, UserDTO.class))
                .collect(Collectors.toList());

        return new PageDTO<>(usuario.getTotalElements(),
                usuario.getTotalPages(),
                pagina,
                tamanho,
                usuarioDTO);
    }


}
