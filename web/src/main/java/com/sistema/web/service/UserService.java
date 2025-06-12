package com.sistema.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.web.model.User;
import com.sistema.web.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void salvarUsuario(User user) {
        userRepository.save(user);
    }

    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }
}
