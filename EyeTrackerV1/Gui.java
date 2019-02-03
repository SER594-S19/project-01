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
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTextPane;


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
    panel.setBounds(0, 319, 444, 29);
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
    setLayout(null);
    this.add(createPanelSouth());
    Dimension screen = getToolkit().getScreenSize();
    this.setSize(548, 454);
    this.setLocation((screen.width - getSize().width) / 2, (screen.height - getSize().height) / 2);
    
    JSlider slider = new JSlider();
    slider.setBounds(16, 17, 313, 21);
    add(slider);
    
    JSlider slider_1 = new JSlider();
    slider_1.setBounds(16, 50, 313, 21);
    add(slider_1);
    
    JSlider slider_2 = new JSlider();
    slider_2.setBounds(16, 83, 313, 21);
    add(slider_2);
    
    JSlider slider_3 = new JSlider();
    slider_3.setBounds(16, 116, 313, 21);
    add(slider_3);
    
    JSlider slider_4 = new JSlider();
    slider_4.setBounds(16, 149, 313, 21);
    add(slider_4);
    
    JSlider slider_5 = new JSlider();
    slider_5.setBounds(16, 182, 313, 21);
    add(slider_5);
    
    JSlider slider_6 = new JSlider();
    slider_6.setBounds(16, 215, 313, 21);
    add(slider_6);
    
    JSlider slider_7 = new JSlider();
    slider_7.setBounds(16, 248, 313, 21);
    add(slider_7);
    
    
    JLabel label = new JLabel(   );
    label.setBounds(341, 17, 173, 16);
    label.setText("50");
    add(label);
    
    JLabel label_1 = new JLabel();
    label_1.setText("50");
    label_1.setBounds(341, 50, 173, 16);
    add(label_1);
    
    JLabel label_2 = new JLabel();
    label_2.setText("50");
    label_2.setBounds(341, 83, 173, 16);
    add(label_2);
    
    JLabel label_3 = new JLabel();
    label_3.setText("50");
    label_3.setBounds(341, 116, 173, 16);
    add(label_3);
    
    JLabel label_4 = new JLabel();
    label_4.setText("50");
    label_4.setBounds(341, 149, 173, 16);
    add(label_4);
    
    JLabel label_5 = new JLabel();
    label_5.setText("50");
    label_5.setBounds(341, 182, 173, 16);
    add(label_5);
    
    JLabel label_6 = new JLabel();
    label_6.setText("50");
    label_6.setBounds(341, 215, 173, 16);
    add(label_6);
    
    JLabel label_7 = new JLabel();
    label_7.setText("50");
    label_7.setBounds(341, 248, 173, 16);
    add(label_7);

    

    
	slider.addChangeListener(new ChangeListener() {
	public void stateChanged(ChangeEvent e) {
		
		//System.out.println("Value1: " + ((JSlider)e.getSource()).getValue());
		label.setText(String.valueOf(((JSlider)e.getSource()).getValue()));
		

		}
	}); 
	
	slider_1.addChangeListener(new ChangeListener() {
	public void stateChanged(ChangeEvent e1) {
		
		//System.out.println("Value1: " + ((JSlider)e.getSource()).getValue());
		label_1.setText(String.valueOf(((JSlider)e1.getSource()).getValue()));
		

		}
	}); 
	
	slider_2.addChangeListener(new ChangeListener() {
	public void stateChanged(ChangeEvent e2) {
		
		//System.out.println("Value1: " + ((JSlider)e.getSource()).getValue());
		label_2.setText(String.valueOf(((JSlider)e2.getSource()).getValue()));
		

		}
	}); 
	
	slider_3.addChangeListener(new ChangeListener() {
	public void stateChanged(ChangeEvent e3) {
		
		//System.out.println("Value1: " + ((JSlider)e.getSource()).getValue());
		label_3.setText(String.valueOf(((JSlider)e3.getSource()).getValue()));
		

		}
	}); 
	
	
	slider_4.addChangeListener(new ChangeListener() {
	public void stateChanged(ChangeEvent e4) {
		
		//System.out.println("Value1: " + ((JSlider)e.getSource()).getValue());
		label_4.setText(String.valueOf(((JSlider)e4.getSource()).getValue()));
		

		}
	}); 
	
	slider_5.addChangeListener(new ChangeListener() {
	public void stateChanged(ChangeEvent e5) {
		
		//System.out.println("Value1: " + ((JSlider)e.getSource()).getValue());
		label_5.setText(String.valueOf(((JSlider)e5.getSource()).getValue()));
		

		}
	}); 
	
	slider_6.addChangeListener(new ChangeListener() {
	public void stateChanged(ChangeEvent e6) {
		
		//System.out.println("Value1: " + ((JSlider)e.getSource()).getValue());
		label_6.setText(String.valueOf(((JSlider)e6.getSource()).getValue()));
		

		}
	}); 
	
	slider_7.addChangeListener(new ChangeListener() {
	public void stateChanged(ChangeEvent e7) {
		
		//System.out.println("Value1: " + ((JSlider)e.getSource()).getValue());
		label_7.setText(String.valueOf(((JSlider)e7.getSource()).getValue()));
		

		}
	}); 	
	
	
	
    
    
/*
	JFrame.setDefaultLookAndFeelDecorated(true);
	JFrame frame = new JFrame("JSlider setting examples");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	frame.pack();
	frame.setVisible(true);	
    *////
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
    
    /*
    
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JPanel panel = new JPanel();	
	JSlider slider = new JSlider(-100, 100, 50);
	
	
	
	panel.add(slider);
	frame.getContentPane().add(panel);
	
	slider.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
		
			System.out.println("Value1: " + ((JSlider)e.getSource()).getValue());
		}
	});
	////*/
	
    frame.pack();
    frame.setSize(500, 400);
    frame.setVisible(true);
  }
}