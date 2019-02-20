package clientEyeTrackerV1;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import javax.swing.*;

import com.sun.glass.events.KeyEvent;


public class ClientDemo extends JFrame implements Observer, ActionListener {

  private final Subscriber[] subscriber = new Subscriber[5];
  private final ExecutorService service;
  private JTextArea eyeTextArea = new JTextArea("");
  private JTextArea faceTextArea = new JTextArea("");
  private JTextArea heartTextArea = new JTextArea("");
  private JTextArea BCITextArea = new JTextArea("");
  private JTextArea skinTextArea = new JTextArea("");
   
  private JButton buttonConnect = new JButton("connect");
  
  private JTextField portInputFace = new JTextField("9999");
  private JTextField portInputHeart = new JTextField("9999");
  private JTextField portInputBCI = new JTextField("9999");
  private JTextField portInputSkin = new JTextField("9999");
  private JTextField portInputEye = new JTextField("9999");
  
  
  private JTextField ipInputFace = new JTextField("localhost");
  private JTextField ipInputHeart = new JTextField("localhost");
  private JTextField ipInputBCI = new JTextField("localhost");
  private JTextField ipInputSkin = new JTextField("localhost");
  private JTextField ipInputEye = new JTextField("localhost");
  
  private int portNumFace = 1595;
  private int portNumBCI = 1599;
  private int portNumHeart = 1597;
  private int portNumSkin = 1598;
  private int portNumEye = 1596;
  
  
  private JPanel processPanel(String labelName) {

	    JPanel label = new JPanel();
	    label.setBackground(Color.lightGray);
	    label.setLayout(new GridLayout(1,1));
	    label.add(new JLabel(labelName),BorderLayout.WEST);

	    JPanel port = new JPanel();
	    port.setBackground(Color.orange);
	    port.setLayout(new GridLayout(1,2));
	    port.add(new JLabel("Enter Port Num" ));
	    
	    JPanel ip = new JPanel();
	    ip.setBackground(Color.orange);
	    ip.setLayout(new GridLayout(1,2));
	    ip.add(new JLabel("Enter the IP Address" ));
	    
	    if(labelName == " Face Simulator ") {
	    	port.add(portInputFace);
	    	ip.add(ipInputFace);
	    }
	    	
	    if(labelName == " Heart Simulator ") {
	    	port.add(portInputHeart);
	    	ip.add(ipInputHeart);
	    }
	    	
		if(labelName == " BCI Simulator ") {
			port.add(portInputBCI);
			ip.add(ipInputBCI);

		}
	    if(labelName == " Skin Simulator ") {
	    	port.add(portInputSkin);
	    	ip.add(ipInputSkin);
	    }
	    	
		if(labelName == " Eye Simulator ") {
			port.add(portInputEye);
			ip.add(ipInputEye);
		}


	    JPanel connectionButtons = new JPanel();
	    connectionButtons.setBackground(Color.CYAN);
	    connectionButtons.setLayout(new GridLayout(1,3));
	    
	    JButton connectButton = new JButton("Connect");
	    connectButton.addActionListener(this);
	    connectionButtons.add(connectButton);
	    
	    JButton disconnectButton = new JButton("Disconnect");
	    disconnectButton.addActionListener(new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		    	  close();
	            }
		    });
	    	    
	    connectionButtons.add(disconnectButton);

	      
	    addWindowListener(new java.awt.event.WindowAdapter() {
			  @Override 
			  public void windowClosing(java.awt.event.WindowEvent e) {
			  shutdown(); 
			  System.exit(0); 
			  } 
			  });
	    
		  addWindowListener(new java.awt.event.WindowAdapter() {
		  @Override 
		  public void windowClosing(java.awt.event.WindowEvent e) {
		  shutdown(); 
		  System.exit(0); 
		  } 
		  });
		 
	    
	    JPanel panel = new JPanel();
	    panel.setBackground(Color.CYAN);
	    panel.setLayout(new GridLayout(4,1));
	    panel.add(label,BorderLayout.NORTH);
	    panel.add(ip, BorderLayout.AFTER_LAST_LINE);
	    panel.add(port, BorderLayout.AFTER_LAST_LINE);
	    panel.add(connectionButtons, BorderLayout.AFTER_LAST_LINE);
	    panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
	    return panel;
	  }

	  private JPanel ClientPanel(){
	    JPanel panel = new JPanel();
	    panel.setBackground(Color.CYAN);
	    panel.setLayout(new GridLayout(5,1));
	    panel.add(processPanel(" Face Simulator "),BorderLayout.NORTH);
	    panel.add(processPanel(" Heart Simulator "),BorderLayout.AFTER_LAST_LINE);
	    panel.add(processPanel(" BCI Simulator "),BorderLayout.AFTER_LAST_LINE);
	    panel.add(processPanel(" Skin Simulator "),BorderLayout.AFTER_LAST_LINE);
	    panel.add(processPanel(" Eye Simulator "),BorderLayout.AFTER_LAST_LINE);
	    return panel;
	  }
	  
	  private void addIt(JTabbedPane tabbedPane, String text, JTextArea textArea) {
		    JPanel panel = new JPanel();
		    JScrollPane scroll = new JScrollPane (textArea);
		    
		    tabbedPane.addTab(text, panel);
		    
		    panel.setLayout(new BorderLayout());
		    panel.setBackground(Color.CYAN);	    
		    panel.add(scroll, BorderLayout.CENTER);
		    panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		  }
  public ClientDemo() {

    service = Executors.newCachedThreadPool();
    
    // TO TEST, RUN TWO SERVERS IN PORTS 1594 and 1595
    
    subscriber[0] = new Subscriber("localhost", 1596); //eye
//    subscriber[1] = new Subscriber("localhost", 1595);
//    subscriber[2] = new Subscriber("localhost", 1596);
//    subscriber[3] = new Subscriber("localhost", 1597);
//    subscriber[4] = new Subscriber("localhost", 1598);
    
    getContentPane().setLayout(new GridLayout(1,2));
    JTabbedPane tabbedPane = new JTabbedPane();
   tabbedPane.addTab("Settings", null, ClientPanel(), "Important Panel");
   addIt(tabbedPane, "Face Simulator", faceTextArea );
   addIt(tabbedPane, "Heart Simulator", heartTextArea);
   addIt(tabbedPane, "Brain Simulator", BCITextArea);
   addIt(tabbedPane, "Skin Simulator", skinTextArea);
   addIt(tabbedPane, "Eye Simulator", eyeTextArea);

   //tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
   
   
   getContentPane().add(tabbedPane);

    buttonConnect.addActionListener(this);
    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override
      public void windowClosing(java.awt.event.WindowEvent e) {
        shutdown();
        System.exit(0);
      }
    });
    setSize(800,800);
    setVisible(true);
    
  }

  private void close() {
    System.out.println("clossing ....... +++++++");
    subscriber[0].stop();
    //subscriber[1].stop();
    //subscriber[2].stop();
    //subscriber[3].stop();
    //subscriber[4].stop();
  }
  
    private void shutdown() {
    subscriber[0].stop();
    //subscriber[1].stop();
    //subscriber[2].stop();
    //subscriber[3].stop();
    //subscriber[4].stop();
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
    int portNum;
    String port = data.substring(data.length()-4,  data.length());
    System.out.println("Hello uhsuibcyec" + port);
    portNum = Integer.parseInt(port);
    
    System.out.println();
    if (data.compareTo("FIN") != 0 && portNum == 1596)
    	eyeTextArea.append(data + "\n" );
    else if(data.compareTo("FIN") != 0 && portNum == 1596)
    	faceTextArea.append(data + "\n" );
    else if(data.compareTo("FIN") != 0 && portNum == 1596)
    	heartTextArea.append(data + "\n" );
    else if(data.compareTo("FIN") != 0 && portNum == 1596)
    	BCITextArea.append(data + "\n" );
    else if(data.compareTo("FIN") != 0 && portNum == 1596)
    	skinTextArea.append(data + "\n" );
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
	  int portNumInputFace = Integer.parseInt(portInputFace.getText());
	  int portNumInputHeart = Integer.parseInt(portInputHeart.getText());
	  int portNumInputBCI = Integer.parseInt(portInputBCI.getText());
	  int portNumInputSkin = Integer.parseInt(portInputSkin.getText());
	  int portNumInputEye = Integer.parseInt(portInputEye.getText());
	  	  
	System.out.println("POrt number are " + portNumInputFace + portNumInputHeart);
    buttonConnect.setEnabled(false);
  
    if (portNumInputEye == 1596) {
    service.submit(subscriber[0]);
    subscriber[0].addObserver(this); 
    }
    else if (portNumInputHeart == 1595) {
    service.submit(subscriber[1]);
    subscriber[1].addObserver(this); 
  }  
    else if (portNumInputBCI == 1597) {
    service.submit(subscriber[2]);
    subscriber[2].addObserver(this);
    }
    else if (portNumInputSkin == 1598) {
    service.submit(subscriber[3]);
    subscriber[3].addObserver(this);
    }
    	
    else if (portNumInputFace == 1599) {
    service.submit(subscriber[4]);
    subscriber[4].addObserver(this);
    }
  }
}
