package controller.databases;


import java.io.IOException;
import java.util.ArrayList;


public class DatabaseManager {
    public static DatabaseManager instance = null;

    private DatabaseManager() throws IOException {}

    public static DatabaseManager getInstance() throws IOException {
        if (instance == null)
            instance = new DatabaseManager();

        return instance;
    }


}
