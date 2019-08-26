package main.com.shopingwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "OrderManage_Controller")
public class OrderManage_Controller {
    @RequestMapping(value = "/order_management", method = RequestMethod.GET)
    public ModelAndView order_management() {
        ModelAndView modelAndView = new ModelAndView("order_management");
        return modelAndView;
    }
}
