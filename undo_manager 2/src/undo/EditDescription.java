/**
 * 
 */
package undo;

/**
 * @author max
 *
 */
public class EditDescription {
	TypeOfChange typeOfChange;
	int pos;
	String s;
	int oldDot;
	int newDot;
	
	public EditDescription(TypeOfChange typeOfChang, int pos, String s, int oldDot, int newDot) {
		this.typeOfChange = typeOfChang;
		this.pos = pos;
		this.s = s;
		this.oldDot = oldDot;
		this.newDot = newDot;
	}
}
