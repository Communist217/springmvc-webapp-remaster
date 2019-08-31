package main.com.shopingwebapp.controller;

import main.com.shopingwebapp.date_converter.Date_Convert;
import main.com.shopingwebapp.service.CartService;
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
    private CartService cartService;

    @RequestMapping(value = "/abort-order", method = RequestMethod.POST)
    public void abort_order(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String UserID = request.getParameter("UserID");
        System.out.println("Remove preorder by " + UserID);
        cartService.abort_order(Integer.valueOf(UserID));
        PrintWriter writer = response.getWriter();
        writer.write("Removed");
    }

    @RequestMapping(value = "/finalize_order", method = RequestMethod.POST)
    public void finalize_order(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
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
        String notify = cartService.order_finalize(Product_list_id, Product_list_price, Product_list_quantity, UserID, orderDate, requiredDate, note, comments, status, payment, paymentMethod, gap);
        PrintWriter writer = response.getWriter();
        writer.write(notify);
    }

    @RequestMapping(value = "/change_quantity", method = RequestMethod.POST)
    public String change_quantity(HttpServletRequest request) {
        String ProductID = request.getParameter("ProductID");
        String UserID = request.getParameter("UserID");
        String quantity_adjust = request.getParameter("quantity_adjust");
        cartService.quantity_adjust(Integer.valueOf(ProductID), Integer.valueOf(UserID), Integer.valueOf(quantity_adjust));
        return "cart";
    }

    @RequestMapping(value = "/remove_product_by_option", method = RequestMethod.POST)
    public String remove_product_by_option(HttpServletRequest request) {
        String ProductID = request.getParameter("Pid");
        String UserID = request.getParameter("Uid");
        cartService.remove_product_by_option(Integer.valueOf(ProductID), Integer.valueOf(UserID));
        return "cart";
    }

}
