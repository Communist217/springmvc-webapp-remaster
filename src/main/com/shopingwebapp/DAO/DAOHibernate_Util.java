package main.com.shopingwebapp.DAO;

import main.com.entity.product.Product;
import main.com.entity.product.ProductType;
import main.com.entity.reply.Reply;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository(value = "DAOHibernate")
public class DAOHibernate_Util implements DAOHibernate {
    @Autowired
    private HibernateTemplate template;

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public void Review_Create(int UserID, int ProductID, String review_cmt, double rating) {
        //Session interface provides methods to insert, update and delete the object. It also provides factory methods for Transaction, Query and Criteria.
        /*Session session = getSessionFactory().openSession();
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
        session.close();*/
    }

    @Override
    public void Reply_Create(int Uid, int PostID, String Reply) {
        /*Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();*/

        Reply reply = new Reply();
        //Transient Object, not determined by Hibernate
        reply.setUserID(Uid);
        reply.setPostID(PostID);
        reply.setReply(Reply);
        template.persist(reply);
        /*session.persist(reply);
           transaction.commit();*/

    }

    @Override
    public boolean Like_Create(int userID, int postID) {
        /*Session session = sessionFactory.openSession();
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
        session.close();*/

        return true;
    }

    @Override
    public boolean Remove_Like(int userID, int postID) {
        /*Session session = sessionFactory.openSession();
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


*/
        return true;
    }

    @Override
    public long Get_Like_Count(int postID) {
        //Session session = sessionFactory.openSession();

        long count = 0;
        /*try {
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
        session.close();*/

        return count;
    }

    @Override
    public long Get_Total_Rating(int ProductID) {
        //Session session = this.sessionFactory.openSession();

        long total = 0;
        /*Query query = session.createQuery("select count(rating_value) from Review where productID = :productID");
        query.setParameter("productID", ProductID);
        List<Long> count = query.getResultList();
        total += count.get(0);

        session.close();
*/
        return total;
    }

    @Override
    public HashMap<String, Long> Get_Total_Rating_by_Type(int ProductID) {
        //Session session = getSessionFactory().openSession();

        //Rating type for overall rating bar
        String[] type = {"Positive", "Mixed", "Negative"};
        HashMap<String,Long> avarage_rating_number = new LinkedHashMap<>();
        /*try {
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
        session.close();*/

        return avarage_rating_number;
    }

    @Override
    public Product product_access(int ProductID) {
        //Session session = sessionFactory.openSession();

        Product getProduct = new Product();//= session.get(Product.class, ProductID);
        //session.close();

        return getProduct;
    }

    @Override
    public ProductType get_ProductType_by_ID(int ID) {
        /*Session session = sessionFactory.openSession();

        ProductType productBytType = session.get(ProductType.class, ID);
        session.close();*/
        ProductType productBytType = new ProductType();
        productBytType = template.get(ProductType.class, ID);
        return productBytType;
    }

    @Override
    public List<Product> get_Product_List(String type, String sort_option) {
        //Session session = sessionFactory.openSession();

        List<Product> list = new ArrayList<Product>();
        List<Product> getProductout = new ArrayList<Product>();
        switch (type) {
            case "Low-Budget":
                ProductType LowBudget = template.load(ProductType.class, 1);
                list = LowBudget.getProductList();
                Hibernate.initialize(list);
                break;

            case "Mid-Range":
                ProductType MidRange = template.load(ProductType.class, 2);
                list = MidRange.getProductList();
                Hibernate.initialize(list);
                break;

            case "High-Range":
                ProductType HighRange = template.load(ProductType.class, 3);
                list = HighRange.getProductList();
                Hibernate.initialize(list);
                break;

            default:
                /*Query query = session.createQuery("from Product");
                list = query.getResultList();*/
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

        return list;
    }

    @Override
    public List<Product> Product_Search(String search) {
        //Session session = sessionFactory.openSession();
        List<Product> list = new ArrayList<Product>();
        /*Query query = session.createQuery("from Product where productname like  :productname ");
        query.setParameter("productname", "%"+ search + "%");

        list = query.getResultList();
        session.close();*/
        return list;
    }



}

class Main {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("/resources/applicationContent.xml");
        DAOHibernate daoHibernate_util = (DAOHibernate) context.getBean("daoHibernateUtil");
        //daoHibernate_util.Reply_Create(1, 1, "Kiss my ass");
        ProductType test = daoHibernate_util.get_ProductType_by_ID(1);
        System.out.println(test.getTypeID() + " " + test.getTypename());
        Iterator<Product> productIterator = test.getProductList().iterator();
        for (Iterator<Product> it = productIterator; it.hasNext(); ) {
            Product product = it.next();
            System.out.println(product.getProductname() + " " + product.getPrice());
        }
        /*HashMap<String, Long> productHashMap = daoHibernate_util.Get_Total_Rating_by_Type(1);
        for (Map.Entry product: productHashMap.entrySet()) {
            System.out.println(product.getKey() + " " + product.getValue());
        }*/
        Iterator<Product> productIterator2 = daoHibernate_util.get_Product_List("Mid-Range", "Price: High to Low").iterator();

        for (Iterator<Product> it = productIterator2; it.hasNext(); ) {
            Product product = it.next();
            System.out.println(product.getProductname() + " " + product.getPrice());
        }
    }
}
