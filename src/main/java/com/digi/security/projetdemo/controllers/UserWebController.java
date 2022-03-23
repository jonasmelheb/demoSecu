package com.digi.security.projetdemo.controllers;

import com.digi.security.projetdemo.entities.User;
import com.digi.security.projetdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@Secured({"ROLE_ADMIN"})
public class UserWebController {
    @Autowired
    UserRepository userRepository;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());

        return "users";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping("/add")
    public String addUser(User user, Model model) {
        userRepository.save(user);
        return "home";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userRepository.findById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String editUser(User user, Model model) {
        userRepository.save(user);
        return "home";
    }

    @GetMapping("/delete/{id}")
    public String addUser(@PathVariable Long id, Model model) {
        userRepository.delete(userRepository.getById(id));
        return "home";
    }
}
