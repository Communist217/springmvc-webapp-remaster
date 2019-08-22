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

import javax.servlet.http.HttpServletRequest;

//This tells that it is a spring controller
@Controller (value = "LoginController")
public class Login_Register_Controller {
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
    public ModelAndView main_page(@RequestParam(value = "Username", required = true) String username, @RequestParam(value = "Password", required = true) String password , ModelMap modelMap, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = null;
        System.out.println("Name is " + username);
        System.out.println("Password is " + password);
        if (homeServiceImplement.checkAccount(username, password)) {
                modelAndView = new ModelAndView("home");
                User getUser = homeServiceImplement.getUser();
                httpServletRequest.getSession().setAttribute("User", getUser);
        }
        else {
            modelAndView = new ModelAndView("login");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/main-page", method = RequestMethod.GET)
    public ModelAndView check(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = null;
        if (httpServletRequest.getSession().getAttribute("User") != null) {
            modelAndView = new ModelAndView("home");
        }
        else {
            modelAndView = new ModelAndView("home");
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

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView("login");
        httpServletRequest.getSession().invalidate();
        return modelAndView;
    }
}
