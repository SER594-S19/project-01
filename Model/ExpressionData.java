package Model;

public class ExpressionData {
	private String blinkSelection;
	private String visionSelection;
	private	String eyeBrowSelection;
	
	private double smirkValue;
	private double clenchValue;
	private double laughValue;
	
	public ExpressionData() {
		
	}
	
	public void updateExpressionData(String blinkSelection, String visionSelection, 
									 String eyeBrowSelection, double smirkValue, 
									 double clenchValue, double laughValue) {
		this.blinkSelection = blinkSelection;
		this.visionSelection = visionSelection;
		this.eyeBrowSelection = eyeBrowSelection;
		this.smirkValue = smirkValue;
		this.clenchValue = clenchValue;
		this.laughValue = laughValue;
		
		System.out.println(this.blinkSelection + " " + this.visionSelection + " " + this.eyeBrowSelection);
		System.out.println(this.smirkValue + " " + this.clenchValue + " " + this.laughValue);
	}
}
