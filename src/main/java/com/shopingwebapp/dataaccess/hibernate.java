package com.shopingwebapp.dataaccess;

import com.entity.product.Product;
import com.entity.product.ProductType;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashMap;
import java.util.List;

public interface hibernate {
    //The SessionFactory is a factory of session and client of ConnectionProvider. It holds second level cache (optional) of data. The org.hibernate.SessionFactory interface provides factory method to get the object of Session.
    static final SessionFactory sessionFactory = new Configuration().configure("HibernateConfig/hibernate.cfg.xml").buildSessionFactory();

    //Store User review about product into db
    public void Review_Create(int UserID, int ProductID, String review_cmt, double rating);

    //Store User reply about product into db
    public void Reply_Create(int Uid, int PostID, String Reply);

    //Create Like by UserID and PostID
    public boolean Like_Create(int userID, int postID);

    //unlike by UserID and PostID
    public boolean Remove_Like(int userID, int postID);

    public long Get_Like_Count(int postID);

    //Get total like number
    public long Get_Total_Rating(int ProductID);

    //Get rating percentage for progress bar
    public HashMap<String,Long> Get_Total_Rating_by_Type(int ProductID);

    //Product access by user using id
    public Product product_access(int ProductID);

    //Get producttype by ID
    public ProductType get_ProductType_by_ID(int ID);

    public List<Product> get_Product_List(String type, String sort_option);

    public List<Product> Product_Search(String search);
}
