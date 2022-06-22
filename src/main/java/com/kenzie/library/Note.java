package com.kenzie.library;

import java.util.Objects;

//Define public Note class here
public class Note{
    protected String message;
    protected Priority priority;

    public Note(String message) {
        this.message = message;
        this.priority = priority.MEDIUM;
    }

    public Note(String message, Priority priority) {
        this.message = message;
        this.priority = priority;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString(){
        return "******\n" + "Priority:" + this.priority+"\n" + "Note:" + this.message + "\n" + "******\n";
    }

}

// StickyNote class
class StickyNote extends Note{
    private String status;
    public StickyNote(String message) {super(message);}
    public StickyNote(String message, Priority priority){super(message, priority);this.status="active";}

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString(){
        return "******\n" + "Priority:" + this.priority+"\n" +"Status:" + this.status + "\n"+ "StickyNote:" + this.message + "\n" + "******\n";
    }
}