package gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import spreadsheet.Paste;

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
		new Paste().execute();
	}

}