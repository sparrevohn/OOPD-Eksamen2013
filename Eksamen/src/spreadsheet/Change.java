package spreadsheet;

/**
 * @author Kenneth S. Mørck
 * Interface used to undo a command
 * @see spreadsheet.command.Set
 * @see spreadsheet.command.NewSpreadsheet
 */
public interface Change {
 	
	abstract void undo();

}
