package gui.control;

import gui.StatusView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import spreadsheet.Paste;
import spreadsheet.exception.InvalidReference;

/**
 * @author Kenneth S. Mørck
 * Listener for the menu item Paste
 */
public final class PasteListener implements ActionListener {
	
	  public static final PasteListener instance = new PasteListener();

	  private PasteListener() {
	    // This is a singleton.
	  }

	
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