package LogIn;

public class User {

    private String username;
    private String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String newUsername){
        this.username = newUsername;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String newPassword){
        this.password = newPassword;
    }

    public boolean equal(User user0){
        return this.username == user0.username && this.password == user0.password;
    }

    public String toString(){
        return "Username: " + this.username + ", Password" + this.password;
    }
}
