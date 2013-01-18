package spreadsheet.command;

import gui.TabbedView;
import spreadsheet.Application;
import spreadsheet.Change;
import spreadsheet.History;
import spreadsheet.exception.NoSuchSpreadsheet;
import spreadsheet.exception.OutcastReferenced;

/**
 * @author Kenneth S. Mørck
 * Class for creating a new spreadsheet
 */
public final class NewSpreadsheet
    extends Command 
    implements Change{

	//Sheet name used by the undo function
	private String newSheet;
	
  /**
   * Forces a new spreadsheet, initializes the variable and adds
   * a change to the top of the history stack
   */
  public void execute() {
	newSheet = Application.instance.forceNewSpreadsheet().getName();
	History.instance.push(this);
  }

  /**
   * Undoes the lastly made spreadsheet
   * Will perform an undo but do nothing 
   * if the spreadsheet has already been removed
   */
  @Override
  public void undo() {
	  Application app = Application.instance;
	  if (newSheet != null && app.getSpreadsheet(newSheet) != null) {
		try {
			app.changeWorksheet(newSheet);
			app.removeSpreadsheet();
			app.changeWorksheet(
					TabbedView.instance.getTitleAt(
							TabbedView.instance.getSelectedIndex()));
		} catch (NoSuchSpreadsheet | OutcastReferenced e) {
			app.reportError(e);
		}
	  }
	  
	  
	}
	

  
}
