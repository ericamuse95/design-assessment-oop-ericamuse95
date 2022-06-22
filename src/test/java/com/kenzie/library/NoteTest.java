package com.kenzie.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NoteTest {
	public static final String TEST_MESSAGE = "Reminder #1";

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
		note.setStatus("active");
		assertEquals("active", note.getStatus(), "Note is created with active status");
		note.setStatus("inactive");
		assertEquals("inactive", note.getStatus(), "Note is updated with inactive status");
	}


	@Test
	void canConvertNoteToString() {
		Note note = getDefaultNote();
		String noteText = note.toString();

		assertTrue(noteText.startsWith("******"), "Test: Stars included at head");
		assertTrue(noteText.endsWith("******\n"), "Test: Stars included at end");

		//split by newline
		String[] lineArray = noteText.split("\n");
		assertEquals(4,lineArray.length,"Test: Note has four lines");
		assertEquals(("Priority:"+note.getPriority()), lineArray[1], "Test: Priority first line");
		assertEquals(("Note:"+note.getMessage()), lineArray[2], "Test: Note message 2nd line");

	}


	@Test
	void canConvertStickyNoteToString() {
		StickyNote note = getDefaultStickyNote();
		String noteText = note.toString();

		assertTrue(noteText.startsWith("******"), "Test: Stars included at head");
		assertTrue(noteText.endsWith("******\n"), "Test: Stars included at end");

		//split by newline
		String[] lineArray = noteText.split("\n");
		assertEquals(5,lineArray.length,"Test: Sticky note has five lines");

		assertEquals(("Priority:"+note.getPriority()), lineArray[1], "Test: Priority first line");
		assertEquals(("Status:"+note.getStatus()), lineArray[2], "Test: Priority first line");
		assertEquals(("StickyNote:"+note.getMessage()), lineArray[3], "Test: Note message 2nd line");

	}

}
