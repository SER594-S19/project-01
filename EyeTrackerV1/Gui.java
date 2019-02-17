package EyeTrackerV1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Gui extends JPanel implements ActionListener,MouseListener
{

	  private static final long serialVersionUID = 1L; 	
	  private static Model model;
	  protected HashMap<String, Integer> eyeParameters;
	  private int radius = 50;
	  private Graphics g;
	  private int r1 = 17;
	  private int r2 = 17;
	  
	  private final int PORT = 1596;
	  protected JLabel labelPublishPort;
	  private final JButton buttonConnect = new JButton("run");
	  private JPanel sliderPanel_1;
	  private static Dimension screen ;
	  
	  /**
	   * Constructor
	   */
	   public Gui() 
	   {
		   screen = getToolkit().getScreenSize();
		   model = new Model(new DataGenerator(), new Publisher(PORT));
		   Color color = UIManager.getColor ( "JSlider.background" );

		   this.setBackground(color);
		   this.setLayout(new BorderLayout());
		   this.add(createPanelSouth(), BorderLayout.SOUTH);
		   Dimension screen = getToolkit().getScreenSize();
		   this.setSize(screen.width / 2, 3 * screen.height / 4);
		   this.setLocation((screen.width - getSize().width) / 2, (screen.height - getSize().height) / 2);
		   
		   sliderPanel_1 = new JPanel()
		   {    	
		       public void paintComponent(Graphics g) 
		       {
		           super.paintComponent(g);
		            int r = 50;
		            
		            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		            int centerX = screenSize.width/2;
		            int centerY = screenSize.height/2;
		            int lefteyecenterX = centerX-100;
		            int lefteyecenterY = centerY-100;
		            int righteyecenterX = centerX+100;
		            int righteyecenterY = centerY-100;
		            g.setColor(Color.BLACK);
		            g.drawOval(lefteyecenterX-r, lefteyecenterY-r, 2*r, 2*r-(int)(0.5*r));
		            g.fillOval(lefteyecenterX-r1, lefteyecenterY-r1, 2*r1, 2*r1-(int)(0.5*r1));
		            
		            g.drawOval(righteyecenterX-r, righteyecenterY-r, 2*r, 2*r-(int)(0.5*r));
		            g.fillOval(righteyecenterX-r2, righteyecenterY-r2, 2*r2, 2*r2-(int)(0.5*r2));
		        }
		   };

	    createSliderPanel(sliderPanel_1, "pupilLeft", 2, 8, 5);
	    createSliderPanel(sliderPanel_1, "pupilRight", 2, 8, 5);
	    createSliderPanel(sliderPanel_1, "validityL", 0, 4, 2);
	    createSliderPanel(sliderPanel_1, "validityR", 0, 4, 2);
	    createSliderPanel(sliderPanel_1, "fixationValue", 0, 50, 25);
	    createSliderPanel(sliderPanel_1, "event", 1, 4, 2);
	    createSliderPanel(sliderPanel_1, "aoi", 1, 4, 2);
	    System.out.println("gui done");
	  }
	   
	  public int getR1() 
	  {
		  	return r1;
	  }

	  public void setR1(int r1) 
	  {
			this.r1 = r1;
			sliderPanel_1.repaint();
	  }

	  public int getR2() 
	  {
		    return r2;	
	  }

	  public void setR2(int r2) 
	  {
			this.r2 = r2;
			//call paint
			sliderPanel_1.repaint();
	  }

	public HashMap<String, Integer> getEyeParameters()
	{
		return eyeParameters;
	}
	
	public void setEyeParameters(HashMap<String, Integer> eyeParameters) 
	{
		this.eyeParameters = eyeParameters;
	}

  /**
   * This helper method creates the South panel at the south most direction, 
   * providing information like what port it is connected to.e.t.c 
   * @return
   */

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
  
	/**
	 * This is a helper method that will create the various slider panels based on the provided input parameters.
	 * @param sliderPanel
	 * @param s
	 * @param min
	 * @param max
	 * @param mid
	 */
	private void createSliderPanel(JPanel sliderPanel, String s, double min, double max, double mid) 
	{
		JSlider slider = new JSlider(JSlider.HORIZONTAL, (int)min*100, (int)max*100, (int)mid*100);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(2000);
		slider.setMinorTickSpacing(500);
	    Enumeration e = slider.getLabelTable().keys();
	    while (e.hasMoreElements()) 
	    {
	        Integer i = (Integer) e.nextElement();
	        JLabel label = (JLabel) slider.getLabelTable().get(i);
	        label.setText(String.valueOf(Integer.parseInt(label.getText())/100));          
	    }
	
	    JPanel panel = createSlider(slider,  s);
	    sliderPanel.add(panel);
	    this.add(sliderPanel);
	}


	public int getPupilRadius(int sliderValue) 
	{
		double percent = ((sliderValue)-2)/6.0;
		return (int)((percent*15)+10);
	}
	
	private JPanel createSlider(JSlider slider, String s) 
	{
		ChangeListener listener = new ChangeListener()
	    {
	       public void stateChanged(ChangeEvent event)
	       {
	    	  
	          JSlider source = (JSlider) event.getSource();
	          
	          if (s =="pupilLeft") {
	        	  
		   		setR1(getPupilRadius(source.getValue()/100));
	
	   	   		}
		   	   if (s =="pupilRight") {
		   		
		   		setR2(getPupilRadius(source.getValue()/100));
		   	   }
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
			  }
			  else if (buttonConnect.getText().compareTo("stop") == 0) 
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
	    frame.setLocationRelativeTo(null);
	    
	    frame.addWindowListener(new java.awt.event.WindowAdapter()
	    {
	      @Override
	      public void windowClosing(java.awt.event.WindowEvent e) 
	      {
	        model.shutdown();
	        System.exit(0);
	      }
	    });
	    frame.addMouseListener(new MouseAdapter() 
	    {
	    	public void mouseClicked(MouseEvent e) 
	    	{
	  			  if (model.getDataGenerator() != null )
	  			  {
					  model.getDataGenerator().updateParam("gpxValue", (double) e.getX());
					  model.getDataGenerator().updateParam("gpyValue", (double) e.getY());
	  			  }
	    	}
	    });
	    frame.pack();
	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    frame.setVisible(true);	 
	  }


	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub	
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseEntered(MouseEvent e) 
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub
	}
}
