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

@Service (value = "HomeService")
public class HomeServiceImplement implements HomeService {

    private User user;

    @Override
    public boolean create_NewAccount(String Create_Username, String Create_Password, String Fullname, String Address, String Phone, String Gender, String BirthDate, String Email) {

        if (daoHibernate_util.Create_New_Account(Create_Username, Create_Password, Fullname, Address, Phone, Gender, BirthDate, Email))
        {
            user = daoHibernate_util.getAccount(Create_Username, Create_Password);
            return true;
        }
        return false;
    }

    @Override
    public boolean change_password(String Username, String Email, String New_Password, String Confirm_Password) {
        return false;
    }

    @Override
    public boolean checkAccount(String Username, String Password) {
        user = daoHibernate_util.getAccount(Username, Password);
        if (daoHibernate_util.isExistence()) {
            return true;
        }
        return false;
    }


    public User getUser() {
        return user;
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
}
