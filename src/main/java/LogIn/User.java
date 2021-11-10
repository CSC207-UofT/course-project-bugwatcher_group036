package LogIn;

public class User {

    private String username;
    private String password;

    /**
     * Construct an User.
     *
     * @param username The name of a user, it can be any string.
     * @param password The password used to log in.
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Construct a customer.
     */
    public User(){
        this.username = "null";
        this.password = "12345";
    }

    /**
     * Get the user's name.
     *
     * @return The name of the user.
     */
    public String getUsername(){
        return username;
    }

    /**
     * Set up a username.
     *
     * @param newUsername The new name the user wants to set.
     */
    public void setUsername(String newUsername){
        this.username = newUsername;
    }

    /**
     * Get the password for the user.
     *
     * @return A string represent the password for the user.
     */
    public String getPassword(){
        return password;
    }

    /**
     * set up a password for the User.
     *
     * @param newPassword A string which is the password the user want ot use.
     */
    public void setPassword(String newPassword){
        this.password = newPassword;
    }

    /**
     * Decide if two users are same.
     *
     * @param user0 the another use used to compare.
     * @return true if their username and password are same.
     */
    public boolean equal(User user0){
        return this.username.equals(user0.username) && this.password.equals(user0.password);
    }

    @Override
    public String toString(){
        return "Username: " + this.username + ", Password" + this.password;
    }
}
