package com.shopingwebapp.service.implementing;

import com.shopingwebapp.service.HomeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServiceImplement implements HomeService {

    @Override
    public List<String> Load_Menu_Service() {
        List<String> service_menu = new ArrayList<>();
        service_menu.add("Phone");
        service_menu.add("Laptop");
        service_menu.add("Accessories");
        service_menu.add("Contact");
        return service_menu;
    }
}
