package co.edu.udea.talentotech.programacion.intermedio.api_rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class FrontController {
    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("message", "Welcome to the Student Management System");
        return "home";
    }
}
