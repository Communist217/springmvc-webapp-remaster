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
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

//This tells that it is a spring controller
@Controller (value = "LoginController")
public class Login_Register_Controller {
    //Model and View push data to view
    @Autowired
    private HomeServiceImplement homeServiceImplement;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView, Model model, HttpServletRequest httpServletRequest) {
        if (httpServletRequest.getSession().getAttribute("User") != null) {
            modelAndView = new ModelAndView("home");
        }
        else {
            model.addAttribute("User", new User());
            modelAndView = new ModelAndView("login");
        }
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

    @RequestMapping(value = "/create-new-account", method = RequestMethod.POST)
    public ModelAndView new_account(HttpServletRequest request) throws UnsupportedEncodingException {
        ModelAndView modelAndView = null;
        request.setCharacterEncoding("UTF-8");
        String new_username = request.getParameter("Create_Username");
        String new_password = request.getParameter("Create_Password");
        String confirm_password = request.getParameter("Confirm_Password");
        String fullname = request.getParameter("Create_Fullname");
        String address = request.getParameter("Create_Address");
        String phone = request.getParameter("Create_Phone");
        String gender = request.getParameter("Create_Gender");
        String birthdate = request.getParameter("Create_BirthDate");
        String email = request.getParameter("Create_Email");
        StringTokenizer checkout=new StringTokenizer(new_username);

        if (new_username.length() >= 5 && new_password.length() > 0 && confirm_password.equals(new_password)) {

            if (checkout.countTokens() == 1) {
                if (homeServiceImplement.create_NewAccount(new_username, new_password, fullname, address, phone, gender, birthdate, email)) {
                    System.out.println("You Account has been successfully created.");
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("User", homeServiceImplement.getUser());
                    modelAndView = new ModelAndView("home");
                }
                else {
                    System.out.println( new_username + " has already been used by another user, pls choose a new username.");
                    modelAndView = new ModelAndView("login");
                }
            }
            else {
                System.out.println("Username " + new_username + " cannot be created due to the space syntax and also your account need to contain at least more than 5 characters.");
                modelAndView = new ModelAndView("login");
            }
        }
        else {
            System.out.println("Please input all the textfield! :) ");
            modelAndView = new ModelAndView("login");
        }
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
