package gui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import spreadsheet.Copy;

/** 
 * @author Kenneth S. Mørck
 * No need for more than one, hence the singleton implementation
 * Listener for the copy menu item
 */
public final class CopyListener implements ActionListener {
	
	public static final CopyListener instance = new CopyListener();

	private CopyListener() {
	  // This is a singleton.
	}

	
	/** Creates new copy */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		new Copy();
	}

}