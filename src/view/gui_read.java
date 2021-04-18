package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;


import java.awt.Button;
import java.awt.Color;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.event.ActionListener;

public class gui_read implements DocumentListener {

	private JFrame frame;
	private JTextField textField;
	JTextArea textArea = new JTextArea();
	JLabel status = new JLabel("");
	

	private gui gui1 = new gui();

	final static Color HILIT_COLOR = Color.MAGENTA;
	final static Color ERROR_COLOR = Color.PINK;
	final static String CANCEL_ACTION = "cancel-search";

	final Color entryBg;
	final Highlighter hilit;
	final Highlighter.HighlightPainter painter;

	public void setVisibleGui() {
	//	gui_read window = new gui_read();
		frame.setVisible(true);
	}
	
	private void clearArea() {
		textArea.setText("");
	}

	public gui_read() {
		initialize();
		hilit = new DefaultHighlighter();
		painter = new DefaultHighlighter.DefaultHighlightPainter(HILIT_COLOR);
		textArea.setHighlighter(hilit);
		
		
		status.setFont(new Font("Tahoma", Font.PLAIN, 15));
		status.setBounds(10, 573, 440, 27);
		frame.getContentPane().add(status);

		entryBg = textField.getBackground();
		textField.getDocument().addDocumentListener(this);

		InputMap im = textField.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = textField.getActionMap();
		im.put(KeyStroke.getKeyStroke("ESCAPE"), CANCEL_ACTION);
		am.put(CANCEL_ACTION, new CancelAction());
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 774, 754);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 738, 523);
		frame.getContentPane().add(scrollPane);

		textArea.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 14));
		
		textArea.setLineWrap(true);
		textArea.setRows(5);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		
		
		
		scrollPane.setViewportView(textArea);
		textField = new JTextField();
		textField.setBounds(243, 545, 257, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton inOrderButton = new JButton("Inorder");
		inOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearArea();
				textArea.append(gui1.captureConsoleIn());
			}
		});
		inOrderButton.setBounds(10, 611, 89, 23);
		frame.getContentPane().add(inOrderButton);

		JButton preOrderButton = new JButton("Preorder");
		preOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearArea();
				textArea.append(gui1.captureConsolePre());
			}
		});
		preOrderButton.setBounds(335, 611, 89, 23);
		frame.getContentPane().add(preOrderButton);

		JButton postOrderButton = new JButton("Postorder");
		postOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearArea();
				textArea.append(gui1.captureConsolePost());
			}
		});
		postOrderButton.setBounds(642, 611, 89, 23);
		frame.getContentPane().add(postOrderButton);

		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		exitButton.setBounds(335, 681, 89, 23);
		frame.getContentPane().add(exitButton);

		JLabel lblNewLabel = new JLabel("Search:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(179, 545, 70, 17);
		frame.getContentPane().add(lblNewLabel);
		
	}

	public void search() {
		hilit.removeAllHighlights();

		String s = textField.getText();
		if (s.length() <= 0) {
			message("Nothing to search");
			return;
		}
		if (s.equals("quit")) {

		}

		String content = textArea.getText();
		int index = content.indexOf(s, 0);
		if (index >= 0) { // match found
			try {
				int end = index + s.length();
				hilit.addHighlight(index, end, painter);
				textArea.setCaretPosition(end);
				textField.setBackground(entryBg);
				message("'" + s + "' found. Press ESC to end search");
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		} else {
			textField.setBackground(ERROR_COLOR);
			message("'" + s + "' not found. Press ESC to start a new search");
		}
	}


	void message(String msg) {
		status.setText(msg);
	}

	// DocumentListener methods

	public void insertUpdate(DocumentEvent ev) {
		search();
	}

	public void removeUpdate(DocumentEvent ev) {
		search();
	}

	public void changedUpdate(DocumentEvent ev) {
	}

	class CancelAction extends AbstractAction {
		public void actionPerformed(ActionEvent ev) {
			hilit.removeAllHighlights();
			textField.setText("");
			textField.setBackground(entryBg);

		}
	}
}
