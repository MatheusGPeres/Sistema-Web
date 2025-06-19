package com.sistema.web.controller;

import com.sistema.web.model.*;
import com.sistema.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProductService produtoService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("produtos", produtoService.listar());
        return "dashboard";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("produto", new Product());
        return "cadastroProduct";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Product produto) {
        produtoService.salvar(produto);
        return "redirect:/produtos/dashboard";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Product produto = produtoService.buscarPorId(id);
        if (produto == null) {
            return "redirect:/produtos/dashboard";
        }
        model.addAttribute("produto", produto);
        return "cadastroProduct";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return "redirect:/produtos/dashboard";
    }
}
