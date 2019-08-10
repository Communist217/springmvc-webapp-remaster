package com.shopingwebapp.controller;

import com.shopingwebapp.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller //This tells that it is a spring controller
public class HomeController {
    //Model and View push data to view

    @RequestMapping(value = "/main_page", method = RequestMethod.GET)
    public ModelAndView main_page() {
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

    @RequestMapping(value = "/service_page", method = RequestMethod.GET)
    public ModelAndView service_page() {
        ModelAndView modelAndView = new ModelAndView("service");
        return modelAndView;
    }
}
