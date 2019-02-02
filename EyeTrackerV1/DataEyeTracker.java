package EyeTrackerV1;
import Core.Data;

public class DataEyeTracker extends Data {
	private double pupilLeft;

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
	    return "Data{" + "time=" + getTime() + "," + ", Pupil Left = " + getPupilLeft()  + '}';
	}
}