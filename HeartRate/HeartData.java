package HeartRate;

import java.util.List;

/**
 * This class collects heart data
 *
 * @version 20190202
 */

public class HeartData extends Core.Data{

  List<Integer> values;

  // Constructor for HeartData class
  public HeartData(double time, List<Integer> values) {
    super(time, 0);
    this.values = values;
  }

  // Method to set data values for core variables
  public void setData(List<Integer> values) {
    this.values = values;
  }

  // Method to print the heart rate values
  @Override
  public String toString() {
    return "Heart Rate = " + values.get(0);
  }
     
}