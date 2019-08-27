package main.com.shopingwebapp.service.implementing;

import main.com.shopingwebapp.date_converter.Date_Convert;
import main.com.shopingwebapp.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplement implements CartService {

    @Override
    public void abort_order(int UserID) {
        daoHibernate_util.Remove_Preorder(UserID);
    }

    @Override
    public String order_finalize(String[] Product_list_id, String[] Product_list_price, String[] Product_list_quantity, int UserID, String orderDate, String requiredDate, String note, String comments, String status, long payment, String paymentMethod, long gap) {

        String order_init_status = "";
        if (orderDate != null && gap >= 1) {
            if(daoHibernate_util.Complete_Order(orderDate, requiredDate, UserID, note, comments, status, payment, paymentMethod)) {
                for(int i = 0; i < Product_list_id.length; i++) {
                    for (int j = i; j < (i+1); j++) {
                        if (daoHibernate_util.Set_Order_Details(Integer.valueOf(Product_list_id[i]), Long.valueOf(Product_list_price[j]), Integer.valueOf(Product_list_quantity[j]))) {
                            daoHibernate_util.InStock_Decrease(Integer.valueOf(Product_list_id[i]), Integer.valueOf(Product_list_quantity[j]));
                        }
                    }
                }
                daoHibernate_util.Remove_Preorder(UserID);
                order_init_status += "Succeed initiating order";
            }
            else {
                order_init_status += "Fail to initiate order";
            }
        }
        else {
            order_init_status += "Fail to initiate order";
        }
        return order_init_status;
    }

    @Override
    public String quantity_adjust(int ProductID, int UserID, int New_Quantity) {
        if(New_Quantity <= 10) {
            daoHibernate_util.Quantity_Adjust(ProductID, UserID, New_Quantity);
            return "Accept";
        }
        return "False";
    }

    @Override
    public void remove_product_by_option(int ProductID, int UserID) {
        daoHibernate_util.Remove_Product_From_Preorder(ProductID, UserID);
    }


}
