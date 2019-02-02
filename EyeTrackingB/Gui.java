
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**This runs the GUI simulator for Eye Tracking Device
 *  The simulator shows movement of eyes and validation scroller value from 0 - 4
 * @author Bharat Goel
 */
public class Gui extends JPanel {
    
    private Scale mousePosition = new Scale(0, 0);
    
    private Eye leftEye = new Eye(400 - 120, 200, 100, 20);
    private Eye rightEye = new Eye(400 + 120, 200, 100, 20);
    
    private Font font = new Font("Arial", Font.PLAIN, 50);
    private Font font2 = new Font("Arial", Font.PLAIN, 150);
    
    public Gui() {
        setPreferredSize(new Dimension(800, 600));
        addMouseMotionListener(new MouseHandler());
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
            repaint();
        }
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
                gui.requestFocus();
            }

        });
    }
    
}
