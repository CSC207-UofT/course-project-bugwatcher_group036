package LogIn.LogInController;
import LogIn.LogInUseCase.UserMap;

import java.io.IOException;

public interface DataReader {
    /**
     * @param filepath location of ser file
     * @param o object to be serialized
     */
    void saveToFile(String filepath, Object o) throws IOException;

    /**
     * @param filepath location of ser file
     */
    UserMap readFromFile(String filepath) throws IOException, ClassNotFoundException;
}