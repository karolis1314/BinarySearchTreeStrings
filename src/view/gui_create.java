package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import controller.MovieTree;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class gui_create {

	private JFrame frame;
	private JTextField movieTxt;
	private JTextField actorTxt;
	private JTextField ratingTxt;
	private JTextField picTxt;
	private JTextField trailerTxt;
	JLabel titleLabel = new JLabel("Insert New Movie");

	public void visible() {
		frame.setVisible(true);
	}
	private String capitalizeMovieName(String s) {
		String s1 = s.substring(0, 1).toUpperCase();
		return s1 + s.substring(1);
	}
	private void clear() {
		movieTxt.setText("");
		actorTxt.setText("");
		ratingTxt.setText("");
		picTxt.setText("");
		trailerTxt.setText("");
		titleLabel.setText("Insert New Movie");
	}


	/**
	 * Create the application.
	 */
	public gui_create() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 459, 405);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

	
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
		titleLabel.setBounds(63, 11, 309, 41);
		frame.getContentPane().add(titleLabel);

		JLabel lblNewLabel_1 = new JLabel("Movie name: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(10, 99, 107, 22);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Lead Actor: ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(10, 132, 107, 22);
		frame.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Rating: ");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_2.setBounds(10, 165, 107, 22);
		frame.getContentPane().add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Picture path: ");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_3.setBounds(10, 198, 107, 22);
		frame.getContentPane().add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Trailer Url: ");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_4.setBounds(10, 231, 107, 22);
		frame.getContentPane().add(lblNewLabel_1_4);

		movieTxt = new JTextField();
		movieTxt.setBounds(127, 103, 223, 18);
		frame.getContentPane().add(movieTxt);
		movieTxt.setColumns(10);

		actorTxt = new JTextField();
		actorTxt.setColumns(10);
		actorTxt.setBounds(127, 136, 223, 18);
		frame.getContentPane().add(actorTxt);

		ratingTxt = new JTextField();
		ratingTxt.setColumns(10);
		ratingTxt.setBounds(127, 169, 223, 18);
		frame.getContentPane().add(ratingTxt);

		picTxt = new JTextField();
		picTxt.setColumns(10);
		picTxt.setBounds(127, 202, 223, 18);
		frame.getContentPane().add(picTxt);

		trailerTxt = new JTextField();
		trailerTxt.setColumns(10);
		trailerTxt.setBounds(127, 235, 223, 18);
		frame.getContentPane().add(trailerTxt);

		JButton btnNewButton = new JButton("Insert");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (movieTxt.getText().equals("") || actorTxt.getText().equals("") || ratingTxt.getText().equals("")
						|| picTxt.getText().equals("") || trailerTxt.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "One of the input fields are empty", "Warning",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					
					String movieName = capitalizeMovieName(movieTxt.getText());
					int rating = Integer.parseInt(ratingTxt.getText());
					String pic = "C:\\\\Users\\\\karol\\\\Desktop\\\\assigment one pictures\\\\"+picTxt.getText()+".jpg";
					gui.insertMovie(movieName, actorTxt.getText(), rating, pic, trailerTxt.getText());
					titleLabel.setText("Inserted!");
				}
			}
		});
		btnNewButton.setBounds(82, 299, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClear.setBounds(194, 299, 89, 23);
		frame.getContentPane().add(btnClear);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(movieTxt.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "Movie Name must be entered", "Warning",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					String movieName = capitalizeMovieName(movieTxt.getText());
					gui.deleteMovie(movieName);
					titleLabel.setText("Deleted!");
				}
			}
		});
		btnDelete.setBounds(308, 299, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		JButton btnClose = new JButton("Close");
		btnClose.setForeground(Color.RED);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnClose.setBounds(194, 333, 89, 23);
		frame.getContentPane().add(btnClose);
	}
}
