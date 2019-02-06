package Core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Gui1 extends JPanel implements ActionListener {

  private static Model model;

  private final int PORT = 1594;
  protected JLabel labelPublishPort;
  private final JButton buttonConnect = new JButton("run");
  private JPanel sliderPanel;
  //private JPanel labels;
  //private JPanel panel;
  JSlider agreement = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
  JSlider disagreement = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
  JSlider frustrate = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
  JSlider concentrate = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
  JSlider thinking = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
  JSlider unsure = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);

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
  
  private Component createSlider() {
	  
	sliderPanel = new JPanel();
	sliderPanel.setLayout(new GridLayout(6,1));
	sliderPanel.setMinimumSize(new Dimension(600, 300));
    //sliderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    agreement.setMajorTickSpacing(10);
	agreement.setMinorTickSpacing(1);
	agreement.setPaintTicks(true);
	agreement.setPaintLabels(true);
	
	disagreement.setMajorTickSpacing(10);
	disagreement.setMinorTickSpacing(1);
	disagreement.setPaintTicks(true);
	disagreement.setPaintLabels(true);
	
	frustrate.setMajorTickSpacing(10);
	frustrate.setMinorTickSpacing(1);
	frustrate.setPaintTicks(true);
	frustrate.setPaintLabels(true);
	
	concentrate.setMajorTickSpacing(10);
	concentrate.setMinorTickSpacing(1);
	concentrate.setPaintTicks(true);
	concentrate.setPaintLabels(true);
	
	thinking.setMajorTickSpacing(10);
	thinking.setMinorTickSpacing(1);
	thinking.setPaintTicks(true);
	thinking.setPaintLabels(true);
	
	unsure.setMajorTickSpacing(10);
	unsure.setMinorTickSpacing(1);
	unsure.setPaintTicks(true);
	unsure.setPaintLabels(true);
	

	addSlider(agreement, "Agreement", "agreement");
	addSlider(disagreement, "Disagreement", "disagreement");
	addSlider(frustrate, "Frustrate", "frustrate");
	addSlider(concentrate, "Concentrate", "concentrate");
	addSlider(thinking, "Thinking", "thinking");
	addSlider(unsure, "Unsure", "unsure");
	
	return sliderPanel;
	  
  }

  public Gui1() {

    model = new Model(new DataGenerator(), new Publisher(PORT));
    this.setBackground(Color.WHITE);
    this.setLayout(new BorderLayout());
    //this.setLayout(new GridLayout(3,2));
    this.add(createPanelSouth(), BorderLayout.SOUTH);
    this.add(createSlider(), BorderLayout.CENTER);
	
    Dimension screen = getToolkit().getScreenSize();
    this.setSize(screen.width / 2, 3 * (screen.height / 4));
    this.setLocation((screen.width - getSize().width) / 2, (screen.height - getSize().height) / 2);
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
  
  public static void main(String[] args) {

    JFrame frame = new JFrame("Simulator");
    frame.setLayout(new GridLayout(1, 1));
    frame.add(new Gui1());
    frame.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        model.shutdown();
        System.exit(0);
      }
    });
    frame.pack();
    frame.setSize(600, 600);
    frame.setVisible(true);
  }
  
  public void addSlider(JSlider s, String description, String dataParam)
  {
	  ChangeListener listener = new ChangeListener()
	    {
	       public void stateChanged(ChangeEvent event)
	       {
	          // update text field when the slider value changes
	    	   DataGenerator dataGen = new DataGenerator();
	    	   
	    	   
	          JSlider source = (JSlider) event.getSource();
	          dataGen.updateParam(dataParam, (double) source.getValue());
	       }
	    };
	    
	 s.addChangeListener(listener);
     JPanel panel = new JPanel();
     panel.add(new JLabel(description));
     panel.add(s);
     sliderPanel.add(panel);
  }
  
}