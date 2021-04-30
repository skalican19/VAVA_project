package controller.databases;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseAdmin<T> implements Serializable{
    public static Logger LOG = Logger.getLogger(DatabaseAdmin.class.getName());

    public void upload(String path,ArrayList <T> uploaded ){

        try
        {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(uploaded);
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            LOG.log(Level.SEVERE, "Couldnt load serialization files.");
        }
    }

    public ArrayList<T> download(String path) {
        ArrayList<T> Database;
        Database = new ArrayList<>();
        File inFile= new File(path);
        boolean newFile = false;

        try {
            newFile = inFile.createNewFile();
            LOG.log(Level.FINE, "Creating new database file.");
        } catch (IOException e) {
            e.printStackTrace();
            LOG.log(Level.SEVERE, "Could not create new database file.");
        }


        if (!newFile) {
            try {
                FileInputStream fis = new FileInputStream(inFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Database = (ArrayList<T>) ois.readObject();
                ois.close();
                fis.close();
            }
            catch (IOException ioe)
            {
                LOG.log(Level.WARNING, "Couldnt load Database from serialization files, creating new Database.");
                return Database;
            }
            catch (ClassNotFoundException c)
            {
                LOG.log(Level.SEVERE, "Class not found.");
                c.printStackTrace();
            }
        }

        return Database;
    }
}
