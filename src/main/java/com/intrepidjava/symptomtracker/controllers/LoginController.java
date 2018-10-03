package com.intrepidjava.symptomtracker.controllers;

import com.intrepidjava.symptomtracker.models.User;
import com.intrepidjava.symptomtracker.services.MongoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private MongoUserDetailsService mongoUserDetailsService;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = mongoUserDetailsService.findByUsername(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError());
            modelAndView.setViewName("register");
        } else {
            mongoUserDetailsService.registerUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");

        }
        return modelAndView;
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = mongoUserDetailsService.findByUsername(auth.getName());
        modelAndView.addObject("currentUser", user);
        modelAndView.addObject("fullName", "Welcome " + user.getEmail());
        modelAndView.addObject("symptoms",user.getSymptoms());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin RoleRepository");
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }

    @GetMapping({"/","/home"})
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @GetMapping("/addsymptom")
    public ModelAndView addSymptom() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = mongoUserDetailsService.findByUsername(auth.getName());
        modelAndView.addObject("symptoms",user.getSymptoms());
        return modelAndView;
    }

    @PostMapping("/addsymptom")
    public ModelAndView addSymptom(@RequestBody String str , Principal principal) {
        User user = mongoUserDetailsService.findByUsername(principal.getName());
        mongoUserDetailsService.addSymptom(user,str.split("=")[1].trim());
        return new ModelAndView("redirect:/addsymptom");
    }

    @GetMapping("/removesymptom")
    public ModelAndView removeSymptom() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = mongoUserDetailsService.findByUsername(auth.getName());
        modelAndView.addObject("symptoms",user.getSymptoms());
        return modelAndView;
    }

    @PostMapping("/removesymptom")
    public ModelAndView removeSymptom(@RequestBody String str , Principal principal) {
        User user = mongoUserDetailsService.findByUsername(principal.getName());
        String[] pairs = str.split("&");
        List<String> symptoms = new ArrayList<>();
        for(int i = 0; i < pairs.length; i++) {
            symptoms.add(pairs[i].split("=")[1].trim());
        }
        mongoUserDetailsService.removeSymptoms(user,symptoms);
        return new ModelAndView("redirect:/removesymptom");
    }


}
