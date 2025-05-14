package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ContaController {
    @GetMapping("/")
    public String home(){
        return "home";
    }

    // @PostMapping("/gastos")
    // public String paginaGastos(Model model, ) {
    //     //TODO: process POST request
        
    //     return "gastos";
    // }
    
}
