package facialgestures;

import Core.Data;

/**
 * This class encapsulates a timestamp for a row of data (one entry per channel)
 *
 */
public class FacialData extends Data{

    public int rWink;
    public int lWink;
    public int lookRight;
    public int lookLeft;
    public double raiseBrow;
    public double furrowBrow;
    
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


    public FacialData(double time,double value, int lwink, int rwink, int lookLeft, int lookRight, double raiseBrow, double furrowBrow) {
		// TODO Auto-generated constructor stub
	
        super(time,value);
        this.lookLeft = lookLeft;
        this.lookRight = lookRight;
        this.raiseBrow = raiseBrow;
        this.furrowBrow = furrowBrow;
        this.lWink = lwink;
        this.rWink = rwink;
    }

    public void setData(double data, int leftwink, int rightwink, int lookleft, int lookright, double raisebrow, double furrowbrow) {
      lWink = leftwink;
      rWink = rightwink;
      lookLeft = lookleft;
      lookRight = lookright;
      raiseBrow = raisebrow;
      furrowBrow = furrowbrow;
    }

   
    
  @Override
  public String toString() {
    return "Facial Data = "+this.lookLeft+", "+this.lookRight+", "+this.raiseBrow+", "+this.furrowBrow+", "+this.lWink+", "+this.rWink;
  }
     
}
