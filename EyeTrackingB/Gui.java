import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

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
    static  Data data;
    //private Scroll window = new Scroll();
    private Eye leftEye = new Eye(350 - 120, 200, 100, 20);
    private Eye rightEye = new Eye(350 + 120, 200, 100, 20);

    private Font font = new Font("Arial", Font.PLAIN, 50);
    private Font font2 = new Font("Arial", Font.PLAIN, 150);
    static JSlider slider_1 = new JSlider();



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
        slider_1.setToolTipText("Move me");
        slider_1.setForeground(Color.WHITE);
        slider_1.setBackground(Color.PINK);
        slider_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        slider_1.setPaintLabels(true);
        slider_1.addChangeListener(new ChangeListener(){ public void stateChanged(ChangeEvent e) {
            scroll = slider_1.getValue(); }});
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
                frame.setBounds(50, 50, 700, 500);
                frame.setBackground(Color.YELLOW);
                frame.getContentPane().add(slider_1, BorderLayout.SOUTH);

                gui.requestFocus();
            }

        });
    }
}
