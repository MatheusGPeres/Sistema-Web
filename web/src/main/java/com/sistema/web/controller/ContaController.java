package com.sistema.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContaController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/gastos")
    public String gastos() {
        return "gastos";
    }
}
