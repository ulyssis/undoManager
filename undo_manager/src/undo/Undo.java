/**
 * 
 */

package undo;

import com.sun.corba.se.impl.encoding.BufferQueue;
import com.sun.corba.se.impl.encoding.ByteBufferWithInfo;

/**
 * @author max
 *
 */
public class Undo implements UndoManager {

	Change change;
	BufferQueue bufferUndo;
	BufferQueue bufferRedo;
	
	@Override
	public void registerChange(Change change) {
		// TODO check the convert!
		ByteBufferWithInfo info = (ByteBufferWithInfo) change;
		bufferUndo.push(info);
	}

	@Override
	public boolean canUndo() {
	if(bufferUndo.size() != 0) {
		return true;
	}else {
		return false;
	}
	}

	@Override
	public void undo() {
		if(bufferUndo.size() != 0) {
			ByteBufferWithInfo info = bufferUndo.dequeue();
			bufferRedo.push(info);
		}
	}

	@Override
	public boolean canRedo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub

	}

}
