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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

public class ClientDemo extends JFrame implements Observer, ActionListener {

  private static final Logger LOGGER = Logger.getLogger(ClientDemo.class.getName());
  private final Subscriber subscriber;
  private final ExecutorService service;
  private JTextPane textArea = new JTextPane();
  private JButton buttonConnect = new JButton("connect");
  StyledDocument doc = textArea.getStyledDocument();

  JScrollPane scrollPane = new JScrollPane(textArea);

  public ClientDemo() {

    service = Executors.newCachedThreadPool();
    subscriber = new Subscriber("localhost", 1594);
   
    setLayout(new BorderLayout());
    add(scrollPane, BorderLayout.CENTER);  
    add(buttonConnect, BorderLayout.SOUTH);  
    buttonConnect.addActionListener(this);
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        shutdown();
        System.exit(0);
      }
    });
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
