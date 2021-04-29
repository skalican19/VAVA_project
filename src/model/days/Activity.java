package model.days;
import model.Translations;

import java.io.Serializable;
import java.time.LocalDate;

/***
 * Author Dušan
 */
public class Activity implements Serializable {
    private String name;
    private Priority priority;
    private String description;
    private String type;
    private LocalDate lastDone;      // when was the activity last done for the user

    public Activity(String name, Priority priority, String description) {
        this.name = name;
        this.priority = priority;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getLastDone() {
        return lastDone;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public String getType(){                                            // Dont delete it do stuff ok
        if (this instanceof Task) return Translations.Translate("task");
        if (this instanceof Hobby) return Translations.Translate("hobby");
        return Translations.Translate("activity");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLastDone(LocalDate lastDone) {
        this.lastDone = lastDone;
    }
}
