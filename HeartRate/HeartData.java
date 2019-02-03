package HeartRate;

import java.util.List;

/**
 * This class collects heart data
 *
 * @author Harshita
 * @version 20190202
 */
public class HeartData extends Core.Data{

  List<Integer> values;

  public HeartData(double time, List<Integer> values) {
    super(time, 0);
    this.values = values;
  }

  public void setData(List<Integer> values) {
    this.values = values;
  }

  @Override
  public String toString() {
    return "Heart Rate = " + values.get(0);
  }
     
}
