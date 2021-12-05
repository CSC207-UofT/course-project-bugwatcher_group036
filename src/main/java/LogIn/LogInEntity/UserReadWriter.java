package LogIn.LogInEntity;

import java.io.*;

public class UserReadWriter implements ReadWriter {

    /**
     *
     * @param filePath The path of the file.
     * @param users The user to be saved.
     * @throws IOException The exception may happen.
     */
    @Override
    public void saveToFile(String filePath, Object users) throws IOException {

        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(users);
        output.close();
    }

    /**
     *
     * @param filePath The path of the file.
     * @return  The user list read from file.
     * @throws IOException The exception may happen.
     * @throws ClassNotFoundException The exception may happen.
     */
    @Override
    public UserList readFromFile(String filePath) throws IOException, ClassNotFoundException {

        InputStream file = new FileInputStream(filePath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // serialize the Map
        UserList users = (UserList) input.readObject();
        input.close();
        return users;
    }
}
