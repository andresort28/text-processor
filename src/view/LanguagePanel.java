package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Processor;

public class LanguagePanel extends JPanel implements ActionListener 
{
	
	private JButton diccionary;
	private JLabel words;
	
	private InterfaceEditor editor;
	
	public LanguagePanel (InterfaceEditor editor)
	{
		this.editor = editor;		
		setLayout(new BorderLayout());
		words = new JLabel("Words: " + 0, JLabel.LEFT);
		words.setFont(new Font("", 0, 13));
		diccionary = new JButton();
		changeLanguage();
		diccionary.setPreferredSize(new Dimension(100, 20));
		diccionary.addActionListener(this);
		add(words, BorderLayout.WEST);
		add(diccionary, BorderLayout.EAST);
	}
		
	
	public void refreshWords ()
	{
		words.setText("Words: " + editor.getTextLenght());
	}
	
	public void changeLanguage ()
	{
		switch (editor.getLanguage())
		{
			case Processor.SPANISH:
				diccionary.setText("Spanish");
				diccionary.setIcon(new ImageIcon("./img/spanish.png"));
				break;
			case Processor.AMERICAN_ENGLISH:
				diccionary.setText("English");
				diccionary.setIcon(new ImageIcon("./img/american.png"));
				break;
			case Processor.BRITISH_ENGLISH:
				diccionary.setText("English");
				diccionary.setIcon(new ImageIcon("./img/british.png"));
				break;
			case Processor.FRENCH:
				diccionary.setText("French");
				diccionary.setIcon(new ImageIcon("./img/french.png"));
				break;
			case Processor.GERMAN:
				diccionary.setText("German");
				diccionary.setIcon(new ImageIcon("./img/german.png"));
				break;
		}
	}
	
		
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		LanguageDialog options = new LanguageDialog(editor);
		options.setVisible(true);	
	}
	
	

}
