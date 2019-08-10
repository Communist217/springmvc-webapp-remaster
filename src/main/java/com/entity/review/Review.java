package com.entity.review;

import com.entity.like.Like;
import com.entity.reply.Reply;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

//POJO Review for table review in onlineshopdatabase
@javax.persistence.Table(name = "review")
public class Review implements Serializable {

    private int PostID;
    private int UserID;
    private int ProductID;
    private String Review_comment;
    private double Rating_value;
    private List<Reply> replies;
    private List<Like> likes;

    public Review() {}

    public Review(int memberID, int productID, String review_comment, double rating_value) {
        UserID = memberID;
        ProductID = productID;
        Review_comment = review_comment;
        Rating_value = rating_value;

    }

    public int getPostID() {
        return PostID;
    }

    public void setPostID(int postID) {
        PostID = postID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public double getRating_value() {
        return Rating_value;
    }

    public void setRating_value(double rating_value) {
        Rating_value = rating_value;
    }

    public String getReview_comment() {
        return Review_comment;
    }

    public void setReview_comment(String review_comment) {
        Review_comment = review_comment;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review")
    @Cascade(CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }
}
