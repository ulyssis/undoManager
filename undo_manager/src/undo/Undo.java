/**
 * 
 */

package undo;

/**
 * @author max
 *
 */
public class Undo implements UndoManager {
	
	RingBuffer<EditDescription> ringBufferUndo;
	RingBuffer<EditDescription> ringBufferRedo;
	SimpleText simpleText;
	
	
	public Undo(Document doc, int bufferSizes) {
		ringBufferUndo = new RingBuffer<EditDescription>(bufferSizes);
		ringBufferRedo = new RingBuffer<EditDescription>(bufferSizes);
		simpleText = (SimpleText) doc;
	}

	@Override
	public void registerChange(Change change){
		// TODO check the convert!
	}
	
	public void recordChange(EditDescription editDescription) throws InterruptedException{
		System.out.print("One Edit is added into buffer.\n"); 
		ringBufferUndo.add(editDescription);
		
		while(ringBufferRedo.size() > 0) {
			ringBufferRedo.remove();
		}
	}
	
	

	@Override
	public boolean canUndo() {
	if(ringBufferUndo.size() > 0) {
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
			try {
				EditDescription editDescription = ringBufferUndo.peek();
				Edit edit = new Edit(simpleText, editDescription);
				edit.apply(simpleText);
				ringBufferUndo.remove();
				ringBufferRedo.add(editDescription);
				System.out.print("Undo is made! The undone content is " + editDescription.s +"\n"); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public boolean canRedo() {
		if(ringBufferRedo.size() != 0) {
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
				try {
					EditDescription editDescription = ringBufferRedo.peek();
					Edit edit = new Edit(simpleText, editDescription);
					edit.revert(simpleText);
					ringBufferRedo.remove();
					ringBufferUndo.add(editDescription);
					System.out.print("Redo is made! The recovered content is " + editDescription.s + "\n"); 

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
