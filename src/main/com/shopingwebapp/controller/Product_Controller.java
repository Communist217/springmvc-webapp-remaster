package main.com.shopingwebapp.controller;

import main.com.entity.product.Product;
import main.com.shopingwebapp.service.implementing.HomeServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller(value = "TransactionController")
public class Product_Controller {
    @Autowired
    private HomeServiceImplement homeServiceImplement;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String access_product(HttpServletRequest request, HttpServletResponse servletResponse) {
        System.out.println("PID is: "+ request.getParameter("ID"));
        String getID = request.getParameter("ID");
        int id = Integer.valueOf(getID);
        Product access_product = homeServiceImplement.getProduct(id);
        HashMap<String, Long> getRating = homeServiceImplement.get_Overall_Rating(id);
        request.setAttribute("Product_Data", access_product);
        request.setAttribute("ProgressBar", getRating);
        //request.setAttribute("RatingCount", getRating);
        return "product";
    }
}
