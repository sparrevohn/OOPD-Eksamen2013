package spreadsheet.command;

import gui.StatusView;
import spreadsheet.Application;
import spreadsheet.Change;
import spreadsheet.History;
import spreadsheet.exception.NoSuchSpreadsheet;
import spreadsheet.exception.OutcastReferenced;

public final class NewSpreadsheet
    extends Command 
    implements Change{

	private String originalSheet;
	private String newSheet;
	
  public void execute() {
	originalSheet = Application.instance.getWorksheet().getName();
    Application.instance.forceNewSpreadsheet();
    newSheet = Application.instance.getWorksheet().getName();
    try {
		Application.instance.changeWorksheet(originalSheet);
	} catch (NoSuchSpreadsheet e) {
		System.out.println("Fejl");
	}
    History.instance.push(this);
  }

  @Override
  public void undo() {
	Application shortcut = Application.instance;
	if (newSheet != null && shortcut.getSpreadsheet(newSheet) != null) {
		try {
			shortcut.changeWorksheet(newSheet);
			shortcut.removeSpreadsheet();
		} catch (NoSuchSpreadsheet | OutcastReferenced e) {
			e.printStackTrace();
		}
	}
	}
	

  
}
