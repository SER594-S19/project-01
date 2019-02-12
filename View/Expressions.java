package View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSlider;

public class Expressions extends JPanel {
	private final ButtonGroup blinkButtonGroup = new ButtonGroup();
	private final ButtonGroup visionButtonGroup = new ButtonGroup();
	private final ButtonGroup eyeBrowButtonGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public Expressions() {
		setLayout(null);
		
		//label for eye blink
		JLabel blinkActionLabel = new JLabel("Select Blink Action:");
		blinkActionLabel.setBounds(20, 11, 127, 23);
		add(blinkActionLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 32, 137, 2);
		add(separator);
		
		//radio buttons for eye blink actions
		JRadioButton rdbtnBlink = new JRadioButton("Blink");
		blinkButtonGroup.add(rdbtnBlink);
		rdbtnBlink.setBounds(10, 47, 109, 23);
		add(rdbtnBlink);
		
		JRadioButton rdbtnWinkLeft = new JRadioButton("Wink Left");
		blinkButtonGroup.add(rdbtnWinkLeft);
		rdbtnWinkLeft.setBounds(125, 47, 109, 23);
		add(rdbtnWinkLeft);
		
		JRadioButton rdbtnWinkRight = new JRadioButton("Wink Right");
		blinkButtonGroup.add(rdbtnWinkRight);
		rdbtnWinkRight.setBounds(10, 75, 109, 23);
		add(rdbtnWinkRight);
		
		JRadioButton rdbtnNone = new JRadioButton("None");
		blinkButtonGroup.add(rdbtnNone);
		rdbtnNone.setBounds(125, 75, 109, 23);
		add(rdbtnNone);
		
		//label for eye vision
		JLabel lblSelectVision = new JLabel("Select Vision:");
		lblSelectVision.setBounds(20, 108, 100, 23);
		add(lblSelectVision);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 130, 137, 2);
		add(separator_1);
		
		//radio buttons for eye vision actions.
		JRadioButton rdbtnLookLeft = new JRadioButton("Look Left");
		visionButtonGroup.add(rdbtnLookLeft);
		rdbtnLookLeft.setBounds(10, 143, 109, 23);
		add(rdbtnLookLeft);
		
		JRadioButton rdbtnLookRight = new JRadioButton("Look Right");
		visionButtonGroup.add(rdbtnLookRight);
		rdbtnLookRight.setBounds(125, 139, 109, 23);
		add(rdbtnLookRight);
		
		JRadioButton rdbtnNone_1 = new JRadioButton("None");
		visionButtonGroup.add(rdbtnNone_1);
		rdbtnNone_1.setBounds(10, 169, 109, 23);
		add(rdbtnNone_1);
		
		//label for eye brow actions
		JLabel lblSelectEyeBrow = new JLabel("Select Eye Brow Action:");
		lblSelectEyeBrow.setBounds(20, 210, 137, 25);
		add(lblSelectEyeBrow);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 235, 137, 2);
		add(separator_2);
		
		//radio buttons for eye brow actions.
		JRadioButton rdbtnRaiseBrow = new JRadioButton("Raise Brow");
		eyeBrowButtonGroup.add(rdbtnRaiseBrow);
		rdbtnRaiseBrow.setBounds(10, 249, 109, 23);
		add(rdbtnRaiseBrow);
		
		JRadioButton rdbtnFurrow = new JRadioButton("Furrow");
		eyeBrowButtonGroup.add(rdbtnFurrow);
		rdbtnFurrow.setBounds(125, 249, 109, 23);
		add(rdbtnFurrow);
		
		JRadioButton rdbtnNone_2 = new JRadioButton("None");
		eyeBrowButtonGroup.add(rdbtnNone_2);
		rdbtnNone_2.setBounds(10, 275, 109, 23);
		add(rdbtnNone_2);
		
		
		//slider for smirk
		JSlider smirkSlider = new JSlider();
		smirkSlider.setMinorTickSpacing(1);
		smirkSlider.setValue(0);
		smirkSlider.setMaximum(1);
		smirkSlider.setMinimum(-1);
		smirkSlider.setBounds(259, 44, 200, 26);
		add(smirkSlider);
		
		JLabel lblLeftSmirk = new JLabel("Left Smirk");
		lblLeftSmirk.setBounds(240, 75, 76, 14);
		add(lblLeftSmirk);
		
		JLabel lblRightSmirk = new JLabel("Right Smirk");
		lblRightSmirk.setBounds(417, 75, 63, 14);
		add(lblRightSmirk);
		
		//slider for clench and smile.
		JSlider clenchSlider = new JSlider();
		clenchSlider.setValue(0);
		clenchSlider.setMinimum(-1);
		clenchSlider.setMaximum(1);
		clenchSlider.setBounds(259, 123, 200, 26);
		add(clenchSlider);
		
		JLabel lblClench = new JLabel("Clench");
		lblClench.setBounds(240, 156, 55, 23);
		add(lblClench);
		
		JLabel lblSmile = new JLabel("Smile");
		lblSmile.setBounds(437, 156, 43, 23);
		add(lblSmile);
		
		//sliders for laugh.
		JSlider laughSlider = new JSlider();
		laughSlider.setValue(0);
		laughSlider.setMaximum(1);
		laughSlider.setBounds(259, 210, 200, 26);
		add(laughSlider);
		
		JLabel lblLaugh = new JLabel("Laugh");
		lblLaugh.setBounds(336, 235, 46, 32);
		add(lblLaugh);

	}
	
	
}
