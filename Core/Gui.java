package Core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

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

  private JPanel processPanel(String lableName) {
      
        JPanel label = new JPanel();
        label.add(new JLabel(lableName));
        JPanel panel = new JPanel(new BorderLayout());
        panel.setLayout(new GridLayout(1,2));
        JTextField signal = new JTextField();
        panel.add(label, BorderLayout.WEST);
        panel.add(signal);
        
        return panel;
  }

  private Component createPanelInput(){
    JPanel panel = new JPanel(new BorderLayout());
    panel.setLayout(new GridLayout(14,1));
    //panel.setBounds(10,10,100,100);


    JPanel labels = new JPanel();
    //labels.setBackground(Color.GRAY);
    labels.add(new JLabel(" ID "));
    JPanel panelId = new JPanel(new BorderLayout());
    panelId.setLayout(new GridLayout(1,2));
    panelId.setBackground(Color.CYAN);
    JTextField id = new JTextField();
    labels.setSize(200,100);
    panelId.add(labels, BorderLayout.WEST);
    panelId.add(id);
    panel.add(panelId, BorderLayout.NORTH);


    /**JPanel labelSig = new JPanel();
    //labelSig.setBackground(Color.GRAY);
    labelSig.add(new JLabel(" Signal "));
    JPanel panelSig = new JPanel(new BorderLayout());
    panelSig.setLayout(new GridLayout(1,2));
    JTextField signal = new JTextField();
    panelSig.add(labelSig, BorderLayout.WEST);
    panelSig.add(signal);*/
    panel.add(processPanel(" Signal "));

    /**JPanel labelBlink = new JPanel();
    //labelBlink.setBackground(Color.GRAY);
    labelBlink.add(new JLabel(" Blink "));
    JPanel panelBlink = new JPanel(new BorderLayout());
    panelBlink.setLayout(new GridLayout(1,2));
    //panelId.setBackground(Color.CYAN);
    JTextField blink = new JTextField();
    panelBlink.add(labelBlink, BorderLayout.WEST);
    panelBlink.add(blink);*/
    panel.add(processPanel(" Blink "));


    /**JPanel labelWinkL  = new JPanel();
    //labelWinkL.setBackground(Color.GRAY);
    labelWinkL.add(new JLabel(" WinkL "));
    JPanel panelWinkL = new JPanel(new BorderLayout());
    panelWinkL.setLayout(new GridLayout(1,2));
    //panelId.setBackground(Color.CYAN);
    JTextField WinkL = new JTextField();
    panelWinkL.add(labelWinkL, BorderLayout.WEST);
    panelWinkL.add(WinkL);*/
    panel.add(processPanel(" WinkL "), BorderLayout.AFTER_LAST_LINE);


    /**JPanel labelWinkR  = new JPanel();
    //labelWinkR.setBackground(Color.GRAY);
    labelWinkR.add(new JLabel(" WinkR "));
    JPanel panelWinkR = new JPanel(new BorderLayout());
    panelWinkR.setLayout(new GridLayout(1,2));
    //panelId.setBackground(Color.CYAN);
    JTextField WinkR = new JTextField();
    panelWinkR.add(labelWinkR, BorderLayout.WEST);
    panelWinkR.add(WinkR);*/
    panel.add(processPanel(" WinkR "), BorderLayout.AFTER_LAST_LINE);

    /**JPanel labelLookL  = new JPanel();
    //labelLookL.setBackground(Color.GRAY);
    labelLookL.add(new JLabel(" LookL "));
    JPanel panelLookL = new JPanel(new BorderLayout());
    panelLookL.setLayout(new GridLayout(1,2));
    //panelId.setBackground(Color.CYAN);
    JTextField LookL= new JTextField();
    panelLookL.add(labelLookL, BorderLayout.WEST);
    panelLookL.add(LookL);*/
    panel.add(processPanel(" LookL "), BorderLayout.AFTER_LAST_LINE);

    /**JPanel labelLookR  = new JPanel();
    //labelLookR.setBackground(Color.GRAY);
    labelLookR.add(new JLabel(" LookR "));
    JPanel panelLookR = new JPanel(new BorderLayout());
    panelLookR.setLayout(new GridLayout(1,2));
    //panelId.setBackground(Color.CYAN);
    JTextField LookR= new JTextField();
    panelLookR.add(labelLookR, BorderLayout.WEST);
    panelLookR.add(LookR);*/
    panel.add(processPanel(" LookR "), BorderLayout.AFTER_LAST_LINE);


    /**JPanel labelEyebrow   = new JPanel();
    //labelEyebrow.setBackground(Color.GRAY);
    labelEyebrow.add(new JLabel(" Eyebrow"));
    JPanel panelEyebrow = new JPanel(new BorderLayout());
    panelEyebrow.setLayout(new GridLayout(1,2));
    //panelId.setBackground(Color.CYAN);
    JTextField Eyebrow= new JTextField();
    panelEyebrow.add(labelEyebrow, BorderLayout.WEST);
    panelEyebrow.add(Eyebrow);*/
    panel.add(processPanel(" Eyebrow "), BorderLayout.AFTER_LAST_LINE);

    /**JPanel labelFurrow   = new JPanel();
    //labelFurrow.setBackground(Color.GRAY);
    labelFurrow.add(new JLabel(" Furrow"));
    JPanel panelFurrow = new JPanel(new BorderLayout());
    panelFurrow.setLayout(new GridLayout(1,2));
    //panelId.setBackground(Color.CYAN);
    JTextField Furrow= new JTextField();
    panelFurrow.add(labelFurrow, BorderLayout.WEST);
    panelFurrow.add(Furrow);*/
    panel.add(processPanel(" Furrow "), BorderLayout.AFTER_LAST_LINE);


    /**JPanel labelSmile  = new JPanel();
    //labelSmile.setBackground(Color.GRAY);
    labelSmile.add(new JLabel(" Smile"));
    JPanel panelSmile = new JPanel(new BorderLayout());
    panelSmile.setLayout(new GridLayout(1,2));
    //panelId.setBackground(Color.CYAN);
    JTextField Smile= new JTextField();
    panelSmile.add(labelSmile, BorderLayout.WEST);
    panelSmile.add(Smile);*/
    panel.add(processPanel(" Smile "), BorderLayout.AFTER_LAST_LINE);

    /**JPanel labelClench  = new JPanel();
    //labelClench.setBackground(Color.GRAY);
    labelClench.add(new JLabel(" Clench"));
    JPanel panelClench = new JPanel(new BorderLayout());
    panelClench.setLayout(new GridLayout(1,2));
    //panelId.setBackground(Color.CYAN);
    JTextField Clench= new JTextField();
    panelClench.add(labelClench, BorderLayout.WEST);
    panelClench.add(Clench);*/
    panel.add(processPanel(" Clench "), BorderLayout.AFTER_LAST_LINE);

    /**JPanel labelSmirkL  = new JPanel();
    //labelSmirkL.setBackground(Color.GRAY);
    labelSmirkL.add(new JLabel(" SmirkL"));
    JPanel panelSmirkL = new JPanel(new BorderLayout());
    panelSmirkL.setLayout(new GridLayout(1,2));
    //panelId.setBackground(Color.CYAN);
    JTextField SmirkL= new JTextField();
    panelSmirkL.add(labelSmirkL, BorderLayout.WEST);
    panelSmirkL.add(SmirkL);*/
    panel.add(processPanel(" SmirkL "), BorderLayout.AFTER_LAST_LINE);

    /**JPanel labelSmirkR  = new JPanel();
    //labelSmirkR.setBackground(Color.GRAY);
    labelSmirkR.add(new JLabel(" SmirkR"));
    JPanel panelSmirkR = new JPanel(new BorderLayout());
    panelSmirkR.setLayout(new GridLayout(1,2));
    //panelId.setBackground(Color.CYAN);
    JTextField SmirkR= new JTextField();
    panelSmirkR.add(labelSmirkR, BorderLayout.WEST);
    panelSmirkR.add(SmirkR);*/
    panel.add(processPanel(" SmirkR "), BorderLayout.AFTER_LAST_LINE);

    /**JPanel labelLaugh  = new JPanel();
    //labelLaugh.setBackground(Color.GRAY);
    labelLaugh.add(new JLabel(" Laugh"));
    JPanel panelLaugh = new JPanel(new BorderLayout());
    panelLaugh.setLayout(new GridLayout(1,2));
    //panelId.setBackground(Color.CYAN);
    JTextField Laugh= new JTextField();
    panelLaugh.add(labelLaugh, BorderLayout.WEST);
    panelLaugh.add(Laugh);*/
    panel.add(processPanel(" Laugh "), BorderLayout.AFTER_LAST_LINE);

    return panel;
  }

  public Gui() {

    model = new Model(new DataGenerator(), new Publisher(PORT));
    this.setBackground(Color.WHITE);
    this.setLayout(new BorderLayout());
    this.add(createPanelInput());
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
    frame.setSize(500, 500);
    frame.setVisible(true);
  }

}
