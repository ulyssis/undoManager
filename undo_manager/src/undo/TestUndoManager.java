/**
 * 
 */
package undo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author max
 *
 */
public class TestUndoManager {

	SimpleText simpleText = new SimpleText();
	int bufferSize = 5;
	EditDescription editDescription = null;
	
	UndoFactory undoFactory = new UndoFactory();
	Undo undo = (Undo) undoFactory.createUndoManager(simpleText, bufferSize);

	@Test
	public void testBasicFunctions() throws Exception {
//		fail("Not yet implemented");
		
		System.out.println("------ test 1 ------\n");
		System.out.println("Test specification: One Edit is made to the doc, undo is available, redo is not:\n");

		// Make a change to the simpleText.
		editDescription = new EditDescription(TypeOfChange.insert, 0, "hello", 0, 10);
		
		assertFalse(undo.canRedo());
		assertFalse(undo.canUndo());
		
		undo.recordChange(editDescription);
		
		assertTrue(undo.canUndo());
		assertFalse(undo.canRedo());
		// Undo the change to the simpleText.
		undo.undo();

		assertFalse(undo.canUndo());
		assertTrue(undo.canRedo());
	}
	
	@Test
	public void testRedoAfterNewEdit() throws Exception {
		
		System.out.println("\n\n------ test 2 ------\n");
		System.out.println("Test specification: Redo is not possible after a new edit is made to the doc.\n");
		
		// Make a change to the simpleText.
		editDescription = new EditDescription(TypeOfChange.insert, 0, "hello", 0, 10);
		undo.recordChange(editDescription);
		
		assertTrue(undo.canUndo());
		assertFalse(undo.canRedo());
		
		// Undo the change to the simpleText.
		undo.undo();
		
		assertTrue(undo.canRedo());
		
		// Make a change to the simpleText.
		editDescription = new EditDescription(TypeOfChange.insert, 0, "hello", 0, 10);
		undo.recordChange(editDescription);
		
		assertFalse(undo.canRedo());
	}
	
	@Test
	public void testBufferSeqence() throws Exception {
		
		System.out.println("\n\n------ test 3 ------\n");
		System.out.println("Test specification: \n"
				+ "step 1. Make edits to file\n"
				+ "step 2. redo all the edits, show contents\n"
				+ "step 3. undo all the edits, show contents\n");
		
		// Make a change to the simpleText.
		EditDescription editDescription = new EditDescription(TypeOfChange.insert, 0, "hello", 0, 10);
		for(int i = 0; i< 5; i++) {
			System.out.println("\nNo." + Integer.toString(i) + " Edit: " + i + "\n");
			editDescription = new EditDescription(TypeOfChange.insert, 0, Integer.toString(i), 0, 10);
			undo.recordChange(editDescription);
		}
		
		// Undo the change to the simpleText.
		undo.undo();
		undo.undo();
		undo.undo();
		undo.undo();
		undo.undo();
		
		assertFalse(undo.canUndo());
		assertTrue(undo.canRedo());
		
		undo.redo();
		undo.redo();
		undo.redo();
		undo.redo();
		undo.redo();
		
		assertFalse(undo.canRedo());
	}
	
	@Test
	public void testBufferContentUpdate() throws Exception {
		undo.clearUndoBuffer();
		undo.clearRedoBuffer();
		System.out.println("\n\n------ test 4 ------\n");
		System.out.println("Test specification: \n" 
				+ "step 1. Make edits to file, buffer is full\n"
				+ "step 2. Make an another edit A\n" 
				+ "step 3. undo all edits, show the contents are identical with latest ones\n");
		
		// Make a change to the simpleText.
		EditDescription editDescription = new EditDescription(TypeOfChange.insert, 0, "hello", 0, 10);
		for(int i = 0; i< 6; i++) {
			System.out.println("\nNo." + Integer.toString(i) + " Edit: " + i + "\n");
			editDescription = new EditDescription(TypeOfChange.insert, 0, Integer.toString(i), 0, 10);
			undo.recordChange(editDescription);
		}
		
		// Undo the change to the simpleText.
		undo.undo();
		undo.undo();
		undo.undo();
		undo.undo();
		undo.undo();
		
		assertFalse(undo.canUndo());
		assertTrue(undo.canRedo());
		
		undo.redo();
		
	}


}
