package main.com.shopingwebapp.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import main.com.entity.product.Product;
import main.com.shopingwebapp.service.ProductService;
import main.com.shopingwebapp.service.implementing.HomeServiceImplement;
import main.com.shopingwebapp.service.implementing.ProductServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@Controller(value = "ProductController")
public class Product_Controller {
    @Autowired
    private ProductServiceImplement productService;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String access_product(HttpServletRequest request, HttpServletResponse servletResponse) {
        System.out.println("PID is: "+ request.getParameter("ID"));
        String getID = request.getParameter("ID");
        int id = Integer.valueOf(getID);
        Product access_product = productService.getProduct(id);
        HashMap<String, Long> getRating = productService.Get_Overall_Rating_By_Type(id);
        Long getOverallRating = productService.Get_Overall_Rating(id);
        request.setAttribute("Product_Data", access_product);
        request.setAttribute("ProgressBar", getRating);
        request.setAttribute("RatingCount", getOverallRating);
        return "product";
    }

    @RequestMapping(value = "/post-user-review", method = RequestMethod.POST)
    public void post_user_review(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        int UserID = Integer.valueOf(request.getParameter("Uid"));
        int ProductID = Integer.valueOf(request.getParameter("Pid"));
        String ReviewCmt = request.getParameter("ReviewCmt");
        double rating = Double.valueOf(request.getParameter("RatingVal"));
        System.out.println(UserID + ProductID + ReviewCmt + rating);
        productService.Post_Review(UserID, ProductID, ReviewCmt, rating);
    }

    @RequestMapping(value = "/add-to-cart", method = RequestMethod.POST)
    public void add_to_cart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        //int UserID = Integer.valueOf(session.getAttribute("UserID").toString());
        int UserID = Integer.valueOf(request.getParameter("UserID"));
        int ProductID = Integer.valueOf(request.getParameter("ProductID"));
        Gson gson = new Gson();
        String status = productService.getStatus(ProductID, UserID);
        JsonElement element = gson.toJsonTree(status);
        writer.write(element.toString());
    }
}
