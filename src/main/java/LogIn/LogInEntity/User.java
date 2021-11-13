package LogIn.LogInEntity;

public class User {

    private final String username;
    private final String password;

    /**
     * Construct a user.
     *
     * @param username The name of a user,  it can never be changed.
     * @param password The password used to log in, it can never be changed.
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Construct a default user.
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
     * Get the password for the user.
     *
     * @return A string represent the password for the user.
     */
    public String getPassword(){
        return password;
    }

    /**
     * Decide if two users are same.
     *
     * @param other the another use used to compare.
     * @return true if their username and password are same.
     */
    public boolean equal(User other){
        return this.username.equals(other.username) && this.password.equals(other.password);
    }

    /**
     * Decide whether the password matches the username.
     * @param
     * @return
     */
    public  boolean match(String password){
        return this.password.equals(password);
    }

    @Override
    public String toString(){
        return "Username: " + this.username + ", Password" + this.password;
    }
}
