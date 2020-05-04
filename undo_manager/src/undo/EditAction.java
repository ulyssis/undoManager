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
	Document simpleText  = new SimpleText();

	
	EditAction(int pos, String s, int oldDot, int newDot) {
		this.pos = pos;
		this.s = s;
		this.oldDot = oldDot;
		this.newDot = newDot;
	}
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
	
	public void Deletetion(Document doc) {
		createDeletion(pos, s, oldDot, newDot);
	}
	
	public void Insertion(Document doc) {
		createInsertion(pos, s, oldDot, newDot);
	}

}
