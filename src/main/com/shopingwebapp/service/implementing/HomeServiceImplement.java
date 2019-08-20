package main.com.shopingwebapp.service.implementing;

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

    public User getUser() {
        return user;
    }
}
