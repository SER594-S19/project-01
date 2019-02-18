package EyeTrackerV1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import java.util.HashMap;

import javax.swing.BorderFactory;
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
	  private final String IP = "localhost";
	  protected JLabel labelPublishedIp;
	  private final JButton buttonConnect = new JButton("run");
	  private JPanel sliderPanel_1;
	  private static Dimension screen ;
	  
	  /**
	   * Constructor
	 * @param frame 
	   */
	   public Gui() 
	   {
		  screen = getToolkit().getScreenSize();
		   model = new Model(new DataGenerator(), new Publisher(PORT));
		   Color color = UIManager.getColor ( "JSlider.background" );
		   JPanel mainPanel = new JPanel(new GridLayout(11,1));
		   this.setLayout(new BorderLayout());
		   mainPanel.add(createRunButtonNorth(),BorderLayout.NORTH);
		   mainPanel.setBackground(Color.LIGHT_GRAY);

		
		  sliderPanel_1 = new JPanel() { 
			  public void paintComponent(Graphics g) {
		  super.paintComponent(g); 
		  int r = 50;
		  
		  Dimension screenSize = sliderPanel_1.getPreferredSize(); 
		  int centerX = screenSize.width/2; 
		  int centerY = screenSize.height/2; 
		  int lefteyecenterX = centerX-100; 
		  int lefteyecenterY = centerY-100; 
		  int righteyecenterX = centerX+100;
		  int righteyecenterY = centerY-100;
		  g.setColor(Color.BLACK);
		  g.drawOval(lefteyecenterX-r, lefteyecenterY-r, 2*r,2*r-(int)(0.5*r));  
		  g.fillOval(lefteyecenterX-r1, lefteyecenterY-r1, 2*r1,2*r1-(int)(0.5*r1));
		  g.drawOval(righteyecenterX-r, righteyecenterY-r, 2*r, 2*r-(int)(0.5*r));
		  g.fillOval(righteyecenterX-r2, righteyecenterY-r2, 2*r2, 2*r2-(int)(0.5*r2));
		  
			  } };
		 
		   
		  mainPanel.add(createSliderPanel("pupilRight", 2, 8, 5));
		  mainPanel.add(createSliderPanel("pupilLeft", 2, 8, 5));
		  mainPanel.add(createSliderPanel("validityL", 0, 4, 2));
		  mainPanel.add(createSliderPanel("validityR", 0, 4, 2));
		  
		   
		   JPanel eyePanel1 = new JPanel(new GridLayout(1,1));
		   eyePanel1.setBackground(Color.WHITE);
		   //eyePanel.add(sliderPanel_1);
		   mainPanel.add(eyePanel1);
		   JPanel eyePanel2 = new JPanel(new GridLayout(1,1));
		   eyePanel2.setBackground(Color.WHITE );
		   mainPanel.add(eyePanel2);
		   

		   mainPanel.add(createSliderPanel("fixationValue", 0, 50, 25), BorderLayout.NORTH); 
		   mainPanel.add(createSliderPanel("event", 1, 4, 2), BorderLayout.AFTER_LAST_LINE); 
		   mainPanel.add(createSliderPanel("aoi", 1, 4, 2), BorderLayout.AFTER_LAST_LINE);
		   

		   mainPanel.add(createPanelSouth(), BorderLayout.SOUTH);
		   

		   
		   this.add(mainPanel);
		   Dimension screen = getToolkit().getScreenSize();
		   this.setSize(screen.width / 2, 3 * screen.height / 4);
		   this.setLocation((screen.width - getSize().width) / 2, (screen.height - getSize().height) / 2);

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
	  JPanel panel = new JPanel(new GridLayout(1, 2)); 
	  panel.setBackground(Color.LIGHT_GRAY); 
	  
	  JPanel labelPanel = new JPanel(new BorderLayout());
	  labelPanel.setBackground(new Color(219,219,219));
	  JLabel label = new JLabel("Click to start the simulator...");
	  label.setHorizontalAlignment(JLabel.CENTER);
	  label.setVerticalAlignment(JLabel.CENTER);
	  labelPanel.add(label, BorderLayout.CENTER);
	  labelPanel.setBackground(new Color(219,219,219));
	  panel.add(labelPanel);
	  panel.setBackground(new Color(219,219,219));
	  panel.add(buttonConnect,BorderLayout.CENTER); 
	  buttonConnect.addActionListener(this);
	  buttonConnect.setEnabled(true); 
	  panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
	  return panel;
  }
  
	
	  private Component createRunButtonNorth() { 
		  JPanel labelPanel = new JPanel(new GridLayout(2,1));
		  //labelPanel.setBackground(new Color(234,241,249));
		  JPanel port = new JPanel();
		    port.add(new JLabel("  Publishing at port: "));
		    labelPublishPort = new JLabel("" + PORT);
		    port.setBackground(new Color(234,241,249));
		    port.add(labelPublishPort);
		   JPanel ip = new JPanel();
		   ip.add(new JLabel("  Publishing at IP: "));
		   labelPublishedIp = new JLabel("" + IP);
		   ip.add(labelPublishedIp);
		   ip.setBackground(new Color(234,241,249));
		    labelPanel.add(port, BorderLayout.CENTER);
		    labelPanel.add(ip, BorderLayout.CENTER);
		    labelPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		    return labelPanel;
	  }

  
	private Component createSliderPanel(String s, double min, double max, double mid) 
	{
		JPanel panel = new JPanel(new GridLayout(1,3));
	    
		JSlider slider = new JSlider(JSlider.HORIZONTAL, (int)min*100, (int)max*100, (int)mid*100);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		 if (s == "fixationValue") {
		    	slider.setMajorTickSpacing(1000);
				slider.setMinorTickSpacing(500);
		    }
		 else {
			 slider.setMajorTickSpacing(100);
				slider.setMinorTickSpacing(50);
		 }
		
		slider.setSnapToTicks(true);
	    Enumeration e = slider.getLabelTable().keys();
	    while (e.hasMoreElements()) 
	    {
	        Integer i = (Integer) e.nextElement();
	        JLabel labelname = (JLabel) slider.getLabelTable().get(i);
	        String g = labelname.getText();
	        int ii = Integer.parseInt(g)/100;
	        if (ii == 0) {
	        	String di = String.valueOf(ii);
	        	labelname.setText(String.valueOf(Integer.parseInt(labelname.getText())/100));
	        }
	        else {
	        	 String di = String.valueOf(ii);
	 	        labelname.setHorizontalTextPosition(JLabel.CENTER);
	 	        labelname.setText(String.valueOf(Integer.parseInt(labelname.getText())/100)+"  ");
	        }
	       
	        
	    }
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

	    JPanel sliderPanel = new JPanel();
	    sliderPanel.setBackground(new Color(234,241,249));
	    JPanel labelPanel = new JPanel(new BorderLayout());
	    labelPanel.setBackground(new Color(234,241,249));
	    JPanel detailPanel = new JPanel();
	    detailPanel.setBackground(new Color(234,241,249));
		  JLabel label = new JLabel(s);
		  label.setHorizontalAlignment(JLabel.CENTER);
		  label.setVerticalAlignment(JLabel.CENTER);
		  labelPanel.add(label, BorderLayout.CENTER);
		  panel.add(labelPanel);
	    
	    sliderPanel.add(slider, BorderLayout.CENTER);
	    panel.add(sliderPanel);
	    
	    JLabel labelquestion = new JLabel("?");
		  labelquestion.setHorizontalAlignment(JLabel.CENTER);
		  labelquestion.setVerticalAlignment(JLabel.CENTER);
		  labelquestion.setForeground(Color.BLACK);
		  detailPanel.add(labelquestion, BorderLayout.CENTER);
	    panel.add(detailPanel, BorderLayout.CENTER);
	    
	    
	    return panel;
	
	}


	public int getPupilRadius(int sliderValue) 
	{
		double percent = ((sliderValue)-2)/6.0;
		return (int)((percent*15)+10);
	}
	
	/*
	 * private JPanel addListener(JSlider slider, String s) { ChangeListener
	 * listener = new ChangeListener() { public void stateChanged(ChangeEvent event)
	 * {
	 * 
	 * JSlider source = (JSlider) event.getSource();
	 * 
	 * if (s =="pupilLeft") {
	 * 
	 * setR1(getPupilRadius(source.getValue()/100));
	 * 
	 * } if (s =="pupilRight") {
	 * 
	 * setR2(getPupilRadius(source.getValue()/100)); }
	 * model.getDataGenerator().updateParam(s, source.getValue()/100.0); } };
	 * slider.addChangeListener(listener); JPanel panel = new JPanel();
	 * panel.add(slider); panel.add(new JLabel(s)); return panel; }
	 */

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
	    frame.getContentPane().setLayout(new GridLayout(1,1));	    
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
	    //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    frame.setSize(600, 800);
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
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub
	}
}
