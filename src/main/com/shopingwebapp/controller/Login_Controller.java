package main.com.shopingwebapp.controller;

import main.com.entity.user.User;
import main.com.shopingwebapp.service.implementing.HomeServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller //This tells that it is a spring controller
public class Login_Controller {
    //Model and View push data to view
    @Autowired
    private HomeServiceImplement homeServiceImplement;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(Model model) {
        ModelAndView modelAndView = new ModelAndView("login");
        model.addAttribute("User", new User());
        return modelAndView;
    }

    @RequestMapping(value = "/main-page", method = RequestMethod.POST)
    public ModelAndView main_page(@RequestParam(value = "Username", required = true) String username, @RequestParam(value = "Password", required = true) String password , ModelMap modelMap) {
        ModelAndView modelAndView = null;
        System.out.println("Name is " + username);
        System.out.println("Password is " + password);
        if (homeServiceImplement.checkAccount(username, password)) {
            modelAndView = new ModelAndView("home");
            User getUser = homeServiceImplement.getUser();
            modelMap.addAttribute("username", username);
        }
        else {
            modelAndView = new ModelAndView("login");
        }
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
