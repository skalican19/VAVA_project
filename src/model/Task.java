package model;

import java.time.LocalTime;

public class Task {
    private LocalTime start;
    private LocalTime end;
    private String name;


    public Task() {}

    public Task(LocalTime start, LocalTime end, String name) {
        this.start = start;
        this.end = end;
        this.name = name;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
