package EyeTrackerV1;

public class DataEyeTracker extends Data 
{
	private double pupilLeft;
	private double pupilRight;
	private double gpx;
	private double gpy;
	private double validityL;
	private double validityR;
	private double Fixation;
	private double event;
	private double aoi;

	public double getEvent() 
	{
		return event;
	}

	public void setEvent(int event)
	{
		this.event = event;
	}

	public double getAoi() 
	{
		return aoi;
	}

	public void setAoi(int aoi)
	{
		this.aoi = aoi;
	}

	public double getFixation()
	{
		return Fixation;
	}

	public void setFixation(int fixation) 
	{
		Fixation = fixation;
	}

	public double getPupilRight() 
	{
		return pupilRight;
	}

	public void setPupilRight(double pupilRight)
	{
		this.pupilRight = pupilRight;
	}

	public double getGpx() 
	{
		return gpx;
	}

	public void setGpx(int gpx)
	{
		this.gpx = gpx;
	}

	public double getGpy() 
	{
		return gpy;
	}

	public void setGpy(int gpy)
	{
		this.gpy = gpy;
	}

	public double getValidityL()
	{
		return validityL;
	}

	public void setValidityL(int validityL)
	{
		this.validityL = validityL;
	}

	public double getValidityR() 
	{
		return validityR;
	}

	public void setValidityR(int validityR)
	{
		this.validityR = validityR;
	}

	public DataEyeTracker(double time, double value, double pupilLeft, double pupilRight, double gpx, double gpy, double d, double e, double f, double g, double h) 
	{
		super(time, value);
		this.pupilLeft = pupilLeft;
		this.pupilRight = pupilRight;
		this.aoi = h;
		this.event = g;
		this.Fixation = f;
		this.gpx = gpx;
		this.validityL = d;
		this.validityR = e;
		this.gpy = gpy;
	}


	public double getPupilLeft()
	{
		return pupilLeft;
	}

	
	public void setPupilLeft(double pupilLeft)
	{
		this.pupilLeft = pupilLeft;
	}
	  @Override
	public String toString() 
	{
	    return "Data{" + "time=" + getTime() + ", Pupil Left = " + getPupilLeft()  +", PupilRight = " + getPupilRight()
	    + ", ValidityL = " + getValidityL() + ", ValidityR = " +getValidityR() + ", Gpx = " +getGpx()+ ", Gpy = " + getGpy()+ ", Fixation = "+ getFixation() +", Event = " +getEvent() +", AOI = "+getAoi() + " }";

	}
}