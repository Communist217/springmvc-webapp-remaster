package main.com.shopingwebapp.service.implementing;

import main.com.entity.product.Product;
import main.com.entity.user.User;
import main.com.shopingwebapp.DAO.DAOHibernate_Util;
import main.com.shopingwebapp.service.HomeService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class HomeServiceImplement implements HomeService {

    private User user;

    @Override
    public boolean checkAccount(String Username, String Password) {
        user = daoHibernate_util.getAccount(Username, Password);
        if (daoHibernate_util.isExistence()) {
            return true;
        }
        return false;
    }

    @Override
    public List<Product> getList(String price_type, String sort_option) {
        List<Product> products = daoHibernate_util.get_Product_List(price_type, sort_option);
        return products;
    }

    @Override
    public List<Product> searchList (String search_word, int TypeID) {
        List<Product> search_product = daoHibernate_util.Product_Search(search_word, TypeID);
        return search_product;
    }

    public Product getProduct(int id) {
        Product product = daoHibernate_util.product_access(id);
        return product;
    }

    public HashMap<String,Long> get_Overall_Rating(int Pid) {
        HashMap<String,Long> hashMap = daoHibernate_util.Get_Total_Rating_by_Type(Pid);
        return hashMap;
    }

    public User getUser() {
        return user;
    }
}
