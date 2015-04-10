package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar implements ActionListener
{
	
	private JButton iNew;
	private JButton iOpen;
	private JButton iSave;
	private JButton iSaveAs;
	private JButton iCopy;
	private JButton iCut;
	private JButton iPaste;
	
	private JButton iCorrect;
		
	private JButton iExportPDF;
	private JButton iPrint;
	
	private InterfaceEditor editor;
	
	public ToolBar (InterfaceEditor editor)
	{
		super("Tool Bar", JToolBar.HORIZONTAL);
		this.editor = editor;
		
		setFloatable(false);
		setRollover(true);
		setBorderPainted(true);
		
		iNew = new JButton("New", new ImageIcon("img/new2.png"));
		iNew.addActionListener(this);
		iNew.setToolTipText("New Document");
		iNew.setVerticalTextPosition(JButton.BOTTOM);
		iNew.setHorizontalTextPosition(JButton.CENTER);
		
		iOpen = new JButton("Open", new ImageIcon("img/open2.png"));
		iOpen.addActionListener(this);	
		iOpen.setToolTipText("Open");
		iOpen.setVerticalTextPosition(JButton.BOTTOM);
		iOpen.setHorizontalTextPosition(JButton.CENTER);
		
		iSave = new JButton ("Save", new ImageIcon("img/save2.png"));
		iSave.addActionListener(this);	
		iSave.setToolTipText("Save");
		iSave.setVerticalTextPosition(JButton.BOTTOM);
		iSave.setHorizontalTextPosition(JButton.CENTER);
		
		iSaveAs = new JButton("Save As",  new ImageIcon("img/saveas2.png"));
		iSaveAs.addActionListener(this);	
		iSaveAs.setToolTipText("Save As");
		iSaveAs.setVerticalTextPosition(JButton.BOTTOM);
		iSaveAs.setHorizontalTextPosition(JButton.CENTER);
		
		iCopy = new JButton ("Copy", new ImageIcon("img/copy2.png"));
		iCopy.addActionListener(this);	
		iCopy.setToolTipText("Copy");
		iCopy.setVerticalTextPosition(JButton.BOTTOM);
		iCopy.setHorizontalTextPosition(JButton.CENTER);
		
		iCut = new JButton ("Cut", new ImageIcon("img/cut2.png"));
		iCut.addActionListener(this);	
		iCut.setToolTipText("Cut");
		iCut.setVerticalTextPosition(JButton.BOTTOM);
		iCut.setHorizontalTextPosition(JButton.CENTER);
		
		iPaste = new JButton ("Paste", new ImageIcon("img/paste2.png"));
		iPaste.addActionListener(this);	
		iPaste.setToolTipText("Paste");
		iPaste.setVerticalTextPosition(JButton.BOTTOM);
		iPaste.setHorizontalTextPosition(JButton.CENTER);
		
		
		iExportPDF = new JButton("PDF", new ImageIcon("img/export2.png"));
		iExportPDF.addActionListener(this);	
		iExportPDF.setToolTipText("Export to PDF");
		iExportPDF.setVerticalTextPosition(JButton.BOTTOM);
		iExportPDF.setHorizontalTextPosition(JButton.CENTER);
		
		iPrint = new JButton("Print", new ImageIcon("img/print2.png"));
		iPrint.addActionListener(this);
		iPrint.setToolTipText("Print");
		iPrint.setVerticalTextPosition(JButton.BOTTOM);
		iPrint.setHorizontalTextPosition(JButton.CENTER);
		
		iCorrect = new JButton ("Correct", new ImageIcon("img/correct2.png"));
		iCorrect.addActionListener(this);	
		iCorrect.setToolTipText("Correct");
		iCorrect.setVerticalTextPosition(JButton.BOTTOM);
		iCorrect.setHorizontalTextPosition(JButton.CENTER);
		
		addSeparator();
		add(iNew);
		add(iOpen);
		addSeparator();
		add(iSave);
		add(iSaveAs);
		addSeparator();
		add(iCopy);
		add(iCut);
		add(iPaste);
		addSeparator();
		add(iExportPDF);
		add(iPrint);
		addSeparator();
		add(iCorrect);
		
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
			editor.saveFile();
		if(cmd.equalsIgnoreCase("Copy"))
			editor.copy();
		if(cmd.equalsIgnoreCase("Cut"))
			editor.cut();
		if(cmd.equalsIgnoreCase("Paste"))
			editor.paste();
		if(cmd.equalsIgnoreCase("PDF"))
			editor.savePDF();
		if(cmd.equalsIgnoreCase("Print"))
			editor.print();
		if(cmd.equalsIgnoreCase("Correct"))
			editor.autoCorrect();
	}
	
	
	
	
	
	
}
