/**
 * 
 */
package undo;

/**
 * @author max
 *
 */
public class EditAction implements ChangeFactory {

	int pos;
	String s;
	int oldDot;
	int newDot;
	
	@Override
	public Change createDeletion(int pos, String s, int oldDot, int newDot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Change createInsertion(int pos, String s, int oldDot, int newDot) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void Deletetion() {
		createDeletion(pos, s, oldDot, newDot);
	}
	
	public void Insertion() {
		createInsertion(pos, s, oldDot, newDot);
	}

}
