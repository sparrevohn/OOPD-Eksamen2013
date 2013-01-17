package gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import spreadsheet.Copy;

/**
 * @author Kenneth S. Mørck
 * Listener for the menu item Copy
 */
public final class CopyListener implements ActionListener {
	
	  public static final CopyListener instance = new CopyListener();

	  private CopyListener() {
	    // This is a singleton.
	  }

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		new Copy();
	}

}