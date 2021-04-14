package controller.databases;


import java.io.IOException;
import java.util.ArrayList;


public class DatabaseManager {
    public static DatabaseManager instance = null;
//    private ArrayList<> usersDatabase = new DatabaseAdmin<>().download("kkti");

    private DatabaseManager() throws IOException {}

    public static DatabaseManager getInstance() throws IOException {
        if (instance == null)
            instance = new DatabaseManager();

        return instance;
    }

//    public void updateRooms(Room r){
//        this.rooms.add(r);
//        new DatabaseAdmin<Room>().upload("Databases\\rooms", rooms);
//    }
//
//    public void updateRooms(ArrayList<Room> r){
//        new DatabaseAdmin<Room>().upload("Databases\\rooms", r);
//    }

//
//    public ArrayList getUsersDatabase() {
//        return usersDatabase;
//    }


}
