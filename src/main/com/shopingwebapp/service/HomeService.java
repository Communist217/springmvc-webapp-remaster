package main.com.shopingwebapp.service;

import main.com.entity.user.User;
import main.com.shopingwebapp.DAO.DAOHibernate;
import main.com.shopingwebapp.DAO.DAOHibernate_Util;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public interface HomeService {
    AbstractApplicationContext context = new ClassPathXmlApplicationContext("/resources/applicationContent.xml");
    DAOHibernate_Util daoHibernate_util = (DAOHibernate_Util) context.getBean("daoHibernateUtil");

    public boolean checkAccount(String Username, String Password);
}
