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

    panel.add(processPanel(" Signal "));
    panel.add(processPanel(" Blink "));
    panel.add(processPanel(" WinkL "), BorderLayout.AFTER_LAST_LINE);
    panel.add(processPanel(" WinkR "), BorderLayout.AFTER_LAST_LINE);
    panel.add(processPanel(" LookL "), BorderLayout.AFTER_LAST_LINE);
    panel.add(processPanel(" LookR "), BorderLayout.AFTER_LAST_LINE);
    panel.add(processPanel(" Eyebrow "), BorderLayout.AFTER_LAST_LINE);
    panel.add(processPanel(" Furrow "), BorderLayout.AFTER_LAST_LINE);
    panel.add(processPanel(" Smile "), BorderLayout.AFTER_LAST_LINE);
    panel.add(processPanel(" Clench "), BorderLayout.AFTER_LAST_LINE);
    panel.add(processPanel(" SmirkL "), BorderLayout.AFTER_LAST_LINE);
    panel.add(processPanel(" SmirkR "), BorderLayout.AFTER_LAST_LINE);
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
