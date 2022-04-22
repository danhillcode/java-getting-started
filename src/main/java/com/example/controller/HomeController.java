package com.example.controller;

import com.example.dao.UserRepository;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController extends ApplicationController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/login")
    public String getLogin() {
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String postShow(Model model) {

        model.addAttribute("email", current_user().getEmail());
        model.addAttribute("password", current_user().getUserName());
        return "show";
    }

    @GetMapping("/users/sign_up")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/users/save")
    public String saveStudent(@ModelAttribute User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/";
    }
}
