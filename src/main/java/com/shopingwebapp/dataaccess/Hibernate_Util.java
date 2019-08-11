package com.shopingwebapp.dataaccess;

import com.entity.like.Like;
import com.entity.product.Product;
import com.entity.product.ProductType;
import com.entity.reply.Reply;
import com.entity.review.Review;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

public class Hibernate_Util implements hibernate {
    @Override
    public void Review_Create(int UserID, int ProductID, String review_cmt, double rating) {
        //Session interface provides methods to insert, update and delete the object. It also provides factory methods for Transaction, Query and Criteria.
        Session session = sessionFactory.openSession();
        //Start a transaction which may consists of one or more crude operations like INSERT,SELECT,DELETE
        Transaction transaction = session.beginTransaction();

        //A new review is created,
        Review user_review = new Review();
        try {
            //user_review is Transient
            user_review.setUserID(UserID);
            user_review.setProductID(ProductID);
            user_review.setReview_comment(review_cmt);
            user_review.setRating_value(rating);

            //user_review is persistence
            session.persist(user_review);
            //Committing all changes happened during a transaction so that database remains in consistent state after operation
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }

    @Override
    public void Reply_Create(int Uid, int PostID, String Reply) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Reply reply = new Reply();
        try {
            //Transient Object, not determined by Hibernate
            reply.setUserID(Uid);
            reply.setPostID(PostID);
            reply.setReply(Reply);
            session.persist(reply);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }

    @Override
    public boolean Like_Create(int userID, int postID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Like like_review = new Like();
        try {
            //Transient Object, not determined by Hibernate
            like_review.setUserID(userID);
            like_review.setPostID(postID);

            //Persistence Object, determined by Hibernate
            session.persist(like_review);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
        session.close();

        return true;
    }

    @Override
    public boolean Remove_Like(int userID, int postID) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            //Create delete query
            Query query = session.createQuery("DELETE from Like where postID = :p and userID = :u");

            //Set Parameter
            query.setParameter("p", postID);
            query.setParameter("u" , userID);

            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        }
        session.close();

        return true;

    }

    @Override
    public long Get_Like_Count(int postID) {
        Session session = sessionFactory.openSession();

        long count = 0;
        try {
            Review review = session.get(Review.class, postID);

            Iterator<Like> allLikes= review.getLikes().iterator();
            while (allLikes.hasNext()) {
                Like like = allLikes.next();
                //System.out.println("UID: " + like.getUserID() + " LikeID: " + like.getLikeID() + " PostID: " + like.getPostID());
                count++;
            }
        } catch (Exception e) {
            System.out.println("No like yet.");
        }
        session.close();

        return count;
    }

    @Override
    public long Get_Total_Rating(int ProductID) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        long total = 0;
        Query query = session.createQuery("select count(rating_value) from Review where productID = :productID");
        query.setParameter("productID", ProductID);
        List<Long> count = query.getResultList();
        total += count.get(0);

        session.close();

        return total;
    }

    @Override
    public HashMap<String, Long> Get_Total_Rating_by_Type(int ProductID) {
        Session session = sessionFactory.openSession();

        //Rating type for overall rating bar
        String[] type = {"Positive", "Mixed", "Negative"};
        HashMap<String,Long> avarage_rating_number = new LinkedHashMap<>();
        try {
            //Create select query
            Query query = session.createQuery("select count(rating_value) from Review where rating_value >= :minrate and rating_value <= :maxrate and productID = :productID");

            //Set Parameter
            query.setParameter("minrate", 3.5);
            query.setParameter("maxrate", 5.0);
            query.setParameter("productID", ProductID);
            //Initiate positive reviews counting number;
            List<Long> positive = query.getResultList();
            avarage_rating_number.put(type[0], positive.get(0));

            //Set Parameter
            query.setParameter("minrate", 2.5);
            query.setParameter("maxrate", 3.0);
            query.setParameter("productID", ProductID);
            //Initiate mixed reviews counting number;
            List<Long> mixed = query.getResultList();
            avarage_rating_number.put(type[1], mixed.get(0));

            //Set Parameter
            query.setParameter("minrate", 0.0);
            query.setParameter("maxrate", 2.0);
            query.setParameter("productID", ProductID);
            //Initiate negative reviews counting number;
            List<Long> negative = query.getResultList();
            avarage_rating_number.put(type[2], negative.get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();

        return avarage_rating_number;
    }

    @Override
    public Product product_access(int ProductID) {
        Session session = sessionFactory.openSession();

        Product getProduct = session.get(Product.class, ProductID);
        session.close();

        return getProduct;
    }

    @Override
    public ProductType get_ProductType_by_ID(int ID) {
        Session session = sessionFactory.openSession();

        ProductType productBytType = session.get(ProductType.class, ID);
        session.close();

        return productBytType;
    }

    @Override
    public List<Product> get_Product_List(String type, String sort_option) {
        Session session = sessionFactory.openSession();

        List<Product> list = new ArrayList<Product>();
        List<Product> getProductout = new ArrayList<Product>();
        switch (type) {
            case "Low-Budget":
                ProductType LowBudget = session.load(ProductType.class, 1);
                list = LowBudget.getProductList();
                Hibernate.initialize(list);
                break;

            case "Mid-Range":
                ProductType MidRange = session.load(ProductType.class, 2);
                list = MidRange.getProductList();
                Hibernate.initialize(list);
                break;

            case "High-Range":
                ProductType HighRange = session.load(ProductType.class, 3);
                list = HighRange.getProductList();
                Hibernate.initialize(list);
                break;

            default:
                Query query = session.createQuery("from Product");
                list = query.getResultList();
                break;
        }

        switch (sort_option) {
            case "Price: Low to High":
                list.sort(Comparator.comparing(Product::getPrice));
                break;
            case "Price: High to Low":
                list.sort(Comparator.comparing(Product::getPrice).reversed());
                break;
            case "Popularity":
                list.sort(Comparator.comparing(Product::getPopularity).reversed());
                break;
            default:
                break;
        }
        session.close();

        return list;
    }

    @Override
    public List<Product> Product_Search(String search) {
        Session session = sessionFactory.openSession();
        List<Product> list = new ArrayList<Product>();
        Query query = session.createQuery("from Product where productname like  :productname ");
        query.setParameter("productname", "%"+ search + "%");

        list = query.getResultList();
        session.close();
        return list;
    }
}

class Main {
    public static void main(String[] args) {
        hibernate hibernate = new Hibernate_Util();
        HashMap<String, Long> productHashMap = hibernate.Get_Total_Rating_by_Type(1);

    }
}
