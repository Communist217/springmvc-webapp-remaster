package main.com.entity.user;

public class User {
    private int UserID;
    private String Username;
    private String Password;
    private String Fullname;
    private String Address;
    private String Phonenumber;
    private String Gender;
    private String Birthdate;
    private String Email;

    public User(int userID, String username, String password, String fullname, String address, String phonenumber, String gender, String birthdate, String email) {
        UserID = userID;
        Username = username;
        Password = password;
        Fullname = fullname;
        Address = address;
        Phonenumber = phonenumber;
        Gender = gender;
        Birthdate = birthdate;
        Email = email;
    }

    public User() {}

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(String birthdate) {
        Birthdate = birthdate;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
