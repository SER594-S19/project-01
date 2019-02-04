package EyeTrackerV1;
import Core.Data;

public class DataEyeTracker extends Data {
	private double pupilLeft;
	private double pupilRight;
	private int gpx;
	private int gpy;
	private int validityL;
	private int validityR;
	private int Fixation;
	private int event;
	private int aoi;

	public int getEvent() {
		return event;
	}

	public void setEvent(int event) {
		this.event = event;
	}

	public int getAoi() {
		return aoi;
	}

	public void setAoi(int aoi) {
		this.aoi = aoi;
	}

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

	public void setGpx(int gpx) {
		this.gpx = gpx;
	}

	public double getGpy() {
		return gpy;
	}

	public void setGpy(int gpy) {
		this.gpy = gpy;
	}

	public double getValidityL() {
		return validityL;
	}

	public void setValidityL(int validityL) {
		this.validityL = validityL;
	}

	public double getValidityR() {
		return validityR;
	}

	public void setValidityR(int validityR) {
		this.validityR = validityR;
	}

	public DataEyeTracker(double time, double value, double pupilLeft, double pupilRight, int gpx, int gpy, int validityL, int validityR, int Fixation, int event, int aoi) {
		super(time, value);
		this.pupilLeft = pupilLeft;
		this.pupilRight = pupilRight;
		this.aoi = aoi;
		this.event = event;
		this.Fixation = Fixation;
		this.gpx = gpx;
		this.gpy = gpy;
		this.validityL = validityL;
		this.validityR = validityR;
		
		
	}

	public double getPupilLeft() {
		return pupilLeft;
	}

	
	public void setPupilLeft(double pupilLeft) {
		this.pupilLeft = pupilLeft;
	}
	  @Override
	public String toString() {
	    return "Data{" + "time=" + getTime() + ", Pupil Left = " + getPupilLeft()  +", PupilRight = " + getPupilRight()
	    + ", ValidityL = " + getValidityL() + ", ValidityR = " +getValidityR() + ", Gpx = " +getGpx()+ ", Gpy = "+ getGpy() +", Fixation = "+ getFixation() +", Event = " +getEvent() +", AOI = "+getAoi() + " }";
	
	}
}