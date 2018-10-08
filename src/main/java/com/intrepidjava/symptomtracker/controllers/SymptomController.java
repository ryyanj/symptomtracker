package com.intrepidjava.symptomtracker.controllers;

import com.intrepidjava.symptomtracker.models.User;
import com.intrepidjava.symptomtracker.services.MongoUserDetailsService;
import com.intrepidjava.symptomtracker.services.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SymptomController {

    @Autowired
    private MongoUserDetailsService mongoUserDetailsService;

    @Autowired
    private SymptomService symptomService;



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
        symptomService.addSymptom(user,str.split("=")[1].trim());
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
        symptomService.removeSymptoms(user,symptoms);
        return new ModelAndView("redirect:/removesymptom");
    }

}
