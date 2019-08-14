package main.com.entity.reply;

import java.io.Serializable;

public class Reply implements Serializable {
    private int ReplyID;
    private int UserID;
    private int PostID;
    private String reply;

    public Reply(int userID, int PostID, String reply) {
        UserID = userID;
        this.PostID = PostID;
        this.reply = reply;
    }

    public Reply() { }

    public int getReplyID() {
        return ReplyID;
    }

    public void setReplyID(int replyID) {
        ReplyID = replyID;
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

    public void setPostID(int postid) {
        this.PostID = postid;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
