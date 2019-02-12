package Model;

public class AffectiveData {

	private double frustration ;
	private double engagement;
	private double meditation;
	private double stengagement;
	private double ltengagement;
	
	


	public AffectiveData() {
		// TODO Auto-generated constructor stub
	}

	public void updateAffectiveData(double frustration, double engagement, double meditation, double stengagement, double ltengagement) {
		
		this.frustration = frustration ;
		this.engagement = engagement;
		this.meditation = meditation ;
		this.stengagement = stengagement;
		this.ltengagement = ltengagement;
		
		System.out.println(this.frustration+" "+ this.engagement +" "+ this.meditation +" "+this.stengagement+" "+this.ltengagement+" ");
	}


	
}
