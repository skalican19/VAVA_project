package controller.databases;


import controller.flowcontrol.IChangeScene;
import model.user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;


public class DatabaseManager {
    public static DatabaseManager instance = null;

    private ArrayList<User> usersDatabase = new DatabaseAdmin<User>().download("databases/user_database");


    public static DatabaseManager getInstance() throws IOException {
        if (instance == null)
            instance = new DatabaseManager();

        return instance;
    }

    public void updateUsers(User user){
        this.usersDatabase.add(user);
        new DatabaseAdmin<User>().upload("databases/user_database", usersDatabase);
    }

    public void updateUsers(ArrayList<User> users){
        new DatabaseAdmin<User>().upload("databases/user_database", users);
    }

    public void saveUsers(){
        new DatabaseAdmin<User>().upload("databases/user_database", usersDatabase);
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
