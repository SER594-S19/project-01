package EyeTrackerV1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Core.Publisher;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Gui extends JPanel implements ActionListener,MouseListener
{

  private static final long serialVersionUID = 1L; 	
  private static Model model;
  protected HashMap<String, Integer> eyeParameters;
  

  public HashMap<String, Integer> getEyeParameters()
  {
	return eyeParameters;
  }

public void setEyeParameters(HashMap<String, Integer> eyeParameters) 
{
	this.eyeParameters = eyeParameters;
}

  private final int PORT = 1594;
  protected JLabel labelPublishPort;
  private final JButton buttonConnect = new JButton("run");
  private JPanel sliderPanel_1;

  private Component createPanelSouth()
  {
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

  public Gui() 
  {
    model = new Model(new DataGenerator(), new Publisher(PORT));
    this.setBackground(Color.WHITE);
    this.setLayout(new BorderLayout());
    this.add(createPanelSouth(), BorderLayout.SOUTH);
    Dimension screen = getToolkit().getScreenSize();
    this.setSize(screen.width / 2, 3 * screen.height / 4);
    this.setLocation((screen.width - getSize().width) / 2, (screen.height - getSize().height) / 2);
    sliderPanel_1 = new JPanel();
   
    createSliderPanel(sliderPanel_1, "pupilLeft", 2, 8, 5);
    createSliderPanel(sliderPanel_1, "pupilRight", 2, 8, 5);
   
    createSliderPanel(sliderPanel_1, "validityL", 0, 4, 2);
    createSliderPanel(sliderPanel_1, "validityR", 0, 4, 2);
    createSliderPanel(sliderPanel_1, "fixationValue", 0, 50, 25);
    createSliderPanel(sliderPanel_1, "event", 1, 4, 2);
    createSliderPanel(sliderPanel_1, "aoi", 1, 4, 2);
    
    
    
    System.out.println("gui done");
  }


private void createSliderPanel(JPanel sliderPanel, String s, double min, double max, double mid) 
{
	JSlider slider = new JSlider(JSlider.HORIZONTAL, (int)min*100, (int)max*100, (int)mid*100);
	slider.setPaintTicks(true);
	slider.setPaintLabels(true);
	slider.setMajorTickSpacing(2000);
	slider.setMinorTickSpacing(500);
    Enumeration e = slider.getLabelTable().keys();
    while (e.hasMoreElements()) {
        Integer i = (Integer) e.nextElement();
        JLabel label = (JLabel) slider.getLabelTable().get(i);
        label.setText(String.valueOf(Integer.parseInt(label.getText())/100));          
    }

    JPanel panel = createSlider(slider,  s);
    sliderPanel.add(panel);
    this.add(sliderPanel);
    }

private JPanel createSlider(JSlider slider, String s) 
{
	ChangeListener listener = new ChangeListener()
    {
       public void stateChanged(ChangeEvent event)
       {
          // update text field when the slider value changes
    	   
    	   
    	   
          JSlider source = (JSlider) event.getSource();
          model.getDataGenerator().updateParam(s, source.getValue()/100.0);
          
          
       }
    };
    slider.addChangeListener(listener);
    JPanel panel = new JPanel();
    panel.add(slider);
    panel.add(new JLabel(s));
	return panel;
}

  @Override
  public void actionPerformed(ActionEvent e) 
  {
    System.out.println("listener trigger");
    if (e.getSource() == buttonConnect) 
    {
      if (buttonConnect.getText().compareTo("run") == 0)
      {
            System.out.println("start");
        model.start();
        buttonConnect.setText("stop");
      } else if (buttonConnect.getText().compareTo("stop") == 0) 
      {
                    System.out.println("stop");
        model.stop();
        buttonConnect.setText("run");
      }
    }
  }
  
  public static void main(String[] args) 
  {

    JFrame frame = new JFrame("Eye Tracker Simulator");
    frame.getContentPane().setLayout(new GridLayout(1, 1));
    frame.getContentPane().add(new Gui());
    
    frame.addWindowListener(new java.awt.event.WindowAdapter()
    {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) 
      {
        model.shutdown();
        System.exit(0);
      }
    });
    frame.addMouseListener(new MouseAdapter() {
        //override the method
	  public void mouseClicked(MouseEvent e) {
	  	// TODO Auto-generated method stub
		  
		
		  if (model.getDataGenerator() != null )
		  {
			  model.getDataGenerator().updateParam("gpxValue", (double) e.getX());
			  model.getDataGenerator().updateParam("gpYValue", (double) e.getY());
		  }
	  }
    });
    frame.pack();
    frame.setSize(500, 300);
    frame.setVisible(true);
  }

@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub

	
}

@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
  
}