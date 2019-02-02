package EyeTrackerV1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Core.Publisher;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Gui extends JPanel implements ActionListener {

  private static Model model;

  private final int PORT = 1594;
  protected JLabel labelPublishPort;
  private final JButton buttonConnect = new JButton("run");

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

  public Gui() {

    model = new Model(new DataGenerator(), new Publisher(PORT));
    this.setBackground(Color.WHITE);
    this.setLayout(new BorderLayout());
    this.add(createPanelSouth(), BorderLayout.SOUTH);
    Dimension screen = getToolkit().getScreenSize();
    this.setSize(444, 348);
    this.setLocation((screen.width - getSize().width) / 2, (screen.height - getSize().height) / 2);
////
	JFrame.setDefaultLookAndFeelDecorated(true);
	JFrame frame = new JFrame("JSlider setting examples");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	frame.pack();
	frame.setVisible(true);	
    ////
    System.out.println("gui done");
    
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("listener trigger");
    if (e.getSource() == buttonConnect) {
      if (buttonConnect.getText().compareTo("run") == 0) {
            System.out.println("start");
        model.start();
        
        buttonConnect.setText("stop");
      } else if (buttonConnect.getText().compareTo("stop") == 0) {
                    System.out.println("stop");
        model.stop();
        buttonConnect.setText("run");
      }
    }
  }
  static int value;
  public static void main(String[] args) {

    JFrame frame = new JFrame("Simulator");
    frame.getContentPane().setLayout(new GridLayout(1, 1));
    frame.getContentPane().add(new Gui());
    frame.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        model.shutdown();
        System.exit(0);
      }
    });
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JPanel panel = new JPanel();	
	JSlider slider = new JSlider(-100, 100, 50);
	
	
	
	panel.add(slider);
	frame.add(panel);
	
	slider.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
		
			System.out.println("Value1: " + ((JSlider)e.getSource()).getValue());
		}
	});
    frame.pack();
    frame.setSize(500, 300);
    frame.setVisible(true);
  }
  
}
