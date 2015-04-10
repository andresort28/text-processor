package view;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class ContentPanel extends JEditorPane implements KeyListener, MouseListener, Printable
{
	
	private InterfaceEditor editor;
	
	
	public ContentPanel (InterfaceEditor editor)
	{
		super();
		this.editor = editor;
		addKeyListener(this);
		addMouseListener(this);
		setContentType("text/html");
	}
	
	
	public int getLength ()
	{
		String line = "";
		try 
		{
			line = getDocument().getText(0, getDocument().getLength());
		} 
		catch (Exception e) {
		}
		return line.split(" ").length;
	}
	
	public String getText ()
	{
		String line = "";
		try 
		{
			line = getDocument().getText(0, getDocument().getLength());
		} 
		catch (Exception e) {
		}
		return line.trim();
		
	}
	


	@Override
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_SPACE)
			editor.refreshWords();
	}


	@Override
	public void keyReleased(KeyEvent e) 
	{		
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
	}


	@Override
	public void mouseClicked(MouseEvent e) 
	{
		int c = e.getClickCount();
		if(c == 2)
		{
			String word = getSelectedText();
			if(word != null)
				editor.correctWord(word, e.getLocationOnScreen());
		}
		//aq45 teng6 q *6ner q4e desab535te e3 *6*0en4
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		// TODO Auto-generated method stub
		return 0;
	}
}
