package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import gui.language.Language;

import gui.control.CopyListener;
import gui.control.ExitListener;
import gui.control.NewSpreadsheetListener;
import gui.control.PasteListener;
import gui.control.RemoveSpreadsheetListener;
import gui.control.UndoListener;

/** The main frame menu bar.
 * <p>
 * The main frame has just one menu bar, hence the singleton implementation.
 */
public final class MenuBar
    extends JMenuBar {

  public static final long serialVersionUID = 1L;

  public static final MenuBar instance = new MenuBar();

  private MenuBar() {
    super();
    this.add(this.newFileMenu());
    this.add(this.newSpreadsheetMenu());
    this.add(this.newEditMenu());
  }

  private JMenu newFileMenu() {
    final JMenu menu = new JMenu(Language.instance.file());
    menu.add(this.newExitMenuItem());
    return menu;
  }

  private JMenuItem newExitMenuItem() {
    final JMenuItem menuItem = new JMenuItem(Language.instance.exit());
    menuItem.addActionListener(ExitListener.instance);
    return menuItem;
  }
  
  private JMenu newSpreadsheetMenu() {
    final JMenu menu = new JMenu(Language.instance.spreadsheet());
    menu.add(this.newNewSpreadsheetMenuItem());
    menu.add(this.newRemoveSpreadsheetMenuItem());
    return menu;
  }
  
  private JMenuItem newNewSpreadsheetMenuItem() {
    final JMenuItem menuItem = new JMenuItem(Language.instance.newSpreadsheet());
    menuItem.addActionListener(NewSpreadsheetListener.instance);
    return menuItem;
  }
  
  private JMenuItem newRemoveSpreadsheetMenuItem() {
    final JMenuItem menuItem = new JMenuItem(Language.instance.removeSpreadsheet());
    menuItem.addActionListener(RemoveSpreadsheetListener.instance);
    return menuItem;
  }

  /**
   * Creates the Edit menu
   * @return JMenu used for Undo, Copy and Paste menu items
   */
  private JMenu newEditMenu() {
	final JMenu menu = new JMenu(Language.instance.edit());
	menu.add(this.newUndoMenuItem());
	menu.add(this.newCopyMenuItem());
	menu.add(this.newPasteMenuItem());
	return menu;
  }
  
  /**
   * Creates the undo menu item
   * @return JMenuItem used to undo actions in the spreadsheet
   */
  private JMenuItem newUndoMenuItem() {
	final JMenuItem menuItem = new JMenuItem(Language.instance.undo());
	menuItem.addActionListener(UndoListener.instance);
	return menuItem;
  }
  
  /**
   * Creates the copy menu item
   * @return JMenuItem used to copy expressions in the spreadsheet
   */
  private JMenuItem newCopyMenuItem() {
		final JMenuItem menuItem = new JMenuItem(Language.instance.copy());
		menuItem.addActionListener(CopyListener.instance);
		return menuItem;
  }
  
  /**
   * Creates the paste menu item
   * @return JMenuItem used to paste expressions in the spreadsheet
   */
  private JMenuItem newPasteMenuItem() {
		final JMenuItem menuItem = new JMenuItem(Language.instance.paste());
		menuItem.addActionListener(PasteListener.instance);
		return menuItem;
}
}
