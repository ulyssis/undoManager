/**
 * 
 */
package undo;

/**
 * @author max
 *	this class makes use of Class ChangeFactory to edit doc.
 */
public class Edit implements Change {

	String typeOfChange;
	
	public Edit(Document doc) {
	}
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return typeOfChange;
	}

	@Override
	// FIX: how to apply editAction on doc??
	public void apply(Document doc) {
		EditAction editAction = new EditAction();
		if(typeOfChange.equals("delete")) {
			editAction.Deletetion();
		}else if(typeOfChange.equals("insert")) {
			editAction.Insertion();
		}
	}

	@Override
	// FIX: how to apply editAction on doc??
	public void revert(Document doc) {
		EditAction editAction = new EditAction();
		if(typeOfChange.equals("delete")) {
			editAction.Deletetion();
		}else if(typeOfChange.equals("insert")) {
			editAction.Insertion();
		}
	}

}
