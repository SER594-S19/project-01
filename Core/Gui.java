package Core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Calendar;

import java.util.Observable;
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
  private JPanel sliderPanel;
  protected JLabel labelPublishPort;
  private final JButton buttonConnect = new JButton("run");
  //JSlider slider;
  JSlider voltage, conductance;

    private int index;

//  private Component createSliders() {
//
//      sliderPanel= new JPanel();
//      sliderPanel.setLayout(new GridLayout(6,1));
//      sliderPanel.setMinimumSize(new Dimension(600, 400));
//
//      voltage.setMajorTickSpacing(10);
//      //voltage.setMinorTickSpacing(1);
//      voltage.setPaintTicks(true);
//      voltage.setPaintLabels(true);
//
//      conductance.setMajorTickSpacing(10);
//      //conductance.setMinorTickSpacing(1);
//      conductance.setPaintTicks(true);
//      conductance.setPaintLabels(true);
//
//      addSlider(voltage, "voltage", "voltage");
//      addSlider(conductance, "conductance", "conductance");
//
//
//      return sliderPanel;
//
//  }

    private Component createPanelConductance() {
        conductance = new JSlider(JSlider.HORIZONTAL,0,3,0);
        conductance.setMajorTickSpacing(10);
        conductance.setPaintTicks(true);

        Hashtable conductanceLabels = new Hashtable();
        conductanceLabels.put(new Integer( 0 ), new JLabel("0") );
        conductanceLabels.put(new Integer( 1 ), new JLabel("1") );
        conductanceLabels.put(new Integer( 2 ), new JLabel("2") );
        conductanceLabels.put(new Integer( 3 ), new JLabel("3") );

        conductance.setLabelTable(conductanceLabels);
        conductance.setPaintLabels(true);

        conductance.addChangeListener(changeListener);

        return conductance;
    }

    private Component createPanelVoltage() {
        voltage = new JSlider(JSlider.HORIZONTAL,0,3,0);
        voltage.setMajorTickSpacing(10);
        voltage.setPaintTicks(true);

        Hashtable voltageLabels = new Hashtable();
        voltageLabels.put(new Integer( 0 ), new JLabel("0") );
        voltageLabels.put(new Integer( 1 ), new JLabel("1") );
        voltageLabels.put(new Integer( 2 ), new JLabel("2") );
        voltageLabels.put(new Integer( 3 ), new JLabel("3") );

        voltage.setLabelTable(voltageLabels);
        voltage.setPaintLabels(true);

        voltage.addChangeListener(changeListener);

        return voltage;
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


//    public void addSlider(JSlider s, String descrip, String dataParam)
//    {
//         ChangeListener listener = new ChangeListener(){
//            public void stateChanged(ChangeEvent event)
//            {
//                // update text field when the slider value changes
//                //DataGenerator dataGen = new DataGenerator(voltage.getValue(), conductance.getValue());
////
//
//                JSlider source = (JSlider) event.getSource();
//                model.updatePara(descrip, voltage.getValue());
//
//                System.out.println(source.getValue());
//            }
//        };
//
//        s.addChangeListener(listener);
//        JPanel panel = new JPanel();
//        panel.add(new JLabel(descrip));
//        panel.add(s);
//        sliderPanel.add(panel);
//    }

  public ChangeListener changeListener = new ChangeListener() {
	  //making the slider react to changing positions
	  public void stateChanged(ChangeEvent event){
          //DataGenerator dataGen = new DataGenerator();
          voltage = (JSlider) event.getSource();


          //JSlider source = (JSlider) event.getSource();
		  if (!voltage.getValueIsAdjusting())
		  {
              model.updatePara("voltage", voltage.getValue());
			  System.out.println(voltage.getValue());
              //dataGen.updateVoltage((double) voltage.getValue());
              //index = voltage.getValue();
//			  if(!(voltage.getValue() == 0))
//			  {
//
//			  }
//			  if(!(voltage.getValue() == 1))
//			  {
//
//			  }
//			  if(!(voltage.getValue() == 2))
//			  {
//
//			  }
		  }

          conductance= (JSlider) event.getSource();
          if (!voltage.getValueIsAdjusting())
          {
              model.updatePara("conductance", voltage.getValue());
              System.out.println(conductance.getValue());
          }

	  }
  };

//    public void dataGen(){
//        Boolean stop = false;
//        double timeStamp=0;
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        long initialTime = calendar.getTimeInMillis();
//
//
//        while (!stop) {
//            System.out.println("data generator running");
//            timeStamp = (System.currentTimeMillis() - initialTime) * .001;
//            System.out.println("Data{" + "time=" + timeStamp + ", voltage=" + voltage.getValue() + ", conductance=" + conductance.getValue() + "}");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//            }
//        }
//    }
  
  public Gui() {

    model = new Model(new DataGenerator(), new Publisher(PORT));
    this.setBackground(Color.WHITE);
    this.setLayout(new BorderLayout());
    //this.add(voltage, BorderLayout.NORTH);
    this.add(createPanelVoltage(), BorderLayout.NORTH);
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
            //dataGen();
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
    frame.setSize(600, 600);
    frame.setVisible(true);
  }
  
}
