package com.altamash.springmvc.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {
    
    // method to show initial HTML form
    @RequestMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }
    // method to process the HTML form
    @RequestMapping("/processForm")
    public String processForm() {
        return "helloworld";
    }

    // method to read form data and add data to the model
    @RequestMapping("/processFormTwo")
    public String letsShoutDude(HttpServletRequest request, Model model) {
        String name = request.getParameter("studentName");
        name = name.toUpperCase();
        String result = "Yo! " + name;
        model.addAttribute("message", result);
        return "helloworld";
    }

    @RequestMapping("/processFormThree")
    public String letsShoutDude(@RequestParam("studentName") String name, Model model) {
        name = name.toUpperCase();
        String result = "Yo Baby! " + name;
        model.addAttribute("message", result);
        return "helloworld";
    }
}
