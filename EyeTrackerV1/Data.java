package EyeTrackerV1;


/**
 * This class encapsulates a timestamp for a row of data (one entry per channel)
 *
 * @author javiergs
 * @version 20190130
 */
public class Data {

    private double time;
    private double value;
    
    public Data(double time, double value) {
    	this.time = time;
        this.value = value;
    }

    public void setData(double data) {
    	time=0;
        value=data;
    }

public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

@Override
  public String toString() {
	    return "Data{" + "time=" + time + ", value=" + value + '}';
	  }
     
}
