package controller.databases;


import model.user.User;

import java.io.IOException;
import java.util.ArrayList;


public class DatabaseManager {
    public static DatabaseManager instance = null;

    private ArrayList<User> usersDatabase = new DatabaseAdmin<User>().download("databases/user_database");

    private DatabaseManager() throws IOException {}

    public static DatabaseManager getInstance() throws IOException {
        if (instance == null)
            instance = new DatabaseManager();

        return instance;
    }

    public void updateUsers(User user){
        this.usersDatabase.add(user);
        new DatabaseAdmin<User>().upload("databases/user_database", usersDatabase);
    }

    public void updateRooms(ArrayList<User> users){
        new DatabaseAdmin<User>().upload("databases/user_database", users);
    }

    public ArrayList getUsersDatabase() {
        return usersDatabase;
    }

    public User getUser(String nameOrEmail){
        for (User user: usersDatabase) {
            if (user.getUserName().equals(nameOrEmail) || user.getEmail().equals(nameOrEmail)){
                return user;
            }
        }
        return null;
    }


}
