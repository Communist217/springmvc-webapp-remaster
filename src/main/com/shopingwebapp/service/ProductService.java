package main.com.shopingwebapp.service;

import main.com.entity.product.Product;
import main.com.shopingwebapp.DAO.DAOHibernate_Util;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.HashMap;
import java.util.List;

public interface ProductService {
    final Resource resource = new ClassPathResource("applicationContent.xml");
    BeanFactory factory = new XmlBeanFactory(resource);
    final DAOHibernate_Util daoHibernate_util = (DAOHibernate_Util) factory.getBean("daoHibernateUtil");

    public Product getProduct(int id);

    public HashMap<String,Long> Get_Overall_Rating_By_Type(int Pid);

    public Long Get_Overall_Rating(int Pid);

    void Post_Review(int userID, int productID, String reviewCmt, double rating);

    public String getStatus(int ProductID, int UserID);
}
