package com.kenzie.library;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class MainTest {
	public static final String TEST_MESSAGE_1 = "Buy groceries";
	public static final String TEST_MESSAGE_2 = "Pay bills";
	public static final String TEST_MESSAGE_3 = "Do homework";

	@Test
	void arrayListDefined() {
		assertTrue(Main.noteArray instanceof ArrayList, "noteArray defined");
	}

	@Test
	void canAddNote() {
		Main.addNote(TEST_MESSAGE_1,Priority.MEDIUM);

		assertEquals(1, Main.noteArray.size(), "noteArray has 1 element addded");
		assertTrue(Main.noteArray.get(0) instanceof StickyNote, "noteArray element is a StickyNote");
		assertEquals(TEST_MESSAGE_1, Main.noteArray.get(0).message, "notearray element has correct message");
		assertEquals(Priority.MEDIUM, Main.noteArray.get(0).getPriority(), "notearray element has correct priority");
		assertEquals("active", Main.noteArray.get(0).getStatus(), "notearray element has correct priority");
	}


	@Test
	void canPeelNote() {
		Main.addNote(TEST_MESSAGE_3,Priority.HIGH);

		Main.peelNote(0);
		assertTrue(Main.noteArray.get(0) instanceof StickyNote, "noteArray element is a StickyNote");
		assertEquals(TEST_MESSAGE_3, Main.noteArray.get(0).message, "notearray element has correct message");
		assertEquals(Priority.HIGH, Main.noteArray.get(0).priority, "notearray element has correct priority");
		assertEquals("inactive", Main.noteArray.get(0).getStatus(), "notearray element is inactive after being peeled");
	}

	@Test
	void canDisplayAllNotes() {
		Main.addNote(TEST_MESSAGE_1,Priority.MEDIUM);
		Main.addNote(TEST_MESSAGE_2,Priority.LOW);
		Main.addNote(TEST_MESSAGE_3,Priority.HIGH);

		ArrayList<String> displayedNotesList = Main.displayAllNotes();

		assertEquals(3, displayedNotesList.size());
		StickyNote firstNote = Main.noteArray.get(0);
		StickyNote secondNote = Main.noteArray.get(1);
		StickyNote thirdNote = Main.noteArray.get(2);
		String displayedFirstNote = displayedNotesList.get(0);
		String displayedSecondNote = displayedNotesList.get(1);
		String displayedThirdNote = displayedNotesList.get(2);

		assertTrue(displayedFirstNote.contains("******"));
		assertTrue(displayedFirstNote.contains(String.format("Priority:%s", firstNote.priority)));
		assertTrue(displayedFirstNote.contains(String.format("Status:%s", firstNote.status)));
		assertTrue(displayedFirstNote.contains(String.format("StickyNote:%s", firstNote.message)));

		assertTrue(displayedSecondNote.contains("******"));
		assertTrue(displayedSecondNote.contains(String.format("Priority:%s", secondNote.priority)));
		assertTrue(displayedSecondNote.contains(String.format("Status:%s", secondNote.status)));
		assertTrue(displayedSecondNote.contains(String.format("StickyNote:%s", secondNote.message)));

		assertTrue(displayedThirdNote.contains("******"));
		assertTrue(displayedThirdNote.contains(String.format("Priority:%s", thirdNote.priority)));
		assertTrue(displayedThirdNote.contains(String.format("Status:%s", thirdNote.status)));
		assertTrue(displayedThirdNote.contains(String.format("StickyNote:%s", thirdNote.message)));
	}

	@Test
	void canDisplayPriorityNotes() {
		Main.addNote(TEST_MESSAGE_1,Priority.MEDIUM);
		Main.addNote(TEST_MESSAGE_2,Priority.LOW);
		Main.addNote(TEST_MESSAGE_3,Priority.HIGH);

		ArrayList<String> displayedNotesList = Main.displayPriorityNotes(Priority.HIGH);

		assertEquals(1, displayedNotesList.size());
		StickyNote firstNote = Main.noteArray.get(2); // only the third message will display
		String displayedFirstNote = displayedNotesList.get(0);

		assertTrue(displayedFirstNote.contains("******"));
		assertTrue(displayedFirstNote.contains(String.format("Priority:%s", firstNote.priority)));
		assertTrue(displayedFirstNote.contains(String.format("Status:%s", firstNote.status)));
		assertTrue(displayedFirstNote.contains(String.format("StickyNote:%s", TEST_MESSAGE_3)));
	}

	@Test
	void canDisplayActiveNotesNotes() {
		Main.addNote(TEST_MESSAGE_1,Priority.MEDIUM);
		Main.addNote(TEST_MESSAGE_2,Priority.LOW);
		Main.addNote(TEST_MESSAGE_3,Priority.HIGH);

		Main.noteArray.get(1).setStatus("inactive");

		ArrayList<String> displayedNotesList = Main.displayActiveNotes();

		assertEquals(2, displayedNotesList.size());
		StickyNote firstNote = Main.noteArray.get(0);
		StickyNote secondNote = Main.noteArray.get(2); // only the first and third messages will display
		String displayedFirstNote = displayedNotesList.get(0);
		String displayedSecondNote = displayedNotesList.get(1);

		assertTrue(displayedFirstNote.contains("******"));
		assertTrue(displayedFirstNote.contains(String.format("Priority:%s", firstNote.priority)));
		assertTrue(displayedFirstNote.contains(String.format("Status:%s", firstNote.status)));
		assertTrue(displayedFirstNote.contains(String.format("StickyNote:%s", TEST_MESSAGE_1)));

		assertTrue(displayedSecondNote.contains("******"));
		assertTrue(displayedSecondNote.contains(String.format("Priority:%s", secondNote.priority)));
		assertTrue(displayedSecondNote.contains(String.format("Status:%s", secondNote.status)));
		assertTrue(displayedSecondNote.contains(String.format("StickyNote:%s", TEST_MESSAGE_3)));
	}

	@AfterEach
	void tearDown() {
		Main.noteArray.clear();
	}
}
