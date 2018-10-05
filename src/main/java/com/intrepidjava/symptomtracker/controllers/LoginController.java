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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            symptoms.add(pairs[i].split("=")[1].trim().replaceAll("\\+"," "));
        }
        mongoUserDetailsService.removeSymptoms(user,symptoms);
        return new ModelAndView("redirect:/removesymptom");
    }

    @GetMapping("/recordsymptom")
    public ModelAndView recordSymptom(@RequestParam String param , Principal principal) {
        User user = mongoUserDetailsService.findByUsername(principal.getName());
        String[] params = param.split(",");
        List<String> symptoms = new ArrayList<>();
        for(int i = 0; i < params.length; i++) {
            symptoms.add(params[i].trim());
        }
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("symptoms",symptoms);
        modelAndView.setViewName("recordsymptom");
        return modelAndView;
    }

    @PostMapping("/recordsymptom")
    public ModelAndView recordSymptomPost(@RequestBody String str , Principal principal) {
        User user = mongoUserDetailsService.findByUsername(principal.getName());
        String[] pairs = str.split("&");
        Map<String,String> map = new HashMap<>();

        for(int i = 0; i < pairs.length; i++) {
            String[] pair = pairs[i].split("=");
            map.put(cleanString(pair[0]),cleanString(pair[1]));
        }
        mongoUserDetailsService.addEvent(user,map);
        return new ModelAndView("redirect:/dashboard");
    }

    private String cleanString(String str) {
        return str.trim().replaceAll("\\+"," ");
    }

    //lsof -i tcp:8080
    //kill -9 pid




}
