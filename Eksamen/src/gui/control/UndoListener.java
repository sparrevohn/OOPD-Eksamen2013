package gui.control;

import gui.StatusView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import spreadsheet.Change;
import spreadsheet.History;

/** A listener for the remove spreadsheet menu item.
 * <p>
 * No need for more than one, hence the singleton implementation.
 */
public final class UndoListener
    implements ActionListener {

  public static final UndoListener instance = new UndoListener();

  private UndoListener() {
    // This is a singleton.
  }

  public void actionPerformed(ActionEvent event) {
	Change nullTest = History.instance.pop();
	if (nullTest != null)
		nullTest.undo();
	else
		StatusView.instance.errorView.setText("Nothing to undo");
  }

}
