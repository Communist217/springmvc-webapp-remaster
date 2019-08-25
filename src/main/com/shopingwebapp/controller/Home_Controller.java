package main.com.shopingwebapp.controller;

import com.google.gson.Gson;
import main.com.entity.product.Product;
import main.com.shopingwebapp.service.implementing.HomeServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller (value = "HomeController")
public class Home_Controller {
    @Autowired
    private HomeServiceImplement homeServiceImplement;

    @RequestMapping (value = "/get_product", method = RequestMethod.GET)
    public void get_product(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String price_type = request.getParameter("Type");
        String sort_option = request.getParameter("Sort_option");
        System.out.println(price_type);
        System.out.println(sort_option);
        List<Product> productListbyType = new ArrayList<Product>();
        productListbyType = homeServiceImplement.getList(price_type, sort_option);
        response.setContentType("application/json");
        new Gson().toJson(productListbyType, response.getWriter());
    }

    @RequestMapping (value = "/cart", method = RequestMethod.GET)
    public String cart_access() {
        return "cart";
    }

    @RequestMapping (value = "/cart", method = RequestMethod.POST)
    public String get_back_to_cart() {
        return "cart";
    }

    @RequestMapping (value = "get_product_search",  method = RequestMethod.GET)
    public void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String search = request.getParameter("Search_result");
        int TypeID = Integer.valueOf(request.getParameter("Typeid"));
        System.out.println(search + " " + TypeID);
        List<Product> list = homeServiceImplement.searchList(search, TypeID);
        response.setContentType("application/json");
        new Gson().toJson(list, response.getWriter());
    }
}
