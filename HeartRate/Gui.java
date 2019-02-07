package HeartRate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Core.Publisher;

/*
 * This class creates the GUI for Heart rate simulator
 *
 * @version 20190202
 *
 */

public class Gui extends JPanel implements ActionListener {

	private static Gui instance = null;
	private static Model model;

	private int PORT = 1594;

	private JTextField portText = new JTextField("1594");
	private JTextField freqText = new JTextField("1");

	private final JButton buttonConnect = new JButton("run");
	private final JRadioButton resting = new JRadioButton("Resting", true);
	private final JRadioButton moderate = new JRadioButton("Moderate Exercise");
	private final JRadioButton vigorous = new JRadioButton("Vigorous Exercise");
	private final JPanel gifPanel = new JPanel();
	private final JTextPane textPane = new JTextPane();

	public static Gui getInstance()
	{
		if (instance == null)
			instance = new Gui();

		return instance;
	}

	private Gui() {

		model = new Model(new HRDataGenerator(), new Publisher(PORT));
		this.setBackground(Color.WHITE);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(createNorthComponent());
		this.add(createButtonGroups());
		initializeHeartGif();
		this.add(gifPanel);
		this.add(createConsole());
		this.add(createPanelSouth());

		Dimension screen = getToolkit().getScreenSize();
		this.setSize(screen.width / 2, 3 * screen.height / 6);
		this.setLocation((screen.width - getSize().width) / 2, (screen.height - getSize().height) / 2);
	}

	private Component createNorthComponent() {
		JPanel top = new JPanel(new GridLayout(2, 2));
		top.add(new JLabel("Enter Port Number: "));
		top.add(portText);
		top.add(new JLabel("Enter Frequency: "));
		top.add(freqText);

		portText.setEnabled(false);

		return top;
	}

	private Component createButtonGroups() {
		JPanel buttons = new JPanel();
		ButtonGroup hrControlGroup = new ButtonGroup();

		hrControlGroup.add(resting);
		hrControlGroup.add(moderate);
		hrControlGroup.add(vigorous);

		buttons.add(resting);
		buttons.add(moderate);
		buttons.add(vigorous);
		resting.addActionListener(this);
		moderate.addActionListener(this);
		vigorous.addActionListener(this);

		return buttons;
	}

	private Component createConsole() {
		JPanel panel = new JPanel(new GridLayout());

		textPane.setFont(new Font("Tahoma", Font.BOLD, 12));
		textPane.setEnabled(false);
		textPane.setEditable(true);
		textPane.setPreferredSize(new Dimension(200, 150));

		JScrollPane scrollPane = new JScrollPane(textPane);
		panel.add(scrollPane);

		return panel;
	}
	
	private Component createPanelSouth() {
		JPanel panel = new JPanel(new GridLayout());
		panel.add(buttonConnect);

		buttonConnect.addActionListener(this);
		buttonConnect.setEnabled(true);
		buttonConnect.setBackground(Color.GREEN);

		return panel;
	}

	private void initializeHeartGif() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource("static.png"));
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(ii);
		gifPanel.add(imageLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int selectedAction;

		if (e.getSource() == buttonConnect) {
			if (buttonConnect.getText().compareTo("run") == 0) {
				if (isFrequencyValid()) {
					textPane.setText("SERVER IS RUNNING...");
					selectedAction = checkButtonSelected();
					model.setFrequency(Double.parseDouble(freqText.getText()));
					model.start();
					model.setHeartState(selectedAction);
					freqText.setEnabled(false);
					buttonConnect.setText("stop");
					buttonConnect.setBackground(Color.RED);
				} else {
					JOptionPane.showMessageDialog(new JPanel(), "Frequency entered is not valid", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} else if (buttonConnect.getText().compareTo("stop") == 0) {
				model.stop();
				buttonConnect.setText("run");
				buttonConnect.setBackground(Color.GREEN);
				freqText.setEnabled(true);
				textPane.setBackground(Color.WHITE);
				ImageIcon ii = new ImageIcon(this.getClass().getResource("static.png"));
				JLabel imageLabel = new JLabel();
				imageLabel.setIcon(ii);
				gifPanel.removeAll();
				gifPanel.add(imageLabel);
				this.getParent().revalidate();
				this.getParent().repaint();
			}

			if(model.getServerState()) {
				if (checkButtonSelected()==0)
					updateHeartGif("slow_heart.gif");
				else if (checkButtonSelected()==1)
					updateHeartGif("normal_heart.gif");
				else if (checkButtonSelected()==2)
					updateHeartGif("fast_heart.gif");
				model.setHeartState(checkButtonSelected());
			}
		}
		
		if(model.getServerState()) {
			if (e.getSource() == resting ) {
				updateHeartGif("slow_heart.gif");
				model.setHeartState(0);
			} else if (e.getSource() == moderate) {
				updateHeartGif("normal_heart.gif");
				model.setHeartState(1);
			} else if (e.getSource() == vigorous) {
				updateHeartGif("fast_heart.gif");
				model.setHeartState(2);
			}
		}
	}

	private void updateHeartGif(String image) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(image));
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(ii);
		gifPanel.removeAll();
		gifPanel.add(imageLabel);
		this.getParent().revalidate();
		this.getParent().repaint();
	}

	private int checkButtonSelected(){
		if(resting.isSelected())
			return 0;
		else if(moderate.isSelected())
			return 1;
		else
			return 2;
	}

	private boolean isFrequencyValid() {
		try {
			return Double.parseDouble(freqText.getText()) > 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public void setTextPane(String data) {
		String currentText = textPane.getText();
		textPane.setText(currentText + "\n" + data);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Heart Rate Simulator");
		frame.getContentPane().setLayout(new GridLayout(1, 1));
		frame.setLayout(new GridLayout(1, 1));
		frame.getContentPane().add(Gui.getInstance());
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				model.shutdown();
				System.exit(0);
			}
		});
		frame.pack();
		frame.setPreferredSize(new Dimension(400, 400));
		frame.setVisible(true);
	}
}
