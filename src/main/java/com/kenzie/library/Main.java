package com.kenzie.library;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    /*** noteArrayList declaration ***/
    public static ArrayList<StickyNote> noteArrayList = new ArrayList<>();

    static ArrayList<StickyNote> addNote(String message, Priority priority, ArrayList<StickyNote> noteArrayList) {
        StickyNote note = new StickyNote(message,priority);
        noteArrayList.add(note);
        return noteArrayList;
    }
    static ArrayList<StickyNote> peelNote(ArrayList<StickyNote> noteArrayList, int index){
        for (int i = 0; i < noteArrayList.size(); i++){
            if (i == index) {
                noteArrayList.get(i).setStatus("inactive");
            }
        }
        return noteArrayList;
    }
    static ArrayList<StickyNote> getActiveNotes(ArrayList<StickyNote> noteArrayList) {
        ArrayList<StickyNote> activeNoteArrayList = new ArrayList<>();
        for (int i = 0; i < noteArrayList.size(); i++) {
            if (noteArrayList.get(i).getStatus() == "active") {
                activeNoteArrayList.add(noteArrayList.get(i));
            }
        }
        return activeNoteArrayList;
    }
    public static ArrayList<StickyNote> getPriorityNotes(ArrayList<StickyNote> noteArrayList, Priority priority) {
        ArrayList<StickyNote> priorityList = new ArrayList<StickyNote>();
        for (StickyNote sticky : noteArrayList) {
            if (sticky.getPriority().equals(priority)) {
                priorityList.add(sticky);
            }
        }
        return priorityList;
    }


    /*** DO NOT CHANGE THE CODE IN THIS BLOCK ***/
    /*** You can use these constant values for test messages **/
    public static final String MESSAGE_1 = "Buy groceries";
    public static final String MESSAGE_2 = "Pay bills";
    public static final String MESSAGE_3 = "Do homework";
    /**********************************************/


    public static void main (String[] args) {
        //your code here
        noteArrayList =addNote(MESSAGE_1, Priority.HIGH, noteArrayList);
        noteArrayList =addNote(MESSAGE_2, Priority.MEDIUM, noteArrayList);
        noteArrayList =addNote(MESSAGE_3, Priority.LOW, noteArrayList);
        peelNote(noteArrayList,0);
        System.out.println(noteArrayList.toString());
        System.out.println(getActiveNotes(noteArrayList));
        System.out.println(getPriorityNotes(noteArrayList,Priority.HIGH));




    }

}
