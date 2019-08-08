package com.shopingwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller //This tells that it is a spring controller
public class HomeController {

    @RequestMapping(value = "/main_page", method = RequestMethod.GET)
    //Model and View push data to view
    public ModelAndView main_page() {
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }
}
