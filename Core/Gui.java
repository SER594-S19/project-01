package Core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Gui extends JPanel implements ActionListener {

  private static Model model;

  private final int PORT = 1594;
  protected JLabel labelPublishPort;
  private final JButton buttonConnect = new JButton("run");
  JSlider slider;
  
  private Component createPanelNorth() {
	  JSlider slider = new JSlider(JSlider.HORIZONTAL,0,2,0);
	  slider.setMajorTickSpacing(10);
	  slider.setPaintTicks(true);
	  
	  Hashtable sliderlabels = new Hashtable();
	  sliderlabels.put(new Integer( 0 ), new JLabel("Voltage") );
	  sliderlabels.put(new Integer( 1 ), new JLabel("Conductance") );
	  sliderlabels.put(new Integer( 2 ), new JLabel("V & C") );
	  slider.setLabelTable(sliderlabels);
	  slider.setPaintLabels(true);
	  
	  slider.addChangeListener(changeListener);
	  
	  return slider;
  }

  private Component createPanelConductance() {
    JSlider slider = new JSlider(JSlider.HORIZONTAL,0,3,0);
    slider.setMajorTickSpacing(10);
    slider.setPaintTicks(true);

    Hashtable conductanceLabels = new Hashtable();
    conductanceLabels.put(new Integer( 0 ), new JLabel("0") );
    conductanceLabels.put(new Integer( 1 ), new JLabel("1") );
    conductanceLabels.put(new Integer( 2 ), new JLabel("2") );
    conductanceLabels.put(new Integer( 3 ), new JLabel("3") );

    slider.setLabelTable(conductanceLabels);
    slider.setPaintLabels(true);

    slider.addChangeListener(changeListener);

    return slider;
  }

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

  public ChangeListener changeListener = new ChangeListener() {
	  //making the slider react to changing positions
	  public void stateChanged(ChangeEvent ce){
		  slider = (JSlider) ce.getSource();
		  if(!(slider.getValue() == 0))
		  {
			  
		  }
		  if(!(slider.getValue() == 1))
		  {
			  
		  }
		  if(!(slider.getValue() == 2))
		  {
			  
		  }
	  }
  };
  
  public Gui() {

    model = new Model(new DataGenerator(), new Publisher(PORT));
    this.setBackground(Color.WHITE);
    this.setLayout(new BorderLayout());
    this.add(createPanelNorth(), BorderLayout.NORTH);
    this.add(createPanelConductance(), BorderLayout.CENTER);

    this.add(createPanelSouth(), BorderLayout.SOUTH);
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
    frame.add(new Gui());
    frame.addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        model.shutdown();
        System.exit(0);
      }
    });
    frame.pack();
    frame.setSize(500, 300);
    frame.setVisible(true);
  }
  
}
