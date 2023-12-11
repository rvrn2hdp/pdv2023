package com.analistas.pdv.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/** 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 * @author RVRN2
 * @version 1.0
 * 
 */

@Controller
public class LoginController {

    @GetMapping("/login")
    public String iniciarSesion(Model model) {

        model.addAttribute("titulo", "Iniciar Sesión");

        return "login";
    }
}
