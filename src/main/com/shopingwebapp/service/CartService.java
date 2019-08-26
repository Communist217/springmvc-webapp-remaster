package main.com.shopingwebapp.service;

import main.com.shopingwebapp.DAO.DAOHibernate_Util;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public interface CartService {
    final Resource resource = new ClassPathResource("applicationContent.xml");
    BeanFactory factory = new XmlBeanFactory(resource);
    final DAOHibernate_Util daoHibernate_util = (DAOHibernate_Util) factory.getBean("daoHibernateUtil");

    public void abort_order(int UserID);

    public String order_finalize(String[] Product_list_id, String[] Product_list_price, String[] Product_list_quantity, int UserID, String orderDate, String requiredDate, String note, String comments, String status, long payment, String paymentMethod, long gap);

    public void quantity_adjust(int ProductID, int UserID, int New_Quantity);

    public void remove_product_by_option(int ProductID, int UserID);
}
