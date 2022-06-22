package com.kenzie.library;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
	public static final String TEST_MESSAGE_1 = "Buy groceries";
	public static final String TEST_MESSAGE_2 = "Pay bills";
	public static final String TEST_MESSAGE_3 = "Do homework";


	@Test
	void arrayListDefined() {
		assertTrue(Main.noteArrayList instanceof ArrayList, "noteArrayList defined");
	}

	@Test
	void canAddNote() {
		Main.noteArrayList.clear();
		ArrayList<StickyNote> arrayList1 = Main.addNote(TEST_MESSAGE_1,Priority.MEDIUM,Main.noteArrayList);

		assertEquals(1, arrayList1.size(), "noteArray has 1 element addded");
		assertTrue(arrayList1.get(0) instanceof StickyNote, "Test: noteArray element is a StickyNote");
		assertEquals(TEST_MESSAGE_1, arrayList1.get(0).message, "Test: notearray element has correct message");
		assertEquals(Priority.MEDIUM, arrayList1.get(0).getPriority(), "notearray element has correct priority");
		assertEquals("active", arrayList1.get(0).getStatus(), "notearray element has correct priority");
	}


	@Test
	void canPeelNote() {
		Main.noteArrayList.clear();
		ArrayList<StickyNote> arrayList1 = Main.addNote(TEST_MESSAGE_2,Priority.MEDIUM,Main.noteArrayList);

		arrayList1 = Main.peelNote(arrayList1, 0);
		assertTrue(arrayList1.get(0) instanceof StickyNote, "noteArray element is a StickyNote");
		assertEquals(TEST_MESSAGE_2, arrayList1.get(0).message, "notearray element has correct message");
		assertEquals(Priority.MEDIUM, arrayList1.get(0).priority, "notearray element has correct priority");
		assertEquals("inactive", arrayList1.get(0).getStatus(), "notearray element has correct priority");
	}

	@Test
	void canGetActiveNotes() {
		Main.noteArrayList.clear();
		Main.noteArrayList = Main.addNote(TEST_MESSAGE_1,Priority.LOW,Main.noteArrayList);
		Main.noteArrayList = Main.addNote(TEST_MESSAGE_2,Priority.MEDIUM,Main.noteArrayList);
		Main.noteArrayList = Main.addNote(TEST_MESSAGE_3,Priority.HIGH,Main.noteArrayList);

		Main.noteArrayList = Main.peelNote(Main.noteArrayList,0);

		ArrayList<StickyNote> resultArrayList = Main.getActiveNotes(Main.noteArrayList);
		assertEquals(2,resultArrayList.size(),"Test: Only two elements returned");
		assertEquals("active",resultArrayList.get(0).getStatus(), "Test: first result is active");
		assertEquals("active",resultArrayList.get(1).getStatus(), "Test: second result is active");
	}

	@Test
	void canGetPriorityNotes() {
		Main.noteArrayList.clear();
		Main.noteArrayList = Main.addNote(TEST_MESSAGE_1,Priority.LOW,Main.noteArrayList);
		Main.noteArrayList = Main.addNote(TEST_MESSAGE_2,Priority.MEDIUM,Main.noteArrayList);
		Main.noteArrayList = Main.addNote(TEST_MESSAGE_3,Priority.HIGH,Main.noteArrayList);

		ArrayList<StickyNote> resultArrayList = Main.getPriorityNotes(Main.noteArrayList,Priority.HIGH);
		assertEquals(1,resultArrayList.size(),"Test: Only one elements returned");
		assertEquals(Priority.HIGH,resultArrayList.get(0).getPriority(), "Test: result is Priority.HIGH");

		resultArrayList = Main.getPriorityNotes(Main.noteArrayList,Priority.LOW);
		assertEquals(1,resultArrayList.size(),"Test: Only one element returned");
		assertEquals(Priority.LOW,resultArrayList.get(0).getPriority(), "Test: result is Priority.LOW");
	}

	@Test
	void canGetActiveNotes_NoActiveFound() {
		Main.noteArrayList.clear();
		Main.noteArrayList = Main.addNote(TEST_MESSAGE_1,Priority.LOW,Main.noteArrayList);
		Main.noteArrayList = Main.addNote(TEST_MESSAGE_2,Priority.MEDIUM,Main.noteArrayList);

		Main.noteArrayList = Main.peelNote(Main.noteArrayList,0);
		Main.noteArrayList = Main.peelNote(Main.noteArrayList,1);
		ArrayList<StickyNote> resultArrayList = Main.getActiveNotes(Main.noteArrayList);
		assertEquals(0,resultArrayList.size(),"Test: No elements returned");

	}

	@Test
	void canGetActiveNotes_EmptyList() {
		Main.noteArrayList.clear();

		ArrayList<StickyNote> resultArrayList = Main.getActiveNotes(Main.noteArrayList);
		assertEquals(0,resultArrayList.size(),"Test: No elements returned");

	}


	@Test
	void canGetPriorityNotes_NoPriorityFound() {
		Main.noteArrayList.clear();
		Main.noteArrayList = Main.addNote(TEST_MESSAGE_1,Priority.LOW,Main.noteArrayList);
		Main.noteArrayList = Main.addNote(TEST_MESSAGE_2,Priority.MEDIUM,Main.noteArrayList);


		ArrayList<StickyNote> resultArrayList = Main.getPriorityNotes(Main.noteArrayList,Priority.HIGH);
		assertEquals(0,resultArrayList.size(),"Test: No elements returned");

	}

	@Test
	void canGetPriorityNotes_EmptyList() {
		Main.noteArrayList.clear();

		ArrayList<StickyNote> resultArrayList = Main.getPriorityNotes(Main.noteArrayList,Priority.HIGH);
		assertEquals(0,resultArrayList.size(),"Test: No elements returned");

	}
}
