package facialgestures;

import java.util.ArrayList;

import facialgestures.Data;

/**
 * This class encapsulates a timestamp for a row of data (one entry per channel)
 *
 */
public class FacialData extends Data{

    public int rWink;
    public int lWink;
    public int blink;
    public int lookRight;
    public int lookLeft;
    public double raiseBrow;
    public double furrowBrow;
    public double leftSmirk;
    public double righSmirk;
    public double smile;
    public double laugh;
    public double clench;
    public int engagement;
    public int shortTermExcitement;
    public int longTermExcitement;
    public int meditation;
    public int furstration;
    
    
    public int getrWink() {
		return rWink;
	}

	public void setrWink(int rWink) {
		this.rWink = rWink;
	}

	public int getlWink() {
		return lWink;
	}

	public void setlWink(int lWink) {
		this.lWink = lWink;
	}

	public int getLookRight() {
		return lookRight;
	}

	public void setLookRight(int lookRight) {
		this.lookRight = lookRight;
	}

	public int getLookLeft() {
		return lookLeft;
	}

	public void setLookLeft(int lookLeft) {
		this.lookLeft = lookLeft;
	}

	public double getRaiseBrow() {
		return raiseBrow;
	}

	public void setRaiseBrow(double raiseBrow) {
		this.raiseBrow = raiseBrow;
	}

	public double getFurrowBrow() {
		return furrowBrow;
	}

	public void setFurrowBrow(double furrowBrow) {
		this.furrowBrow = furrowBrow;
	}


//    public FacialData(double time,double value, int lwink, int rwink, int lookLeft, int lookRight, double raiseBrow, double furrowBrow) {
//		// TODO Auto-generated constructor stub
//	
//        super(time,value);
//        this.lookLeft = lookLeft;
//        this.lookRight = lookRight;
//        this.raiseBrow = raiseBrow;
//        this.furrowBrow = furrowBrow;
//        this.lWink = lwink;
//        this.rWink = rwink;
//    }

    public FacialData(double timestampsystem, ArrayList<Double> facialValues) {
		// TODO Auto-generated constructor stub
    	super(timestampsystem);
    	//this.setBlink(Integer.parfacialValues.get(0));
    	
	}


   
    
  public int getBlink() {
		return blink;
	}

	public void setBlink(int blink) {
		this.blink = blink;
	}

	public double getLeftSmirk() {
		return leftSmirk;
	}

	public void setLeftSmirk(double leftSmirk) {
		this.leftSmirk = leftSmirk;
	}

	public double getRighSmirk() {
		return righSmirk;
	}

	public void setRighSmirk(double righSmirk) {
		this.righSmirk = righSmirk;
	}

	public double getSmile() {
		return smile;
	}

	public void setSmile(double smile) {
		this.smile = smile;
	}

	public double getLaugh() {
		return laugh;
	}

	public void setLaugh(double laugh) {
		this.laugh = laugh;
	}

	public double getClench() {
		return clench;
	}

	public void setClench(double clench) {
		this.clench = clench;
	}

	public int getEngagement() {
		return engagement;
	}

	public void setEngagement(int engagement) {
		this.engagement = engagement;
	}

	public int getShortTermExcitement() {
		return shortTermExcitement;
	}

	public void setShortTermExcitement(int shortTermExcitement) {
		this.shortTermExcitement = shortTermExcitement;
	}

	public int getLongTermExcitement() {
		return longTermExcitement;
	}

	public void setLongTermExcitement(int longTermExcitement) {
		this.longTermExcitement = longTermExcitement;
	}

	public int getMeditation() {
		return meditation;
	}

	public void setMeditation(int meditation) {
		this.meditation = meditation;
	}

	public int getFurstration() {
		return furstration;
	}

	public void setFurstration(int furstration) {
		this.furstration = furstration;
	}

@Override
  public String toString() {
    return "Facial Data = "+this.lookLeft+", "+this.lookRight+", "+this.raiseBrow+", "+this.furrowBrow+", "+this.lWink+", "+this.rWink;
  }
     
}
