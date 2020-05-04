package undo;

public class Demo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		SimpleText simpleText = new SimpleText();
		int bufferSize = 32;
		EditDescription editDescription = null;

		UndoFactory undoFactory = new UndoFactory();
		Undo undo = (Undo) undoFactory.createUndoManager(simpleText, bufferSize);
		
		System.out.println("-----------test 1:\nafter adding one change:\n");

		// Make a change to the simpleText.
		editDescription = new EditDescription(TypeOfChange.insert, 0, "hello", 0, 10);
		
		undo.canRedo();
		undo.recordChange(editDescription);
		undo.canUndo();
		undo.undo();
		undo.canUndo();
		
		undo.canRedo();
		undo.redo();
		
		// go wrong here.
		undo.canUndo();
		
		// Make a change to the simpleText.
		editDescription = new EditDescription(TypeOfChange.insert, 0, "hello", 0, 10);
		
		// Make a change to the simpleText.
		editDescription = new EditDescription(TypeOfChange.insert, 0, "hello", 0, 10);
		
		
		
		
	}

}
