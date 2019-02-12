package Core;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import View.Affective;

import java.awt.FlowLayout;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewGui extends JFrame implements ActionListener{

	private JPanel contentPane;
	private static Model model;

	private final int PORT = 1594;
	protected JLabel labelPublishPort;
	private DataGenerator dataGenerator =new DataGenerator();

	JButton btnNewButton = new JButton("");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGui frame = new NewGui();
					frame.setVisible(true);
					frame.addWindowListener(new java.awt.event.WindowAdapter() {
						@Override
						public void windowClosing(java.awt.event.WindowEvent e) {
							model.shutdown();
							System.exit(0);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public NewGui() throws IOException {

		model = new Model(dataGenerator, new Publisher(PORT)); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1410, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.BLACK);
		topPanel.setBounds(0, 0, 1388, 75); 
		contentPane.add(topPanel);
		topPanel.setLayout(null);

		btnNewButton.setToolTipText("Start");
		btnNewButton.addActionListener(this);

		btnNewButton.setIcon(new ImageIcon(NewGui.class.getResource("/Core/download.png")));
		btnNewButton.setBounds(1313, 0, 75, 75);
		topPanel.add(btnNewButton);

		JPanel brainImagePanel = new JPanel(); 
		brainImagePanel.setBackground(Color.WHITE);
		brainImagePanel.setBounds(0, 75, 690, 725);
		contentPane.add(brainImagePanel);
		brainImagePanel.setLayout(null);

		JLabel ch1 = new JLabel("1");
		ch1.setBounds(117, 101, 43, 20);
		brainImagePanel.add(ch1);

		JLabel ch2Label = new JLabel("2");
		ch2Label.setBounds(380, 101, 33, 20);
		brainImagePanel.add(ch2Label);

		JLabel ch3Label = new JLabel("3");
		ch3Label.setBounds(80, 167, 69, 20);
		brainImagePanel.add(ch3Label);

		JLabel ch4Label = new JLabel("4");
		ch4Label.setBounds(429, 167, 69, 20);
		brainImagePanel.add(ch4Label);

		JLabel ch5Label = new JLabel("5");
		ch5Label.setBounds(46, 241, 69, 20);
		brainImagePanel.add(ch5Label);

		JLabel ch6Label = new JLabel("6");
		ch6Label.setBounds(451, 241, 99, 20);
		brainImagePanel.add(ch6Label);

		JLabel ch7Label = new JLabel("7");
		ch7Label.setBounds(36, 314, 69, 20);
		brainImagePanel.add(ch7Label);

		JLabel label_4 = new JLabel("8");
		label_4.setBounds(470, 314, 69, 20);
		brainImagePanel.add(label_4);

		JLabel label = new JLabel("9");
		label.setBounds(36, 350, 69, 20);
		brainImagePanel.add(label);

		JLabel label_1 = new JLabel("11");
		label_1.setBounds(46, 432, 69, 20);
		brainImagePanel.add(label_1);

		JLabel label_2 = new JLabel("13");
		label_2.setBounds(80, 497, 69, 20);
		brainImagePanel.add(label_2);

		JLabel label_3 = new JLabel("10");
		label_3.setBounds(480, 350, 99, 20);
		brainImagePanel.add(label_3);

		JLabel label_5 = new JLabel("12");
		label_5.setBounds(451, 432, 69, 20);
		brainImagePanel.add(label_5);

		JLabel label_6 = new JLabel("14");
		label_6.setBounds(451, 497, 69, 20);
		brainImagePanel.add(label_6);

		JLabel brainLabel = new JLabel("");
		brainLabel.setBounds(36, 0, 580, 606);
		brainImagePanel.add(brainLabel);
		brainLabel.setIcon(new ImageIcon(NewGui.class.getResource("/Core/brain.jpg")));

		View.Expressions expressivePanel=new View.Expressions();
		expressivePanel.setBounds(780, 78, 564, 317);
		getContentPane().add(expressivePanel);
		expressivePanel.setLayout(null);

		Affective affectivePanel = new Affective(dataGenerator);
		affectivePanel.setSize(564, 317);
		affectivePanel.setLocation(780, 411);
		getContentPane().add(affectivePanel);


	}



	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("listener trigger");
		if (e.getSource() == btnNewButton) {
			if (btnNewButton.getToolTipText().compareTo("Start") == 0) {
				btnNewButton.setToolTipText("Stop");
				btnNewButton.setIcon(new ImageIcon(NewGui.class.getResource("/Core/stop.png")));
				model.start();
			} else{
				System.out.println("stop");
				model.stop();
				btnNewButton.setToolTipText("Start");
				btnNewButton.setIcon(new ImageIcon(NewGui.class.getResource("/Core/download.png")));	

			}
		}
	}
}