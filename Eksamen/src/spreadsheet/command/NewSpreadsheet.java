package spreadsheet.command;

import spreadsheet.Application;
import spreadsheet.Change;
import spreadsheet.History;
import spreadsheet.exception.NoSuchSpreadsheet;
import spreadsheet.exception.OutcastReferenced;

public final class NewSpreadsheet
    extends Command 
    implements Change{

	private String sheet;
	
  public void execute() {
    Application.instance.forceNewSpreadsheet();
    sheet = Application.instance.getWorksheet().getName();
    History.instance.push(this);
  }

@Override
public void undo() {
	if (sheet != null) {
		try {
			Application.instance.changeWorksheet(sheet);
			Application.instance.removeSpreadsheet();
		} catch (NoSuchSpreadsheet | OutcastReferenced e) {
			e.printStackTrace();
		}
	}
	}
	

  
}
