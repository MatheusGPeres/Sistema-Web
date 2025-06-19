package com.sistema.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sistema.web.model.User;
import com.sistema.web.repository.UserRepository;

@Service
@Primary
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    public User salvar(User usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        usuario.setRole("ROLE_USER");
        return repo.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User usuario = repo.findByUsername(username);
        
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return org.springframework.security.core.userdetails.User
            .withUsername(usuario.getUsername())
            .password(usuario.getSenha())
            .authorities(usuario.getRole())
            .build();


    }

    public User buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public User atualizar(User usuario) {
        User existente = repo.findById(usuario.getId()).orElse(null);
        if (existente != null) {
            existente.setUsername(usuario.getUsername());
            existente.setEmail(usuario.getEmail());
            if (!usuario.getSenha().isEmpty()) {
                existente.setSenha(encoder.encode(usuario.getSenha()));
            }
            return repo.save(existente);
        }
        return null;
    }
}
