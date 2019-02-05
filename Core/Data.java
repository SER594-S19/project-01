package Core;


/**
 * This class encapsulates a timestamp for a row of data (one entry per channel)
 *
 * @author javiergs
 * @version 20190130
 */
public class Data {

    private double time;
    private int x;
    private int y;
    private int scroll;

    public Data(double time, int x,int y, int scroll){
        this.time=time;
        this.x=x;
        this.y=y;
        this.scroll=scroll;
    }

  @Override
  public String toString() {
    return "Data{" + "time=" + time + ", x=" + x + ", y="+y +", scroll="+scroll +"}";
  }
     
}
