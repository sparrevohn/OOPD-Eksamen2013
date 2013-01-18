package gui.control;

import gui.StatusView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import spreadsheet.Paste;
import spreadsheet.exception.InvalidReference;

/** 
 * @author Kenneth S. Mørck
 * No need for more than one, hence the singleton implementation
 * Listener for the paste menu item
 */
public final class PasteListener implements ActionListener {
	
	public static final PasteListener instance = new PasteListener();

	private PasteListener() {
	  // This is a singleton.
	}

	
	/**
	 * Pastes the copied expression if it lives up to the expectations
	 * Else it sets statusview to "Paste reference out of bounds"
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			new Paste();
		} catch (InvalidReference e) {
			StatusView.instance.errorView.setText(
											"Paste reference out of bounds");
		}
	}

}