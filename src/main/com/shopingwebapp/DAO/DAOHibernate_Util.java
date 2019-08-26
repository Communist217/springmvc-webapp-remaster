package main.com.shopingwebapp.DAO;

import main.com.entity.like.Like;
import main.com.entity.order.Order;
import main.com.entity.order.Preorder;
import main.com.entity.product.Product;
import main.com.entity.product.ProductType;
import main.com.entity.reply.Reply;
import main.com.entity.review.Review;
import main.com.entity.user.User;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.sql.Timestamp;
import java.util.*;
@Repository(value = "daoHibernateUtil")
public class DAOHibernate_Util implements DAOHibernate {
    private HibernateTemplate template;
    private boolean existence;
    private int orderID;

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public boolean Create_New_Account(String Create_Username, String Create_Password, String Fullname, String Address, String Phone, String Gender, String BirthDate, String Email) {
        Session session = template.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        try {
            //Transient Object, not determined by Hibernate
            user.setUsername(Create_Username);
            user.setPassword(Create_Password);
            user.setFullname(Fullname);
            user.setBirthdate(BirthDate);
            user.setEmail(Email);
            user.setPhonenumber(Phone);
            user.setAddress(Address);
            user.setGender(Gender);

            session.persist(user);
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
    public User getAccount(String Username, String Password) {
        Session session = template.getSessionFactory().openSession();

        User user = new User();
        try {
            Query query = session.createQuery("from User where username =:username and password =:password");
            query.setParameter("username", Username);
            query.setParameter("password", Password);

            if (query.getResultList().size() == 0) {
                System.out.println(query.getResultList());
                existence = false;
            } else {
                existence = true;
                List<User> userList = query.getResultList();
                System.out.println(query.getResultList());
                user = userList.get(0);
            }
        } catch (Exception e) {
            System.out.println();
        }
        return user;
    }

    public boolean isExistence() {
        return existence;
    }

    @Override
    public void Review_Create(int UserID, int ProductID, String review_cmt, double rating) {
        Session session = template.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        //A new review is created
        try {
            Review user_review = new Review();
            //user_review is Transient
            user_review.setUserID(UserID);
            user_review.setProductID(ProductID);
            user_review.setReview_comment(review_cmt);
            user_review.setRating_value(rating);

            //user_review is persistence
            session.persist(user_review);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        session.close();
    }

    @Override
    public void Reply_Create(int Uid, int PostID, String Reply) {
        Session session = template.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Reply reply = new Reply();
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
        Session session = template.getSessionFactory().openSession();
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
        return true;
    }

    @Override
    public boolean Remove_Like(int userID, int postID) {
        Session session = template.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            //Create delete query
            Query query = session.createQuery("Delete from Like where userID = :u and postID =:p");
            //Set Parameter
            query.setParameter("p", postID);
            query.setParameter("u", userID);
            query.executeUpdate();
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
    public long Get_Like_Count(int postID) {
        //Session session = sessionFactory.openSession();

        long count = 0;
        Review review = template.get(Review.class, postID);

        Iterator<Like> allLikes = review.getLikes().iterator();
        while (allLikes.hasNext()) {
            Like like = allLikes.next();
            System.out.println("UID: " + like.getUserID() + " LikeID: " + like.getLikeID() + " PostID: " + like.getPostID());
            count++;
        }

        return count;
    }

    @Override
    public long Get_Total_Rating(int ProductID) {
        //Session session = this.sessionFactory.openSession();
        Session session = template.getSessionFactory().openSession();

        long total = 0;
        Query query = session.createQuery("select count(rating_value) from Review where productID = :productID");
        query.setParameter("productID", ProductID);
        template.getSessionFactory().openSession().close();
        List<Long> count = query.getResultList();
        total += count.get(0);
        session.close();
        return total;
    }

    @Override
    public HashMap<String, Long> Get_Total_Rating_by_Type(int ProductID) {
        //Session session = getSessionFactory().openSession();

        //Rating type for overall rating bar
        String[] type = {"Positive", "Mixed", "Negative"};
        HashMap<String, Long> avarage_rating_number = new LinkedHashMap<>();
        List<Long> positive_rating = (List<Long>) template.find("select count(rating_value) from Review where rating_value >= 3.5 and rating_value <= 5 and productID = " + ProductID);
        avarage_rating_number.put(type[0], positive_rating.get(0));
        List<Long> mixed_rating = (List<Long>) template.find("select count(rating_value) from Review where rating_value >= 2.5 and rating_value <= 3 and productID = " + ProductID);
        avarage_rating_number.put(type[1], mixed_rating.get(0));
        List<Long> negative_rating = (List<Long>) template.find("select count(rating_value) from Review where rating_value <= 2 and productID = " + ProductID);
        avarage_rating_number.put(type[2], mixed_rating.get(0));

        return avarage_rating_number;
    }

    @Override
    public Product product_access(int ProductID) {
        //Session session = sessionFactory.openSession();

        Product getProduct = new Product();//= session.get(Product.class, ProductID);
        getProduct = template.load(Product.class, ProductID);
        //session.close();

        return getProduct;
    }

    @Override
    public ProductType get_ProductType_by_ID(int ID) {
        ProductType productBytType = new ProductType();
        productBytType = template.load(ProductType.class, ID);
        return productBytType;
    }

    @Override
    public List<Product> get_Product_List(String type, String sort_option) {
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
                Query query = template.getSessionFactory().openSession().createQuery("from Product");
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

        return list;
    }

    @Override
    public List<Product> Product_Search(String search, int typeid) {
        Session session = template.getSessionFactory().openSession();

        List<Product> list = new ArrayList<Product>();
        Query query;
        if (typeid == 0) {
            query = session.createQuery("from Product where productname like :productname");
            query.setParameter("productname", "%" + search + "%");
        } else {
            query = session.createQuery("from Product where productname like :productname and typeID=:typeid");
            query.setParameter("productname", "%" + search + "%");
            query.setParameter("typeid", typeid);
        }

        list = query.getResultList();
        session.close();
        return list;
    }

    public int ProductQuantity_In_Cart(int ProductID, int UserID) {
        int quantity = 0;
        Query query = template.getSessionFactory().openSession().createQuery("select quantity from Preorder where productID=:ProductID and userID=:UserID");
        query.setParameter("ProductID", ProductID);
        query.setParameter("UserID", UserID);
        List<Integer> getquantity_in_cart= query.getResultList();
        if(getquantity_in_cart.size() != 0) {
            quantity += getquantity_in_cart.get(0);
        }
        return quantity;
    }

    public void Preorder_Product(int ProductID, int UserID) {
        Session session = template.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Preorder preorder = new Preorder();
            preorder.setProductID(ProductID);
            preorder.setUserID(UserID);
            preorder.setQuantity(1);
            session.persist(preorder);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Fail to preorder");
        }

        session.close();
    }

    public void Add_More_To_Quantity(int ProductID, int UserID) {
        Session session = template.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("update Preorder set quantity = quantity + 1 where productID=:ProductID and userID=:UserID");
            query.setParameter("ProductID", ProductID);
            query.setParameter("UserID", UserID);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        session.close();
    }

    @Override
    public void Remove_Preorder(int UserID) {
        Session session = template.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("delete from Preorder where userID=:UserID");
            query.setParameter("UserID", UserID);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        session.close();
    }
    public void InStock_Decrease(int ProductID, int Quantity) {
        Session session = template.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("update Product set stock = stock - :quantity where productID=:ProductID");
            query.setParameter("quantity", Quantity);
            query.setParameter("ProductID", ProductID);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        session.close();
    }

    @Override
        public boolean Complete_Order(String orderDate, String requiredDate, int UserID, String note, String comments, String status, long payment, String paymentMethod) {
        Session session = template.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Order newOrder = new Order(orderDate, null, requiredDate, UserID, note, comments, status, payment, paymentMethod);
            session.persist(newOrder);
            transaction.commit();
            System.out.println(" Order ID is " + newOrder.getOrderID());
            setOrderID(newOrder.getOrderID());
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }

        return true;
    }

    private void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getOrderID(){
        return orderID;
    }

    @Override
    public boolean Set_Order_Details(int ProductID, Long Price, int Quantity) {
        System.out.println(getOrderID());
        return true;
    }

}
class Main {
    public static void main(String[] args) {
        Resource r = new ClassPathResource("resources/applicationContent.xml");
        BeanFactory factory = new XmlBeanFactory(r);
        /*AbstractApplicationContext context = new Path("resources/applicationContent.xml");*/
        DAOHibernate_Util daoHibernate_util = (DAOHibernate_Util) factory.getBean("daoHibernateUtil");
        //daoHibernate_util.Reply_Create(1, 1, "Kiss my ass");
        ProductType test = daoHibernate_util.get_ProductType_by_ID(1);
        System.out.println(test.getTypeID() + " " + test.getTypename());

        User user = daoHibernate_util.getAccount("IAMGAY", "12345");
        Iterator<Product> productIterator = test.getProductList().iterator();
        for (Iterator<Product> it = productIterator; it.hasNext(); ) {
            Product product = it.next();
            System.out.println(product.getProductname() + " " + product.getPrice());
        }
        daoHibernate_util.Remove_Preorder(11);
        HashMap<String, Long> productHashMap = daoHibernate_util.Get_Total_Rating_by_Type(1);
        for (Map.Entry product: productHashMap.entrySet()) {
            System.out.println(product.getKey() + " " + product.getValue());
        }
//        boolean disLiked = daoHibernate_util.Remove_Like(1, 1 );
//        System.out.println(disLiked);
//        boolean isLiked = daoHibernate_util.Like_Create(1, 1);
//        System.out.println(isLiked);
        long Total_rating = daoHibernate_util.Get_Total_Rating(7);
        System.out.println(Total_rating);

        Iterator<Product> productIterator2 = daoHibernate_util.get_Product_List("Mid-Range", "Price: High to Low").iterator();
        for (Iterator<Product> it = productIterator2; it.hasNext(); ) {
            Product product = it.next();
            System.out.println(product.getProductname() + " " + product.getPrice());
        }
    }
}
