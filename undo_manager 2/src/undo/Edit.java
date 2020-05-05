/**
 * 
 */
package undo;

/**
 * @author max
 *	this class makes use of Class ChangeFactory to edit doc.
 */
public class Edit implements Change {

	TypeOfChange typeOfChange;
	int pos;
	String s;
	int oldDot;
	int newDot;
	Document simpleText;

	
	public Edit(Document doc, EditDescription editDescription) {
		this.typeOfChange = editDescription.typeOfChange;
		this.pos = editDescription.pos;
		this.s = editDescription.s;
		this.oldDot = editDescription.oldDot;
		this.newDot = editDescription.newDot;
		simpleText = doc;
	}
	
	
	@Override
	public String getType() {
		String ret = null;
		if(typeOfChange.equals(TypeOfChange.delete)) {
			ret = "delete";
		}
		if(typeOfChange.equals(TypeOfChange.insert)) {
			ret = "insert";
		}
		return ret;
		
	}

	@Override
	// FIX: how to apply editAction on doc??
	public void apply(Document doc) {
		EditAction editAction = new EditAction(pos, s, oldDot, newDot);
		if(this.typeOfChange.equals(TypeOfChange.delete)) {
			editAction.Deletetion(simpleText);
			simpleText.delete(pos, s);
		}else if(this.typeOfChange.equals(TypeOfChange.insert)) {
			editAction.Insertion(simpleText);
			simpleText.insert(pos, s);
		}
	}

	@Override
	public void revert(Document doc) {
		EditAction editAction = new EditAction(pos, s, oldDot, newDot);
		if(this.typeOfChange.equals(TypeOfChange.delete)) {
			editAction.Deletetion(simpleText);
			simpleText.delete(pos, s);
		}else if(this.typeOfChange.equals(TypeOfChange.insert)) {
			editAction.Insertion(simpleText);
			simpleText.insert(pos, s);
		}
	}

	
	

}
