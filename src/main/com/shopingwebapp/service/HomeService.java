package main.com.shopingwebapp.service;

import main.com.entity.product.Product;
import main.com.entity.user.User;
import main.com.shopingwebapp.DAO.DAOHibernate;
import main.com.shopingwebapp.DAO.DAOHibernate_Util;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

public interface HomeService {
    final Resource resource = new ClassPathResource("applicationContent.xml");
    BeanFactory factory = new XmlBeanFactory(resource);
    final DAOHibernate_Util daoHibernate_util = (DAOHibernate_Util) factory.getBean("daoHibernateUtil");

    public boolean create_NewAccount(String Create_Username, String Create_Password, String Fullname, String Address, String Phone, String Gender, String BirthDate, String Email);

    public boolean change_password(String Username, String Email, String New_Password, String Confirm_Password);

    public boolean checkAccount(String Username, String Password);

    public User getUser();

    public List<Product> getList(String price_type, String sort_option);

    public List<Product> searchList(String search_word, int typeid);

}