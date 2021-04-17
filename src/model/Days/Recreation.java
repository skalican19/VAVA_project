package model.Days;

import java.time.LocalDate;

public class Recreation extends Activity{
    private boolean outdoor;
    private LocalDate lastDone;

    public Recreation(String name, Priority priority, String description, boolean outdoor) {
        super(name, priority, description);
        this.outdoor = outdoor;
    }
}
