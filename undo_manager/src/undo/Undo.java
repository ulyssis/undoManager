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
		ringBufferUndo.add(editDescription);
	}
	
	

	@Override
	public boolean canUndo() {
	if(ringBufferUndo.size() != 0) {
		return true;
	}else {
		throw new IllegalStateException("change cannot be applied!");
		}
	}
	
	@Override
	public void undo() {
		if(canUndo()) {
			//ByteBufferWithInfo info = ringBufferUndo.dequeue();
			EditDescription editDescription = new EditDescription(); 
			try {
				editDescription = ringBufferUndo.peek();
				Edit edit = new Edit(simpleText, editDescription);
				edit.apply(simpleText);
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ringBufferUndo.remove();
				ringBufferRedo.add(editDescription);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	@Override
	public boolean canRedo() {
		if(ringBufferRedo.size() != 0) {
			return true;
		}else {
			throw new IllegalStateException("change cannot be applied!");
		}
	}

	@Override
	public void redo() {
		if(canRedo()) {
			//ByteBufferWithInfo info = ringBufferUndo.dequeue();
			EditDescription editDescription = new EditDescription(); 
			try {
				editDescription = ringBufferRedo.peek();
				Edit edit = new Edit(simpleText, editDescription);
				edit.revert(simpleText);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ringBufferRedo.remove();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
