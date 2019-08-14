package main.com.shopingwebapp.controller;

import main.com.entity.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller //This tells that it is a spring controller
public class Login_Controller {
    //Model and View push data to view

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(Model model) {
        ModelAndView modelAndView = new ModelAndView("login");
        model.addAttribute("User", new User());
        return modelAndView;
    }

    @RequestMapping(value = "/main-page", method = RequestMethod.POST)
    public ModelAndView main_page(@ModelAttribute("User") User user, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView("home");
        System.out.println("Name is " + user.getUsername());
        System.out.println("Password is " + user.getPassword());
        modelMap.addAttribute("User", user);
        return modelAndView;
    }

    @RequestMapping(value = "/create-new-account", method = RequestMethod.GET)
    public ModelAndView new_account() {
        ModelAndView modelAndView = new ModelAndView("create_account");
        return modelAndView;
    }

    @RequestMapping(value = "/service-page", method = RequestMethod.GET)
    public ModelAndView service_page() {
        ModelAndView modelAndView = new ModelAndView("service");
        return modelAndView;
    }
}
