package Client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.GridLayout;
import java.awt.Color;

public class ClientDemo extends JFrame implements Observer, ActionListener {

  private static final Logger LOGGER = Logger.getLogger(ClientDemo.class.getName());
  private final Subscriber subscriber;
  private final ExecutorService service;
  private JTextPane textArea = new JTextPane();
  private JButton buttonConnect = new JButton("connect");
  StyledDocument doc = textArea.getStyledDocument();

  JScrollPane scrollPane = new JScrollPane(textArea);

  private JPanel processPanel(String lableName) {

    JPanel label = new JPanel();
    label.setLayout(new GridLayout(1,1));
    label.add(new JLabel(lableName),BorderLayout.WEST);

    JPanel ip = new JPanel();
    ip.setLayout(new GridLayout(1,2));
    JTextField ipInput = new JTextField();
    ip.add(new JLabel("IP" ));
    ip.add(ipInput);

    JPanel port = new JPanel();
    port.setLayout(new GridLayout(1,2));
    JTextField portInput = new JTextField();
    port.add(new JLabel("Port" ));
    port.add(portInput);

    JPanel connectCondition = new JPanel();
    connectCondition.setLayout(new GridLayout(1,3));
    JLabel condition = new JLabel("Connecting...");
    JButton connect = new JButton("Connect");
    JButton disConnect = new JButton("DisConnect");
    connect.addActionListener(this);
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        shutdown();
        System.exit(0);
      }
    });

    disConnect.addActionListener(this);
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        shutdown();
        System.exit(0);
      }
    });

    connectCondition.add(condition);
    connectCondition.add(connect);
    connectCondition.add(disConnect);


    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(4,1));
    panel.add(label,BorderLayout.NORTH);
    panel.add(ip, BorderLayout.AFTER_LAST_LINE);
    panel.add(port, BorderLayout.AFTER_LAST_LINE);
    panel.add(connectCondition, BorderLayout.AFTER_LAST_LINE);
    panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

    return panel;
  }

  private JPanel ClientPanel(){
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(5,1));
    panel.add(processPanel(" BCI "),BorderLayout.NORTH);
    panel.add(processPanel(" Face "),BorderLayout.AFTER_LAST_LINE);
    panel.add(processPanel(" Heart "),BorderLayout.AFTER_LAST_LINE);
    panel.add(processPanel(" Skin "),BorderLayout.AFTER_LAST_LINE);
    panel.add(processPanel(" Eye "),BorderLayout.AFTER_LAST_LINE);
    return panel;
  }

  public ClientDemo() {

    service = Executors.newCachedThreadPool();
    subscriber = new Subscriber("localhost", 1594);
    setLayout(new GridLayout(1,2));
    //setLayout(new BorderLayout());
    add(ClientPanel(), BorderLayout.NORTH);

    add(scrollPane, BorderLayout.CENTER);
    /*
    add(buttonConnect, BorderLayout.SOUTH);  
    buttonConnect.addActionListener(this);
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        shutdown();
        System.exit(0);
      }
    });
    */
    setSize(500,500);
    setVisible(true);
    
  }

  private void close() {
    System.out.println("clossing ....... +++++++");
    subscriber.stop();
  }
  
    private void shutdown() {
    subscriber.stop();
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
    if (data.compareTo("FIN") != 0) {
    	try
    	{
    	    doc.insertString(doc.getLength(), data + "\n", null);
    	} catch(Exception e) { 
    		LOGGER.log(Level.SEVERE, "Exception while writing in client", e);
    	}
    } else {
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
    service.submit(subscriber);
    subscriber.addObserver(this); 
    
  }
}
