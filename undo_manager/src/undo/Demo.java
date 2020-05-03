package undo;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		SimpleText text = new SimpleText();
		int bufferSize = 32;
		
		UndoFactory undoFactory = new UndoFactory();
		Undo undo = (Undo) undoFactory.createUndoManager(text, bufferSize);
		
		
		EditAction editAction = new EditAction();
		editAction.createDeletion(0, "Hello!", 2, 3);
		
		Edit edit = new Edit(text);
		edit.apply(text);
		
		
		if(undo.canRedo()) {
			undo.registerChange(edit);
		}
		
	}

}
