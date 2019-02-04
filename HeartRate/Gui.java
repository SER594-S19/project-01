package HeartRate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Core.Publisher;

public class Gui extends JPanel implements ActionListener {

	private static Model model;

	private final int PORT = 1594;
	protected JLabel labelPublishPort;
	private final JButton buttonConnect = new JButton("run");
	private final JRadioButton resting = new JRadioButton("Resting", true);
	private final JRadioButton moderate = new JRadioButton("Moderate Exercise");
	private final JRadioButton vigorous = new JRadioButton("Vigorous Exercise");
	private int selectedAction = 0;

	// private final JSplitPane splitPane;

	private JPanel gifPanel = new JPanel();

	private Component createPanelSouth() {

		JPanel labels = new JPanel();
		labels.setBackground(Color.GRAY);
		labels.add(new JLabel("  Publishing at port: "));
		labelPublishPort = new JLabel("" + PORT);
		labels.add(labelPublishPort);
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.CYAN);
		panel.add(labels, BorderLayout.WEST);
		panel.add(buttonConnect, BorderLayout.EAST);
		buttonConnect.addActionListener(this);
		buttonConnect.setEnabled(true);
		return panel;
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

	
	private void initializeHeartGif() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource("slow_heart.gif"));
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(ii);
		gifPanel.add(imageLabel);

	}

	// private Component createGroupButtons

	public Gui() {

		model = new Model(new HRDataGenerator(), new Publisher(PORT));
		this.setBackground(Color.WHITE);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(createButtonGroups());
		initializeHeartGif();
		this.add(gifPanel);
		this.add(createPanelSouth());

		Dimension screen = getToolkit().getScreenSize();
		this.setSize(screen.width / 2, 3 * screen.height / 4);
		this.setLocation((screen.width - getSize().width) / 2, (screen.height - getSize().height) / 2);
		System.out.println("gui done");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("listener trigger");
		if (e.getSource() == buttonConnect) {
			if (buttonConnect.getText().compareTo("run") == 0) {
				System.out.println("start");
				selectedAction = checkButtonSelected();
				model.start();
				model.setHeartState(selectedAction);
				buttonConnect.setText("stop");
			} else if (buttonConnect.getText().compareTo("stop") == 0) {
				System.out.println("stop");
				model.stop();
				buttonConnect.setText("run");
			}
		}
		if (e.getSource() == resting) {
			ImageIcon ii = new ImageIcon(this.getClass().getResource("slow_heart.gif"));
			JLabel imageLabel = new JLabel();
			imageLabel.setIcon(ii);
			gifPanel.removeAll();
			gifPanel.add(imageLabel);
			this.getParent().revalidate();
			this.getParent().repaint();
			selectedAction = 0;
			model.setHeartState(0);

		} else if (e.getSource() == moderate) {
			System.out.println("test");
			ImageIcon ii = new ImageIcon(this.getClass().getResource("normal_heart.gif"));
			JLabel imageLabel = new JLabel();
			imageLabel.setIcon(ii);
			this.gifPanel.removeAll();
			this.gifPanel.add(imageLabel);
			this.getParent().revalidate();
			this.getParent().repaint();
			selectedAction = 1;
			model.setHeartState(1);
		} else if (e.getSource() == vigorous) {
			ImageIcon ii = new ImageIcon(this.getClass().getResource("fast_heart.gif"));
			JLabel imageLabel = new JLabel();
			imageLabel.setIcon(ii);
			this.gifPanel.removeAll();
			this.gifPanel.add(imageLabel);
			this.getParent().revalidate();
			this.getParent().repaint();
			selectedAction = 2;
			model.setHeartState(2);
		}
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Simulator");
		frame.setLayout(new GridLayout(1, 1));
		frame.add(new Gui());
		// frame.add(new ButtonGroup());
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

	private int checkButtonSelected(){
		if(resting.isSelected() == true)
			return 0;
		else if(moderate.isSelected() == true){
			return 1;
		}
		else
			return 2;
	}

}
