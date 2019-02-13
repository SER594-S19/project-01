package Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class ClientDemo extends JFrame implements Observer, ActionListener {

  private final Subscriber  [] subscriber = new Subscriber[5];
  private final ExecutorService service;
  private JTextArea textArea = new JTextArea();
  private JButton buttonConnect = new JButton("connect");
  private JPanel processPanel(String lableName) {

    JPanel jLabel = new JPanel();
    jLabel.setBackground(Color.RED);
    jLabel.setLayout(new GridLayout(1,1));
    jLabel.add(new JLabel(lableName),BorderLayout.WEST);

    JPanel jPanel = new JPanel();
    jPanel.setBackground(Color.CYAN);
    jPanel.setLayout(new GridLayout(1,2));
    JTextField ipAdd = new JTextField();
    jPanel.add(new JLabel("IP Address" ));
    jPanel.add(ipAdd);

    JPanel jPort = new JPanel();
    jPort.setBackground(Color.CYAN);
    jPort.setLayout(new GridLayout(1,2));
    JTextField portInput = new JTextField();
    jPort.add(new JLabel("Port #" ));
    jPort.add(portInput);

    JPanel connectCondition = new JPanel();
    connectCondition.setBackground(Color.CYAN);
    connectCondition.setLayout(new GridLayout(1,3));
    JLabel condition = new JLabel("Connecting...");
    JButton connect = new JButton("Connect");
    JButton disconnect = new JButton("Disconnect");
    connect.addActionListener(this);
    
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        shutdown();
        System.exit(0);
      }
    });

    disconnect.addActionListener(this);
    
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        shutdown();
        System.exit(0);
      }
    });

    connectCondition.add(condition);
    connectCondition.add(connect);
    connectCondition.add(disconnect);

    JPanel panel = new JPanel();
    panel.setBackground(Color.CYAN);
    panel.setLayout(new GridLayout(4,1));
    panel.add(jLabel,BorderLayout.NORTH);
    panel.add(jPanel, BorderLayout.AFTER_LAST_LINE);
    panel.add(jPort, BorderLayout.AFTER_LAST_LINE);
    panel.add(connectCondition, BorderLayout.AFTER_LAST_LINE);
    panel.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
    return panel;
  }

  private JPanel ClientPanel(){
    JPanel panel = new JPanel();
    panel.setBackground(Color.CYAN);
    panel.setLayout(new GridLayout(5,1));
    panel.add(processPanel(" Face "),BorderLayout.NORTH);
    panel.add(processPanel(" Heart "),BorderLayout.AFTER_LAST_LINE);
    panel.add(processPanel(" BCI "),BorderLayout.AFTER_LAST_LINE);
    panel.add(processPanel(" Skin "),BorderLayout.AFTER_LAST_LINE);
    panel.add(processPanel(" Eye "),BorderLayout.AFTER_LAST_LINE);
    return panel;
  }
  public ClientDemo() {

    service = Executors.newCachedThreadPool();

    // TO TEST, RUN TWO SERVERS IN PORTS 1594 and 1595

    subscriber[0] = new Subscriber("localhost", 1594);
    subscriber[1] = new Subscriber("localhost", 1595);
    subscriber[2] = new Subscriber("localhost", 1596);
    subscriber[3] = new Subscriber("localhost", 1597);
    subscriber[4] = new Subscriber("localhost", 1598);

    setLayout(new GridLayout(1,2));
    add(ClientPanel(), BorderLayout.NORTH);
    add(textArea, BorderLayout.CENTER);

    buttonConnect.addActionListener(this);
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        shutdown();
        System.exit(0);
      }
    });
    setSize(600,600);
    setVisible(true);

  }

  private void close() {
    System.out.println("closing ....... +++++++");
    subscriber[0].stop();
    subscriber[1].stop();
    subscriber[2].stop();
    subscriber[3].stop();
    subscriber[4].stop();
  }

  private void shutdown() {
    subscriber[0].stop();
    subscriber[1].stop();
    subscriber[2].stop();
    subscriber[3].stop();
    subscriber[4].stop();
    service.shutdown();
    try {
      if (!service.awaitTermination(10, TimeUnit.SECONDS)) {
        service.shutdownNow();
      }
    } catch (InterruptedException ex) {
    }
  }


  @Override
  public void update(Observable o, Object arg) {
    String data = ((Subscriber) o).getObject().toString();
    if (data.compareTo("FIN") != 0)
      textArea.append(data + "\n" );
    else {
      close();
      buttonConnect.setEnabled(true);
    }
  }

  public static void main(String[] args) {
    ClientDemo tester = new ClientDemo();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    buttonConnect.setEnabled(false);
    service.submit(subscriber[0]);
    subscriber[0].addObserver(this);

    service.submit(subscriber[1]);
    subscriber[1].addObserver(this);
    
    service.submit(subscriber[2]);
    subscriber[2].addObserver(this);
    
    service.submit(subscriber[3]);
    subscriber[3].addObserver(this);
    
    service.submit(subscriber[4]);
    subscriber[4].addObserver(this);
    
  }
}
