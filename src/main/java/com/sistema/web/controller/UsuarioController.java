package com.sistema.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sistema.web.model.User;
import com.sistema.web.service.UserService;


@Controller
public class UsuarioController {

    @Autowired
    private UserService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "/dashboard";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("usuario", new User());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(@ModelAttribute User usuario) {
        usuarioService.salvar(usuario);
        return "redirect:/login";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        User usuario = usuarioService.buscarPorId(id);
        if (usuario == null) {
            return "redirect:/cadastro";
        }
        model.addAttribute("usuario", usuario);
        return "cadastro";
    }

    @PostMapping("/atualizar")
    public String atualizarUsuario(@ModelAttribute User usuario) {
        usuarioService.atualizar(usuario);
    return "redirect:/login";
}




}