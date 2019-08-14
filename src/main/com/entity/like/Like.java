package main.com.entity.like;

import java.io.Serializable;

public class Like implements Serializable {
    private long LikeID;
    private int UserID;
    private int PostID;

    public Like(int userID, int postID) {
        UserID = userID;
        PostID = postID;
    }

    public Like() { }

    public long getLikeID() {
        return LikeID;
    }

    public void setLikeID(long likeID) {
        LikeID = likeID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getPostID() {
        return PostID;
    }

    public void setPostID(int postID) {
        PostID = postID;
    }
}
