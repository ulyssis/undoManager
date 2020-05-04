package undo;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		SimpleText simpleText = new SimpleText();
		int bufferSize = 32;

		UndoFactory undoFactory = new UndoFactory();
		Undo undo = (Undo) undoFactory.createUndoManager(simpleText, bufferSize);
		
		undo.canUndo();
		
		
		
	}

}
