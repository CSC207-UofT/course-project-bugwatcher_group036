package LogIn.LogInEntity;

import java.io.IOException;

public interface ReadWriter {

    /**
     *
     * @param filepath The path of file.
     * @param o The username and the password to save.
     * @throws IOException The exception may happen.
     */
    void saveToFile(String filepath, Object o) throws IOException;

    /**
     *
     * @param filepath The path of file.
     * @return The list of users.
     * @throws IOException The exception may happen.
     * @throws ClassNotFoundException The exception may happen.
     */
    UserList readFromFile(String filepath) throws IOException, ClassNotFoundException;
}
