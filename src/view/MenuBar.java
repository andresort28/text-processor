package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

public class MenuBar extends JMenuBar implements ActionListener
{
	private JMenu menuFile;
	
	private JMenuItem iNew;
	private JMenuItem iOpen;
	private JMenuItem iSave;
	private JMenuItem iSaveAs;
	private JMenuItem iExportPDF;
	private JMenuItem iPrint;
	private JMenuItem iExit;
		
	
	private JMenu menuEdit;
	
	private JMenuItem iCopy;	
	private JMenuItem iCut;
	private JMenuItem iPaste;
	private JMenuItem iSearch;
	private JMenuItem iReplace;
	private JMenuItem iSelectAll;	
	private JMenuItem iAutoCorrect;
		
	
	
	private JMenu menuAbout;
	
	private JMenuItem iAbout;
	private JMenuItem iHelp;
	
	private InterfaceEditor editor;
	
		
	public MenuBar(InterfaceEditor editor)
	{
		this.editor = editor;
		menuFile = new JMenu("File");
		
		iNew = new JMenuItem("New", new ImageIcon("img/new.png"));
		iNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		iNew.addActionListener(this);
		
		iOpen = new JMenuItem("Open", new ImageIcon("img/open.png"));
		iOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		iOpen.addActionListener(this);		
		
		iSave = new JMenuItem ("Save", new ImageIcon("img/save.png"));
		iSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		iSave.addActionListener(this);	
		
		iSaveAs = new JMenuItem("Save As",  new ImageIcon("img/saveas.png"));
		iSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		iSaveAs.addActionListener(this);	
		
		iExportPDF = new JMenuItem("Export to PDF", new ImageIcon("img/export.png"));
		iExportPDF.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		iExportPDF.addActionListener(this);	
		
		iPrint = new JMenuItem("Print", new ImageIcon("img/print.png"));
		iPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		iPrint.addActionListener(this);	
		
		iExit = new JMenuItem ("Exit", new ImageIcon("img/exit.png"));
		iExit.addActionListener(this);	
		
		menuFile.add(iNew);
		menuFile.add(iOpen);
		menuFile.add(new JSeparator());
		menuFile.add(iSave);
		menuFile.add(iSaveAs);
		menuFile.add(iExportPDF);
		menuFile.add(iPrint);
		menuFile.add(new JSeparator());
		menuFile.add(iExit);
		
		
		
		
		menuEdit = new JMenu("Edit");
		
		iCopy = new JMenuItem("Copy", new ImageIcon("img/copy.png"));
		iCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		iCopy.addActionListener(this);
		
		iCut = new JMenuItem("Cut", new ImageIcon("img/cut.png"));
		iCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		iCut.addActionListener(this);
		
		iPaste = new JMenuItem("Paste", new ImageIcon("img/paste.png"));
		iPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		iPaste.addActionListener(this);
		
		iSearch = new JMenuItem("Search", new ImageIcon("img/search.png"));
		iSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
		iSearch.addActionListener(this);
		
		iReplace = new JMenuItem("Replace", new ImageIcon("img/replace.png"));
		iReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		iReplace.addActionListener(this);
		
		iSelectAll = new JMenuItem("Select All", new ImageIcon("img/selectall.png"));
		iSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		iSelectAll.addActionListener(this);
		
		iAutoCorrect = new JMenuItem("Auto-Correct", new ImageIcon("img/correct.png"));
		iAutoCorrect.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		iAutoCorrect.addActionListener(this);

		menuEdit.add(iCopy);
		menuEdit.add(iCut);
		menuEdit.add(iPaste);
		menuEdit.add(new JSeparator());
		menuEdit.add(iSearch);
		menuEdit.add(iReplace);
		menuEdit.add(iSelectAll);
		menuEdit.add(new JSeparator());
		menuEdit.add(iAutoCorrect);
		
		
		
		
		
		
		
		menuAbout = new JMenu("About");
		
		iAbout = new JMenuItem("About", new ImageIcon("img/about.png"));
		iAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.SHIFT_MASK));
		iAbout.addActionListener(this);
		
		iHelp = new JMenuItem("Help", new ImageIcon("img/help.png"));
		iHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.SHIFT_MASK));
		iHelp.addActionListener(this);
		
		menuAbout.add(iAbout);
		menuAbout.add(iHelp);
		
		
		
		add(menuFile);
		add(menuEdit);
		add(menuAbout);
	}




	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String cmd = e.getActionCommand();
		
		if(cmd.equalsIgnoreCase("New"))
			editor.newDocument();
		if(cmd.equalsIgnoreCase("Open"))
			editor.openFile();
		if(cmd.equalsIgnoreCase("Save"))
			editor.saveFile();
		if(cmd.equalsIgnoreCase("Save As"))
			editor.saveAsFile();		
		if(cmd.equalsIgnoreCase("Export to PDF"))
			editor.savePDF();
		if(cmd.equalsIgnoreCase("Print"))
			editor.print();
		if(cmd.equalsIgnoreCase("Exit"))
			editor.exit();
		
		if(cmd.equalsIgnoreCase("Copy"))
			editor.copy();
		if(cmd.equalsIgnoreCase("Cut"))
			editor.cut();
		if(cmd.equalsIgnoreCase("Paste"))
			editor.paste();
		if(cmd.equalsIgnoreCase("Search"))
			editor.search();
		if(cmd.equalsIgnoreCase("Replace"))
			editor.replace();
		if(cmd.equalsIgnoreCase("Select All"))
			editor.selectAll();		
		if(cmd.equalsIgnoreCase("Auto-Correct"))
			editor.autoCorrect();
		
		if(cmd.equalsIgnoreCase("About"))
			JOptionPane.showMessageDialog(editor, "Developed by: \n\n Andres Felipe Ortiz \n Daniel Andres Palacios \n Yan Sebastian Arce \n\n Universidad ICESI \n 10/09/2012", "Development", JOptionPane.DEFAULT_OPTION);
			
		if(cmd.equalsIgnoreCase("Help"))
			JOptionPane.showMessageDialog(editor, "Help Unavailable", "Help", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
}
