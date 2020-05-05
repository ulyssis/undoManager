/**
 * 
 */

package undo;
/**
 * @author max
 *
 */
public class Undo implements UndoManager {
	
	LIFOQueueLimitedSize<EditDescription> LIFOQueueUndo;
	LIFOQueueLimitedSize<EditDescription> LIFOQueueRedo;
	SimpleText simpleText;
	
	
	public Undo(Document doc, int bufferSizes) {
		LIFOQueueUndo = new LIFOQueueLimitedSize<EditDescription>(bufferSizes);
		LIFOQueueRedo = new LIFOQueueLimitedSize<EditDescription>(bufferSizes);
		simpleText = (SimpleText) doc;
	}

	@Override
	public void registerChange(Change change){
		// TODO check the convert!
	}
	
	public void recordChange(EditDescription editDescription) throws InterruptedException{
		System.out.print("One Edit is added into buffer.\n"); 
		LIFOQueueUndo.add(editDescription);
		
		LIFOQueueRedo.clear();
	}
	
	

	@Override
	public boolean canUndo() {
	if(LIFOQueueUndo.size() > 0) {
		System.out.print("Undo can be done, in case you want to make an undo.\n"); 
		return true;
	}else {
		System.out.print("Undo can NOT be done, because there is no records of input anymore.\n"); 
//		throw new IllegalStateException("change cannot be applied!");
		return false;
		}
	}
	
	@Override
	public void undo() {
		if(canUndo()) {
			EditDescription editDescription;
			editDescription = LIFOQueueUndo.pollLast();
			Edit edit = new Edit(simpleText, editDescription);
			edit.apply(simpleText);
			LIFOQueueRedo.add(editDescription);
			System.out.print("Undo is made! The undone content is " + editDescription.s +"\n");
		}else {
			throw new IllegalStateException("undoBuffer is empty!");
		}
		
	}


	@Override
	public boolean canRedo() {
		if(LIFOQueueRedo.size() != 0) {
			System.out.print("Redo can be done, in case you want to make a Redo.\n"); 
			return true;
		}else {
			System.out.print("Redo can NOT be done right now.\n"); 
			return false;
			//			throw new IllegalStateException("change cannot be applied!\n");
		}
	}

	@Override
	public void redo() {
		if(canRedo()) {
			EditDescription editDescription;
			editDescription = LIFOQueueRedo.pollLast();
			Edit edit = new Edit(simpleText, editDescription);
			edit.revert(simpleText);
			LIFOQueueUndo.add(editDescription);
			System.out.print("Redo is made! The recovered content is " + editDescription.s + "\n");
		}else {
			throw new IllegalStateException("redoBuffer is empty!");
		}
	}
	
	public void clearUndoBuffer() {
		LIFOQueueUndo.clear();
	}

	public void clearRedoBuffer() {
		LIFOQueueRedo.clear();
	}

}
