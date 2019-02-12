package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;

import controller.AffectiveController;
import Core.*;

public class Affective extends JPanel{

	
	public Affective(DataGenerator dg) {
		setLayout(null);
		
		JLabel lblAffective = new JLabel("Affective");
		lblAffective.setEnabled(false);
		lblAffective.setBounds(6, 39, 61, 16);
		add(lblAffective);
		
		JLabel lblFrustration = new JLabel("Frustration:");
		lblFrustration.setBounds(6, 95, 81, 16);
		add(lblFrustration);
		
		JLabel lblEngagement = new JLabel("Engagement:");
		lblEngagement.setBounds(6, 123, 81, 16);
		add(lblEngagement);
		
		JLabel lblMeditation = new JLabel("Meditation:");
		lblMeditation.setBounds(6, 151, 81, 16);
		add(lblMeditation);
		
		JLabel lblSTExcitement = new JLabel("Short Term Excitement: ");
		lblSTExcitement.setBounds(6, 182, 151, 16);
		add(lblSTExcitement);
		
		JLabel lblLTExcitement = new JLabel("Long Term Excitement:");
		lblLTExcitement.setBounds(6, 211, 145, 16);
		add(lblLTExcitement);
		
		JSlider sliderFrustration = new JSlider();
		sliderFrustration.setValue(0);
		sliderFrustration.setPaintTicks(true);
		sliderFrustration.setMinorTickSpacing(1);
		sliderFrustration.setBounds(203, 82, 209, 29);
		add(sliderFrustration);
		
		JSlider sliderEngagement = new JSlider();
		sliderEngagement.setValue(0);
		sliderEngagement.setPaintTicks(true);
		sliderEngagement.setMinorTickSpacing(1);
		sliderEngagement.setBounds(203, 110, 209, 29);
		add(sliderEngagement);
		
		JSlider sliderMeditation = new JSlider();
		sliderMeditation.setValue(0);
		sliderMeditation.setMinorTickSpacing(1);
		sliderMeditation.setPaintTicks(true);
		sliderMeditation.setBounds(203, 138, 209, 29);
		add(sliderMeditation);
		
		JSlider sliderSTEngagement = new JSlider();
		sliderSTEngagement.setValue(0);
		sliderSTEngagement.setPaintTicks(true);
		sliderSTEngagement.setMinorTickSpacing(1);
		sliderSTEngagement.setBounds(203, 169, 209, 29);
		add(sliderSTEngagement);
		
		JSlider sliderLTEngagement = new JSlider();
		sliderLTEngagement.setPaintTicks(true);
		sliderLTEngagement.setSnapToTicks(true);
		sliderLTEngagement.setMinorTickSpacing(1);
		sliderLTEngagement.setValue(0);
		sliderLTEngagement.setBounds(203, 210, 209, 29);
		add(sliderLTEngagement);
	
		
		new AffectiveController(sliderFrustration,sliderEngagement,sliderMeditation,sliderSTEngagement,sliderLTEngagement,dg);
		
	}
	
	
//	public static void main (String [] args) {
//		
//		JFrame frame = new JFrame();
//		frame.setSize(500, 400);
//		frame.getContentPane().add(new Affective());
//		
//		
//		frame.addWindowListener(new java.awt.event.WindowAdapter() {
//		      @Override
//		      public void windowClosing(java.awt.event.WindowEvent e) {
//		        System.exit(0);
//		      }
//		    });
//		
//		frame.setVisible(true);
//	}
}
