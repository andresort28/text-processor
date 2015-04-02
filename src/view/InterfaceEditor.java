package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.FileManager;

import model.Processor;



public class InterfaceEditor extends JFrame
{

	private MenuBar menuBar;
	private ToolBar toolBar;
	private LanguagePanel languagePanel;
	private ContentPanel contentPanel;
	
	private Processor processor;
	private boolean saved;
	private String path;
	
	private boolean popUp;
	private PopUpMenu popUpMenu;
	
	public InterfaceEditor ()
	{
		setTitle("TextEditor");
		setSize(530, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(this);
		setResizable(false);
		
		saved = false;
		path = "";
		popUp = false;
		popUpMenu = null;
		processor = new Processor();
		
		menuBar = new MenuBar(this);
		toolBar = new ToolBar(this);
		contentPanel = new ContentPanel(this);
		JScrollPane scroll = new JScrollPane(contentPanel);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		languagePanel = new LanguagePanel(this);

		setJMenuBar(menuBar);
		add(toolBar, BorderLayout.PAGE_START);
		add(scroll, BorderLayout.CENTER);
		add(languagePanel, BorderLayout.PAGE_END);	
		
	}
	
	public void newDocument ()
	{
		int r = JOptionPane.showConfirmDialog(this, "Do you want to save the current document ?");
		if(r == JOptionPane.OK_OPTION)
		{
			saveFile();
			refreshData();
		}
		if(r == JOptionPane.NO_OPTION)
			refreshData();
		saved = false;
	}
	
	
	public void openFile ()
	{
		String text = FileManager.openFile(this);
		if( text != null)
		{
			setContent(text);
			refreshWords();
		}
	}
	
	
	public void saveFile ()
	{
		if(!saved)
			saveAsFile();
		else
		{
			FileManager.saveFile(contentPanel.getText(), path);
		}
	}
	
	public void saveAsFile ()
	{
		path = FileManager.saveAsFile(this, contentPanel.getText());	
		if(path != null)
			saved = true;
	}
	
	public void refreshData ()
	{
		contentPanel.setText("");
	}
	
	public void copy ()
	{
		contentPanel.copy();
	}
	
	public void cut ()
	{
		contentPanel.cut();
	}
	
	public void paste ()
	{
		contentPanel.paste();
		refreshWords();
	}
	 
	public void savePDF ()
	{
		JOptionPane.showMessageDialog(this, "Not yet available", "Information", JOptionPane.INFORMATION_MESSAGE);
		//FileManager.savePDF(contentPanel.getText());
	}
	
	
	public void print ()
	{
		FileManager.print(contentPanel);
	}
	
	public void search ()
	{
		JOptionPane.showMessageDialog(this, "Not yet available", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void replace ()
	{
		JOptionPane.showMessageDialog(this, "Not yet available", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void selectAll ()
	{
		JOptionPane.showMessageDialog(this, "Not yet available", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
		
	public void autoCorrect ()
	{
		JOptionPane.showMessageDialog(this, "Please, right click on the wrong word", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void exit ()
	{
		int r = JOptionPane.showConfirmDialog(this, "Do you want to save the current document ?");
		if(r == JOptionPane.OK_OPTION)
		{
			saveFile();
			dispose();
		}
		if(r == JOptionPane.NO_OPTION)
			dispose();
	}
	
	public void correctWord (String word, Point p)
	{
		if(!processor.verifyWord(word))
		{
			ArrayList<String> list = processor.getPossible(word);
			PopUpMenu popUp = new PopUpMenu(this, p, list);
			popUp.setVisible(true);
		}
	}
	
	public void changeWord (String word2)
	{
		String[] text = contentPanel.getText().split(" ");		
		String text2 = "";
		for (int i = 0; i < text.length; i++) 
		{
			String word = text[i];
			word = word.trim();
			if(!word.equalsIgnoreCase(" "))
			{
				if( word2.equalsIgnoreCase(word) )
					text2 += "<font color=\"black\">" + word2 + "</font> ";
				else if(!processor.verifyWord(word))
				{
					if(processor.isOmitted(word))
						text2 += "<font color=\"black\">" + word + "</font> ";
					else
						text2 += "<font color=\"red\">" + word + "</font> ";
				}				
				else
				{
					if(text2.length() == 0)
						text2 += word + " ";
					else
						text2 += " " + word + " ";
				}
			}			
			else
				text2 += "<font color=\"black\">" + " " + "</font>";
		}
		contentPanel.setText(text2);
		languagePanel.refreshWords();
		
	}
	
	public void refreshWords ()
	{
		String[] text = contentPanel.getText().split(" ");		
		String text2 = "";
		for (int i = 0; i < text.length; i++) 
		{
			String word = text[i];
			word = word.trim();
			if(!word.equalsIgnoreCase(" "))
			{
				if(!processor.verifyWord(word))
				{
					if(processor.isOmitted(word))
						text2 += "<font color=\"black\">" + word + "</font> ";
					else
						text2 += "<font color=\"red\">" + word + "</font> ";
				}
				else
				{
					if(text2.length() == 0)
						text2 += word + " ";
					else
						text2 += " " + word + " ";
				}
			}
			else
				text2 += "<font color=\"black\">" + " " + "</font>";
		}
		contentPanel.setText(text2);
		languagePanel.refreshWords();
	}
	
	public void setContent (String text)
	{
		contentPanel.setText(text);
	}
	
	public void changeLanguage (int language)
	{
		if(getLanguage() != language)
		{
			processor.setUpLanguage(language);
			languagePanel.changeLanguage();
			refreshWords();
		}
	}
	
	public int getLanguage ()
	{
		return processor.getLanguage();
	}
	
	public ArrayList<String> getLanguages ()
	{
		return processor.getLanguages();
	}
	
	public int getTextLenght ()
	{
		return contentPanel.getLength();
	}
	
	public void setPopUpStatus (boolean status)
	{
		popUp = status;
	}
	
	public boolean getPopUpStatus ()
	{
		return popUp;
	}
	
	
	
	
	
	


}
