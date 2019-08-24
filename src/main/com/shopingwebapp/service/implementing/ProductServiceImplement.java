package main.com.shopingwebapp.service.implementing;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import main.com.entity.product.Product;
import main.com.shopingwebapp.service.ProductService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@Service (value = "ProductService")
public class ProductServiceImplement implements ProductService {

    @Override
    public Product getProduct(int id) {
        return daoHibernate_util.product_access(id);
    }

    @Override
    public HashMap<String,Long> Get_Overall_Rating_By_Type(int Pid) {
        return daoHibernate_util.Get_Total_Rating_by_Type(Pid);

    }

    @Override
    public Long Get_Overall_Rating(int Pid) {
        return daoHibernate_util.Get_Total_Rating(Pid);
    }

    @Override
    public void Post_Review(int userID, int productID, String reviewCmt, double rating) {
        daoHibernate_util.Review_Create(userID, productID, reviewCmt, rating);
    }

    @Override
    public String getStatus(int ProductID, int UserID) {
        int quantity = daoHibernate_util.ProductQuantity_In_Cart(ProductID, UserID);
        Product product_access = daoHibernate_util.product_access(ProductID);
        long stock = product_access.getStock();
        System.out.println("quantity is " + quantity);
        System.out.println("stock is " + stock);

        String status = "";
        if (quantity < stock && quantity < 10) {
            if (quantity == 0) {
                daoHibernate_util.Preorder_Product(ProductID, UserID);
                status += "Succeed";
                System.out.println(status);
            }
            else {
                daoHibernate_util.Add_More_To_Quantity(ProductID, UserID);
                status += "Succeed";
                System.out.println(status);
            }
        }
        else {
            status += "Fail";
            System.out.println(status);
        }
        return status;
    }
}

