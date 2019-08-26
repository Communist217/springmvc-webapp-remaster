package main.com.shopingwebapp.controller;

import main.com.shopingwebapp.date_converter.Date_Convert;
import main.com.shopingwebapp.service.implementing.CartServiceImplement;
import main.com.shopingwebapp.service.implementing.HomeServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller (value = "CartController")
public class Cart_Controller {
    @Autowired
    private CartServiceImplement cartServiceImplement;

    @RequestMapping(value = "/abort-order", method = RequestMethod.POST)
    public void abort_order(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String UserID = request.getParameter("UserID");
        System.out.println("Remove preorder by " + UserID);
        cartServiceImplement.abort_order(Integer.valueOf(UserID));
        PrintWriter writer = response.getWriter();
        writer.write("Removed");
    }

    @RequestMapping(value = "/finalize_order", method = RequestMethod.POST)
    public void finalize_order(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] Product_list_id = request.getParameterValues("Product_List");
        String[] Product_list_price = request.getParameterValues("Product_List_Price");
        String[] Product_list_quantity = request.getParameterValues("Product_List_Quantity");
        int UserID = Integer.valueOf(request.getParameter("UserID"));
        String orderDate = request.getParameter("OrderDate");
        String requiredDate = request.getParameter("RequiredDate");
        String note = request.getParameter("Note");
        String comments = request.getParameter("Comment");
        String status = request.getParameter("Status");
        long payment = Long.valueOf(request.getParameter("Payment"));
        String paymentMethod = request.getParameter("Method");
        long gap = Date_Convert.Day_Gap(orderDate, requiredDate);
        System.out.println( gap + " days.");
        System.out.println(requiredDate);
        String noti = cartServiceImplement.order_finalize(Product_list_id, Product_list_price, Product_list_quantity, UserID, orderDate, requiredDate, note, comments, status, payment, paymentMethod, gap);
        System.out.println(noti);

    }
}
