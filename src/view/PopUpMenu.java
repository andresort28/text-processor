package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class PopUpMenu extends JPopupMenu implements ActionListener
{
	public final static String ADD = "Add";
	public final static String SKIP = "Skip";
	
	private ArrayList<String> words;
	
	private JButton add;
	private JButton skip;	
	
	private InterfaceEditor editor;
	
	public PopUpMenu (InterfaceEditor editor, Point p, ArrayList<String> words)
	{
		super();
		this.words = words;
		this.editor = editor;
		setLocation(p);
		setLayout(new BorderLayout());
		
		JPanel options = new JPanel();
		int n = words.size();
		if(n>5)
			n = 5;
		options.setLayout(new GridLayout(n, 1));
		for (int i = 0; i < n; i++) 
		{
			JButton temp = new JButton(words.get(i));
			temp.addActionListener(this);
			temp.setBackground(new Color(238,238, 238));
			options.add(temp);
		}
		if(n == 0)
		{
			options.setLayout(new GridLayout(1, 1));
			JButton temp = new JButton("No spelling suggestions");
			temp.setEnabled(false);
			temp.setBackground(new Color(238,238, 238));
			options.add(temp);
		}
		
		JPanel buttons = new JPanel(new GridLayout(2, 1));
		
		add = new JButton("Add word", new ImageIcon("img/add.png"));
		add.addActionListener(this);
		
		skip = new JButton("Skip", new ImageIcon("img/omit.png"));
		skip.addActionListener(this);
		
		buttons.add(add);
		buttons.add(skip);
		
		this.add(options, BorderLayout.CENTER);
		this.add(buttons, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String cmd = e.getActionCommand();
		if(!cmd.equalsIgnoreCase("Add word")  && !cmd.equalsIgnoreCase("Skip"))
			editor.changeWord(cmd);
		setVisible(false);
	}
	
}
