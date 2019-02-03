package Core;


/**
 * This class encapsulates a timestamp for a row of data (one entry per channel)
 *
 * @author javiergs
 * @version 20190130
 */
public class Data {

    private double time;
    // representing 14 channels of bci
    private double[] values=new double[14];

    public Data(double time, double[] values) {
        this.time = time;
        this.values = values;
    }

    public void setData(double[] data) {
      time=0;
      values=data;
    }

  @Override
    //  public String toString() {
    //    return "Data{" + "time=" + time + ", value=" + value + '}';
    //  }
  public String toString() {
        String data;
        data = "time ="+time+"\n";
        for(int i=0;i<14;i++) {
            String ch=i+1+"";
            data+="Channel "+ch+": "+values[i]+"\n";
        }

        return data;
      //return "Data{" + "time=" + time + ", value=" + value + '}';
  }
}
