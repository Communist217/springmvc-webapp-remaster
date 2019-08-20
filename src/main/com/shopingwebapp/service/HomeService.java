package main.com.shopingwebapp.service;

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

import java.util.List;

public interface HomeService {
    final Resource r = new ClassPathResource("applicationContent.xml");
    final BeanFactory factory = new XmlBeanFactory(r);
    final DAOHibernate_Util daoHibernate_util = (DAOHibernate_Util) factory.getBean("daoHibernateUtil");


    public boolean checkAccount(String Username, String Password);
}
