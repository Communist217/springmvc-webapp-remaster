package main.com.shopingwebapp.service.implementing;

import main.com.shopingwebapp.service.CartService;
import org.springframework.stereotype.Controller;

@Controller(value = "CartController")
public class CartServiceImplement implements CartService {

    @Override
    public void abort_order(int UserID) {
        daoHibernate_util.Remove_Preorder(UserID);
    }

    @Override
    public String order_finalize(String[] Product_list_id, String[] Product_list_price, String[] Product_list_quantity, int UserID, int OrderID, String orderDate, String requiredDate, String note, String comments, String status, long payment, String paymentMethod, long gap) {

        String order_init_status = "";
        if (gap >= 1) {
            if(daoHibernate_util.Complete_Order(OrderID, orderDate, requiredDate, UserID, note, comments, status, payment, paymentMethod)) {
                for(int i = 0; i < Product_list_id.length; i++) {
                    for (int j = i; j < (i+1); j++) {
                        if (daoHibernate_util.Set_Order_Details(OrderID, Integer.valueOf(Product_list_id[i]), Long.valueOf(Product_list_price[j]), Integer.valueOf(Product_list_quantity[j]))) {
                            daoHibernate_util.InStock_Decrease(Integer.valueOf(Product_list_id[i]), Integer.valueOf(Product_list_quantity[j]));
                            System.out.println("Successfully initiating order");;
                        }
                    }
                }
                daoHibernate_util.Remove_Preorder(UserID);
                order_init_status += "Succeed";
            }
            else {
                order_init_status += "Fail";
            }
        }
        else {
            order_init_status += "Fail";
        }
        return order_init_status;
    }
}
