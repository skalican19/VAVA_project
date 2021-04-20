package model.Days;

import java.time.LocalDate;

/***
 * Author Dušan
 */
public class Hobby extends Activity{
    private boolean outdoor;

    public Hobby(String name, Priority priority, String description, boolean outdoor) {
        super(name, priority, description);
        this.outdoor = outdoor;
    }
}
