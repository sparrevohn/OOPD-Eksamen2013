package gui.control;

import gui.StatusView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import spreadsheet.Change;
import spreadsheet.History;

/** 
 * @author Kenneth S. Mørck
 * No need for more than one, hence the singleton implementation
 * Listener for the undo menu item
 */
public final class UndoListener implements ActionListener {

	public static final UndoListener instance = new UndoListener();

	private UndoListener() {
		// This is a singleton.
	}

	/**
	 * Undoes last change, unless no changes have been made then
	 * prints "Nothing to undo" to the statusview
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		Change nullTest = History.instance.pop();
		if (nullTest != null)
			nullTest.undo();
		else
			StatusView.instance.errorView.setText("Nothing to undo");
	}

}
