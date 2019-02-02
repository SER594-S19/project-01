package EyeTrackerV1;
import Core.Data;

public class DataEyeTracker extends Data {
	private double pupilLeft;
	private double pupilRight;
	private double gpx;
	private double gpy;
	private double validityL;
	private double validityR;
	private int Fixation;

	public int getFixation() {
		return Fixation;
	}

	public void setFixation(int fixation) {
		Fixation = fixation;
	}

	public double getPupilRight() {
		return pupilRight;
	}

	public void setPupilRight(double pupilRight) {
		this.pupilRight = pupilRight;
	}

	public double getGpx() {
		return gpx;
	}

	public void setGpx(double gpx) {
		this.gpx = gpx;
	}

	public double getGpy() {
		return gpy;
	}

	public void setGpy(double gpy) {
		this.gpy = gpy;
	}

	public double getValidityL() {
		return validityL;
	}

	public void setValidityL(double validityL) {
		this.validityL = validityL;
	}

	public double getValidityR() {
		return validityR;
	}

	public void setValidityR(double validityR) {
		this.validityR = validityR;
	}

	public DataEyeTracker(double time, double value, double pupilLeft) {
		super(time, value);
		this.pupilLeft = pupilLeft;
		
	}

	public double getPupilLeft() {
		return pupilLeft;
	}

	
	public void setPupilLeft(double pupilLeft) {
		this.pupilLeft = pupilLeft;
	}
	  @Override
	public String toString() {
	    return "Data{" + "time=" + getTime() + "," + ", Pupil Left = " + getPupilLeft()  + '}'+", PupilRight = " + getPupilLeft()
	    + ", ValidityL = " + getValidityL() + ", ValidityR = " +getValidityR() + ", Gpx = " +getGpx()+ ", Gpy = "+ getGpy() +", Fixation = "+ getFixation();
	
	}
}