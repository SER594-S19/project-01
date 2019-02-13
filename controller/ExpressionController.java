package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ExpressionController {
	
	private JSlider smirkSlider;
	private JSlider clenchSlider;
	private JSlider laughSlider;
	private ButtonGroup blinkButtonGroup;
	private ButtonGroup visionButtonGroup;
	private ButtonGroup eyeBrowButtonGroup;
	
	private ExpressionData expressionData;
	
	public ExpressionController(JSlider smirkSlider, JSlider clenchSlider, JSlider laughSlider,
				ButtonGroup blinkButtonGroup, ButtonGroup visionButtonGroup, ButtonGroup eyeBrowButtonGroup) {
		
		this.smirkSlider = smirkSlider;
		this.clenchSlider = clenchSlider;
		this.laughSlider = laughSlider;
		this.blinkButtonGroup = blinkButtonGroup;
		this.visionButtonGroup = visionButtonGroup;
		this.eyeBrowButtonGroup = eyeBrowButtonGroup;
		
		expressionData = new ExpressionData();
		
		smirkSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				updateExpressionModel();
			}
		});
		
		clenchSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				updateExpressionModel();
			}
		});
		
		laughSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				updateExpressionModel();
			}
		});
		
		setChangeListeners(blinkButtonGroup);
		setChangeListeners(visionButtonGroup);
		setChangeListeners(eyeBrowButtonGroup);
	}
	
	/*
	 * method to set change listeners for each button in a button group.
	 * @param: buttonGroup of type ButtonGroup
	 * referred from: https://stackoverflow.com/questions/201287/
	 */
	private void setChangeListeners(ButtonGroup buttonGroup) {
		for(Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			buttons.nextElement().addItemListener((new ItemListener() {
				public void itemStateChanged(ItemEvent event) {
					if(event.getStateChange() == ItemEvent.SELECTED)
						updateExpressionModel();
				}
			}));
		}
	}
	
	/*
	 * method to get selected option for each button in a button group.
	 * @param: buttonGroup of type ButtonGroup
	 * referred from: https://stackoverflow.com/questions/201287/
	 */
	private String getSelectedOption(ButtonGroup buttonGroup) {
		for(Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			if(button.isSelected()) {
				return button.getText();
			}
		}
		return "None";
	}
	
	private double getTickDecimal(int tickValue) {
		return (double)tickValue/100;
	}
	
	private void updateExpressionModel() {
		double smirkSliderValue = getTickDecimal(this.smirkSlider.getValue());
		double clenchSliderValue = getTickDecimal(this.clenchSlider.getValue());
		double laughSliderValue = getTickDecimal(this.laughSlider.getValue());
		String blinkOption = getSelectedOption(this.blinkButtonGroup);
		String visionOption = getSelectedOption(this.visionButtonGroup);
		String eyeBrowOption = getSelectedOption(this.eyeBrowButtonGroup);
		
		expressionData.updateExpressionData(blinkOption, visionOption, eyeBrowOption, 
											smirkSliderValue, clenchSliderValue, laughSliderValue);
	}

}
