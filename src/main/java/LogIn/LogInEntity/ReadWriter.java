package LogIn.LogInEntity;

import java.io.IOException;

public interface ReadWriter {

    void saveToFile(String filepath, Object o) throws IOException;

    UserList readFromFile(String filepath) throws IOException, ClassNotFoundException;
}
