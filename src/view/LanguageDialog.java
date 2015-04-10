package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class LanguageDialog extends JDialog implements ActionListener
{
	
	public final static String ACCEPT = "accept";
	public final static String CANCEL = "cancel";
	
	private JButton accept;
	private JButton cancel;
	private JComboBox<String> list;
	
	private InterfaceEditor editor;
	
	public LanguageDialog (InterfaceEditor editor)
	{
		super();
		this.editor = editor;
		
		setTitle("Language");
		setSize(200, 100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(editor);
		setResizable(false);
		setLayout(new GridLayout(3, 1));
		
		list = new JComboBox(editor.getLanguages().toArray());
		list.setSelectedIndex(editor.getLanguage());
		
		JPanel buttons = new JPanel(new GridLayout(1,2));
		
		accept = new JButton("Accept");
		accept.setActionCommand(ACCEPT);
		accept.addActionListener(this);
		
		cancel = new JButton("Cancel");
		cancel.setActionCommand(CANCEL);
		cancel.addActionListener(this);
		
		buttons.add(accept);
		buttons.add(cancel);
		
		add(new JLabel("Select the Language"));
		add(list);
		add(buttons);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String cmd = e.getActionCommand();
		if(cmd.equalsIgnoreCase(ACCEPT))
		{
			int language = list.getSelectedIndex();
			editor.changeLanguage(language);
		}
		dispose();			
	}
	
	
}
