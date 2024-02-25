package com.FuriousCustom.domain.service;

import com.FuriousCustom.config.exception.ServiceException;
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
        passwordEncoder(user);
        userRepository.save(user);
        UserDTO usuarioDTO = objectMapper.convertValue(user, UserDTO.class);
        usuarioDTO.setUserId(user.getUserId());
        return usuarioDTO;
    }

    private void passwordEncoder(User user) {
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
    }

    public PageDTO<UserDTO> findAll(Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);
        List<UserDTO> usuarioDTO = userPage.getContent().stream()
                .map(user -> objectMapper.convertValue(user, UserDTO.class))
                .collect(Collectors.toList());

        return new PageDTO<>(userPage.getTotalElements(),
                userPage.getTotalPages(),
                page,
                size,
                usuarioDTO);
    }

    public User findById(Integer id) throws ServiceException {
        return userRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Usuário não encontrado!"));
    }

    public UserDTO update(UserCreateDTO userCreateDTO, Integer id) throws ServiceException
    {
        User user = findById(id);
        user.setAge(userCreateDTO.getAge());
        user.setEmail(userCreateDTO.getEmail());
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        userRepository.save(user);
        return objectMapper.convertValue(user, UserDTO.class);
    }

    public void delete(Integer id) throws ServiceException {
        User user = findById(id);
        userRepository.delete(user);
    }


}
