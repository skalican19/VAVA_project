package model.Days;

import java.time.LocalDate;

public class Recreation extends Activity{
    private boolean outdoor;

    public Recreation(String name, Priority priority, String description, boolean outdoor) {
        super(name, priority, description);
        this.outdoor = outdoor;
    }
}
