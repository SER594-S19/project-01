package Core;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Calendar;
import java.awt.event.*;
import java.text.DecimalFormat;

/**This runs the GUI simulator for Eye Tracking Device
 *  The simulator shows movement of eyes and validation scroller value from 0 - 4
 * @author Bharat Goel
 */
public class Gui extends JPanel implements ActionListener {

  private static Model model;

  private final int PORT = 1594;
  protected JLabel labelPublishPort;
  private final JButton buttonConnect = new JButton("run");

  private Scale mousePosition = new Scale(0, 0);
  private int scroll;
  private float rscroll;
  private float lscroll;
  static Data data;
  //private Scroll window = new Scroll();
  private Eye leftEye = new Eye(350 - 120, 300, 100, 20);
  private Eye rightEye = new Eye(350 + 120, 300, 100, 20);

  private Font font = new Font("Arial", Font.PLAIN, 50);
  private Font font2 = new Font("Arial", Font.PLAIN, 150);
  static JSlider slider_1 = new JSlider();
  //static JSlider slider_2 = new JSlider();
  final DecimalFormat df = new DecimalFormat("0.####");
  final static JTextField text = new JTextField(50);
  final static DoubleJSlider slider_2 = new DoubleJSlider(0, 5000, 0, 1000);
  final static DoubleJSlider slider_3 = new DoubleJSlider(0, 5000, 0, 1000);

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
    setPreferredSize(new Dimension(800, 600));
    addMouseMotionListener(new MouseHandler());
    this.add(createPanelSouth(), BorderLayout.SOUTH);
    slider_1.setMaximum(4);
    slider_1.setMajorTickSpacing(1);
    slider_1.setPaintTicks(true);
    slider_1.setValue(0);
    slider_1.setFont(new Font("Calibri", Font.BOLD, 16));
    slider_1.setToolTipText("Validating");
    slider_1.setForeground(Color.WHITE);
    slider_1.setBackground(Color.PINK);
    slider_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
    slider_1.setPaintLabels(true);
    slider_1.addChangeListener(new ChangeListener(){ public void stateChanged(ChangeEvent e) {
      scroll = slider_1.getValue(); }});
  
    slider_2.setFont(new Font("Calibri", Font.BOLD, 20));
    slider_2.setToolTipText("PUPIL RIGHT");
    slider_2.setForeground(Color.WHITE);
    slider_2.setBackground(Color.GRAY);
    slider_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
    slider_2.setPaintLabels(true);
    slider_2.setPaintTicks(true);
    slider_2.setPreferredSize(new Dimension(150,150));
    slider_2.addChangeListener(new ChangeListener(){
        @Override
        public void stateChanged(ChangeEvent e) {
            text.setText(df.format(slider_2.getScaledValue()));
            rscroll = (float) slider_2.getScaledValue();
            rightEye.setR2(rscroll*15);
            repaint();
        }
    });
    text.addKeyListener(new KeyAdapter(){
        @Override
        public void keyReleased(KeyEvent ke) {
            String typed = text.getText();
            slider_2.setValue(0);
            if(!typed.matches("\\d+(\\.\\d*)?")) {
                return;
            }
            double value = Double.parseDouble(typed)*slider_2.scale;
            slider_2.setValue((int)value);
        }
    });
    
    slider_3.setFont(new Font("Calibri", Font.BOLD, 16));
    slider_3.setToolTipText("PUPIL LEFT");
    slider_3.setForeground(Color.WHITE);
    slider_3.setBackground(Color.GRAY);
    slider_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
    slider_3.setPaintLabels(true);
    slider_3.setPaintTicks(true);
    slider_3.setPreferredSize(new Dimension(150,150));
    slider_3.addChangeListener(new ChangeListener(){
        @Override
        public void stateChanged(ChangeEvent e) {
            text.setText(df.format(slider_3.getScaledValue()));
            lscroll = (float) slider_3.getScaledValue();
            leftEye.setR2(lscroll*15);
            repaint();
        }
    });
    text.addKeyListener(new KeyAdapter(){
        @Override
        public void keyReleased(KeyEvent ke) {
            String typed = text.getText();
            slider_3.setValue(0);
            if(!typed.matches("\\d+(\\.\\d*)?")) {
                return;
            }
            double value = Double.parseDouble(typed)*slider_3.scale;
            slider_3.setValue((int)value);
        }
    });
    
  }


  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    leftEye.draw(g, mousePosition);
    rightEye.draw(g, mousePosition);

	        /*g.setFont(font);
	        g.drawString("Eye Tracking Simulator", 100, 180);
	        /*g.setFont(font2);
	        g.drawString("+1", 180, 350);
	        g.setFont(font);
	        g.drawString("subscribers :) !!!", 220, 460);*/
  }


  private class MouseHandler extends MouseAdapter {
    @Override
    public void mouseMoved(MouseEvent e) {
      mousePosition.setX(e.getX());
      mousePosition.setY(e.getY());
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.HOUR_OF_DAY, 0);
      calendar.set(Calendar.MINUTE, 0);
      calendar.set(Calendar.SECOND, 0);
      long initialTime = calendar.getTimeInMillis();
      double timeStamp = 0;
      timeStamp = (System.currentTimeMillis() - initialTime) * .001;
      data=new Data(timeStamp,e.getX(),e.getY(),scroll);
      repaint();
    }
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

  public static Data getData(){
    return data;

  }
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        Gui gui = new Gui();
        JFrame frame = new JFrame();
        frame.setTitle("Eye Tracking Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(gui);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setBounds(200, 200, 1000, 700);
        frame.setBackground(Color.YELLOW);
        frame.getContentPane().add(slider_1, BorderLayout.SOUTH);
        frame.getContentPane().add(text, BorderLayout.NORTH);
        frame.getContentPane().add(slider_2, BorderLayout.EAST);
        frame.getContentPane().add(slider_3, BorderLayout.WEST);
     
        gui.requestFocus();
      }

    });
  }
}

class DoubleJSlider extends JSlider {

    final int scale;

    public DoubleJSlider(int min, int max, int value, int scale) {
        super(min, max, value);
        this.scale = scale;
    }

    public double getScaledValue() {
        return ((double)super.getValue()) / this.scale;
    }
}