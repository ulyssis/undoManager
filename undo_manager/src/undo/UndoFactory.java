/**
 * 
 */
package undo;

/**
 * @author max
 *
 */
public class UndoFactory implements UndoManagerFactory {

	@Override
	public UndoManager createUndoManager(Document doc, int bufferSize) {
		// TODO Auto-generated method stub
		Undo undo = new Undo(doc, bufferSize);
		
		return undo;
	}

}
