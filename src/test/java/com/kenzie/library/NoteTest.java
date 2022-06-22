package com.kenzie.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NoteTest {
	public static final String TEST_MESSAGE = "Reminder #1";
	public static final String TEST_MESSAGE_2 = "Reminder #2";

	public static Note getDefaultNote() {
		Note note = new Note(TEST_MESSAGE);
		return note;
	}

	public static Note getPriorityNote() {
		Note note = new Note(TEST_MESSAGE, Priority.HIGH);
		return note;
	}
	public static StickyNote getDefaultStickyNote() {
		StickyNote note = new StickyNote(TEST_MESSAGE);
		return note;
	}

	public static StickyNote getPriorityStickyNote() {
		StickyNote note = new StickyNote(TEST_MESSAGE, Priority.HIGH);
		return note;
	}

	public static void deactivateStickyNote(StickyNote note){
		note.setStatus("inactive");
	}

	public static void activateStickyNote(StickyNote note){
		note.setStatus("active");
	}

	@Test
	void enumPriorityDefined() {
		assertEquals("LOW", Priority.LOW.name(),"Enum Priority LOW value defined");
		assertEquals("MEDIUM", Priority.MEDIUM.name(),"Enum Priority MEDIUM value defined");
		assertEquals("HIGH", Priority.HIGH.name(),"Enum Priority HIGH value defined");
	}

	@Test
	void canCreateNote() {
		Note note = getDefaultNote();
		assertEquals(TEST_MESSAGE, note.message, "Note is created with message" );
		assertEquals(Priority.MEDIUM, note.priority, "Note is created with MEDIUM priority");
		Note highNote = getPriorityNote();
		assertEquals(TEST_MESSAGE, highNote.message, "Note is created with message" );
		assertEquals(Priority.HIGH, highNote.priority, "Note is created with HIGH priority");
	}

	@Test
	void canSetGetNotePriority() {
		Note note = getDefaultNote();
		note.setPriority(Priority.LOW);
		assertEquals(Priority.LOW, note.priority, "Note is created with LOW priority");
		assertEquals(Priority.LOW, note.getPriority(), "Note is created with LOW priority");
	}

	@Test
	void canUpdateNoteMessage() {
		Note note = getDefaultNote();
		note.setMessage(TEST_MESSAGE_2);
		assertEquals(TEST_MESSAGE_2, note.message, "Note message has been updated");
		assertEquals(TEST_MESSAGE_2, note.getMessage(), "New note message is returned by getter");
	}


	@Test
	void canCreateStickyNote() {
		StickyNote note = getDefaultStickyNote();
		assertTrue(note instanceof Note, "note is an instance of Note");
		assertEquals(TEST_MESSAGE, note.message, "Note is created with message" );
		assertEquals(Priority.MEDIUM, note.priority, "Note is created with MEDIUM priority");
		StickyNote highNote = getPriorityStickyNote();
		assertEquals(TEST_MESSAGE, highNote.message, "Note is created with message" );
		assertEquals(Priority.HIGH, highNote.priority, "Note is created with HIGH priority");
	}

	@Test
	void canSetGetNoteStatus() {
		StickyNote note = getDefaultStickyNote();
		activateStickyNote(note);
		assertEquals("active", note.status, "Note is created with active status");
		deactivateStickyNote(note);
		assertEquals("inactive", note.status, "Note is updated with inactive status");
		assertEquals("inactive", note.getStatus(), "Note is updated with inactive status");
		activateStickyNote(note);
		assertEquals("active", note.getStatus(), "Note is updated with active status");
	}

}
