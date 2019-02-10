package controller;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Model.AffectiveData;

public class AffectiveController {
	
	
	JSlider sliderFrustration, sliderEngagement, sliderMeditation, sliderSTEngagement, sliderLTEngagement; 
	public AffectiveData affectivedata ;
	
	public AffectiveController(final JSlider sliderFrustration,final JSlider sliderEngagement,final JSlider sliderMeditation,final JSlider sliderSTEngagement,final JSlider sliderLTEngagement) {
		
		affectivedata = new AffectiveData();
		this.sliderFrustration = sliderFrustration;
		this.sliderEngagement = sliderEngagement;
		this.sliderMeditation = sliderMeditation;
		this.sliderSTEngagement = sliderSTEngagement;
		this.sliderLTEngagement = sliderLTEngagement; 
		
		sliderFrustration.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateDataModel();
			}
		});
		
		sliderLTEngagement.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateDataModel();
			}
		});
		
		sliderSTEngagement.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateDataModel();
			}
		});
		
		sliderMeditation.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateDataModel();
			}
		});
		
		sliderEngagement.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateDataModel();
				
			}
		});
			
	}
	
	private double getDecimalBasedOnTick(int sliderValue) {
		
		return (double)sliderValue/100;
	}
	
	private void updateDataModel() {
		
		
		double frustrationLevel = getDecimalBasedOnTick(this.sliderFrustration.getValue());
		double meditationLevel  = getDecimalBasedOnTick(this.sliderMeditation.getValue());
		double engagementLevel = getDecimalBasedOnTick(this.sliderEngagement.getValue());
		double shortTermEngagementLevel = getDecimalBasedOnTick(this.sliderSTEngagement.getValue());
		double longTermEngagementLevel = getDecimalBasedOnTick(this.sliderLTEngagement.getValue());
		
		affectivedata.updateAffectiveData(frustrationLevel, engagementLevel, meditationLevel, shortTermEngagementLevel, longTermEngagementLevel);
		
		
		
	}
	

}
