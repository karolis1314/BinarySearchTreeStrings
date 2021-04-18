package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.MovieTree;
import model.Node;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class gui {

	private JFrame frame;
	private JTextField txtEnterMovieName;
	JLabel labelForThePicture = new JLabel("");
	static MovieTree tree = new MovieTree();
	JLabel movieLabel = new JLabel("Movie:");
	JLabel leadActorLabel = new JLabel("Lead Actor: ");
	JLabel ratingLabel = new JLabel("Rating:");
	String s = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String captureConsoleIn() {
		ByteArrayOutputStream test = new ByteArrayOutputStream();
		PrintStream PS = new PrintStream(test);
		PrintStream old = System.out;
		System.setOut(PS);
		tree.traverseInOrder();
		return test.toString();
	}

	public String captureConsolePost() {
		ByteArrayOutputStream test = new ByteArrayOutputStream();
		PrintStream PS = new PrintStream(test);
		PrintStream old = System.out;
		System.setOut(PS);
		tree.traversePostOrder();
		return test.toString();
	}

	public String captureConsolePre() {
		ByteArrayOutputStream test = new ByteArrayOutputStream();
		PrintStream PS = new PrintStream(test);
		PrintStream old = System.out;
		System.setOut(PS);
		tree.traversePreOrder();
		return test.toString();
	}

	public void dataInit() {
		tree.insert("Catch me if you can", "Leonardo Dicaprio", 8,
				"C:\\Users\\karol\\Desktop\\assigment one pictures\\catchMeIfYouCan.jpg",
				"https://www.youtube.com/watch?v=s-7pyIxz8Qg&ab_channel=MovieclipsClassicTrailers");
		tree.insert("Harry potter", "Daniel Radcliffe", 9,
				"C:\\Users\\karol\\Desktop\\assigment one pictures\\harryPotter.jpg",
				"https://www.youtube.com/watch?v=cGvbeaq7kK4&ab_channel=ChristopherCherigo");
		tree.insert("Armagedon", "Bruce Willis", 6, "C:\\Users\\karol\\Desktop\\assigment one pictures\\armagedon.jpg",
				"https://www.youtube.com/watch?v=kg_jH47u480&ab_channel=TrailersPlaygroundHD");
		tree.insert("Inception", "Leonardo Dicaprio", 10,
				"C:\\Users\\karol\\Desktop\\assigment one pictures\\inception.jpg",
				"https://www.youtube.com/watch?v=YoHD9XEInc0&ab_channel=MovieclipsClassicTrailers");
		tree.insert("Warrior", "Tom Hardy", 9, "C:\\Users\\karol\\Desktop\\assigment one pictures\\warrior.jpg",
				"https://www.youtube.com/watch?v=I5kzcwcQA1Q&ab_channel=MovieclipsTrailers");
		// tree.traverseInOrder();
		// s = captureConsole();
	}

	public static void insertMovie(String name, String actor, int rating, String pic, String trailer) {
		tree.insert(name, actor, rating, pic, trailer);
	}
	public static void deleteMovie(String name) {
		tree.delete(name);
	}


	private void reset() {
		movieLabel.setText("Movie: ");
		leadActorLabel.setText("Lead Actor: ");
		ratingLabel.setText("Rating: ");
		labelForThePicture.setIcon(new ImageIcon("C:\\Users\\karol\\Desktop\\assigment one pictures\\title.jpg"));
	}

	private String capitalizeMovieName(String s) {
		String s1 = s.substring(0, 1).toUpperCase();
		return s1 + s.substring(1);
	}

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
		dataInit();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 255));
		frame.getContentPane().setLayout(null);

		JLabel moveSearchLabel = new JLabel("Movie search engine");
		moveSearchLabel.setBackground(new Color(255, 255, 255));
		moveSearchLabel.setFont(new Font("Stencil", Font.PLAIN, 50));
		moveSearchLabel.setBounds(213, 11, 659, 65);
		frame.getContentPane().add(moveSearchLabel);

		JPanel pictureDisplayWindow = new JPanel();
		pictureDisplayWindow.setBackground(new Color(255, 204, 255));
		pictureDisplayWindow.setBounds(212, 128, 606, 437);
		frame.getContentPane().add(pictureDisplayWindow);

		labelForThePicture.setIcon(new ImageIcon("C:\\Users\\karol\\Desktop\\assigment one pictures\\title.jpg"));
		pictureDisplayWindow.add(labelForThePicture);

		txtEnterMovieName = new JTextField();
		txtEnterMovieName.setFont(new Font("Tahoma", Font.PLAIN, 28));
		txtEnterMovieName.setText("Enter Movie Name Here...");
		txtEnterMovieName.setBounds(328, 576, 382, 43);
		frame.getContentPane().add(txtEnterMovieName);
		txtEnterMovieName.setColumns(10);
		txtEnterMovieName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterMovieName.setText("");
			}
		});

		JLabel lblNewLabel = new JLabel("Movie: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(261, 585, 57, 20);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Node temp = tree.get(capitalizeMovieName(txtEnterMovieName.getText()));
				if (temp == null) {
					txtEnterMovieName.setText("No movie found!");
					reset();
				} else {

					movieLabel.setText("Movie: " + temp.getMovieNameAsKey());
					leadActorLabel.setText("Lead Actor: " + temp.getLeadActor());
					ratingLabel.setText("Rating: " + temp.getRating());
					labelForThePicture.setIcon(new ImageIcon(temp.getMoviePicUrl()));
				}

			}
		});
		btnNewButton.setBackground(new Color(204, 204, 51));
		btnNewButton.setBounds(399, 657, 214, 54);
		frame.getContentPane().add(btnNewButton);

		movieLabel.setFont(movieLabel.getFont().deriveFont(movieLabel.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		movieLabel.setBounds(828, 158, 243, 43);
		frame.getContentPane().add(movieLabel);

		leadActorLabel.setFont(
				leadActorLabel.getFont().deriveFont(leadActorLabel.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		leadActorLabel.setBounds(828, 212, 243, 43);
		frame.getContentPane().add(leadActorLabel);

		ratingLabel
				.setFont(ratingLabel.getFont().deriveFont(ratingLabel.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		ratingLabel.setBounds(828, 266, 243, 43);
		frame.getContentPane().add(ratingLabel);

		JButton btnNewButton_1 = new JButton("Look");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gui_read newGui2 = new gui_read();
				newGui2.setVisibleGui();

			}
		});
		btnNewButton_1.setBackground(new Color(102, 204, 0));
		btnNewButton_1.setBounds(953, 362, 89, 23);
		frame.getContentPane().add(btnNewButton_1);

		JLabel seeMoviesLabel = new JLabel("See all Movies-->");
		seeMoviesLabel.setFont(
				seeMoviesLabel.getFont().deriveFont(seeMoviesLabel.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		seeMoviesLabel.setBounds(828, 352, 115, 43);
		frame.getContentPane().add(seeMoviesLabel);

		JLabel lblGetSize = new JLabel("Get size-->");
		lblGetSize.setFont(lblGetSize.getFont().deriveFont(lblGetSize.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		lblGetSize.setBounds(828, 401, 115, 43);
		frame.getContentPane().add(lblGetSize);

		JButton btnNewButton_1_1 = new JButton("Get");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Size of the tree --> " + tree.getSize(), "Size of the tree",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_1_1.setBackground(new Color(102, 204, 0));
		btnNewButton_1_1.setBounds(953, 411, 89, 23);
		frame.getContentPane().add(btnNewButton_1_1);

		JLabel lblShowHeight = new JLabel("Show height-->");
		lblShowHeight.setFont(
				lblShowHeight.getFont().deriveFont(lblShowHeight.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		lblShowHeight.setBounds(828, 450, 115, 43);
		frame.getContentPane().add(lblShowHeight);

		JButton btnNewButton_1_1_1 = new JButton("Show");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Size of the tree --> " + tree.getHeight(), "Size of the tree",
						JOptionPane.INFORMATION_MESSAGE);

			}
		});
		btnNewButton_1_1_1.setBackground(new Color(102, 204, 0));
		btnNewButton_1_1_1.setBounds(953, 460, 89, 23);
		frame.getContentPane().add(btnNewButton_1_1_1);

		JButton btnNewButton_1_1_1_1 = new JButton("Open");
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				gui_create window = new gui_create();
				window.visible();
			}
		});
		btnNewButton_1_1_1_1.setBackground(new Color(102, 204, 0));
		btnNewButton_1_1_1_1.setBounds(953, 502, 89, 23);
		frame.getContentPane().add(btnNewButton_1_1_1_1);

		JLabel lblInsertdelete = new JLabel("Insert/Delete-->");
		lblInsertdelete.setFont(
				lblInsertdelete.getFont().deriveFont(lblInsertdelete.getFont().getStyle() | Font.BOLD | Font.ITALIC));
		lblInsertdelete.setBounds(828, 491, 115, 43);
		frame.getContentPane().add(lblInsertdelete);
		frame.setBounds(100, 100, 1097, 795);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
