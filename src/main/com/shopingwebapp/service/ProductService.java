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

    Product getProduct(int id);

    HashMap<String, Long> Get_Overall_Rating_By_Type(int Pid);

    Long Get_Overall_Rating(int Pid);

    void Post_Review(int userID, int productID, String reviewCmt, double rating);

    String getStatus(int ProductID, int UserID);

    void Reply_Create(int UserID, int PostID, String Reply);

    long Like_Create(int UserID, int PostID);

    long Unlike_Create(int UserID, int PostID);
}
