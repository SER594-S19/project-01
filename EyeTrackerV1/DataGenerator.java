package EyeTrackerV1;

import java.util.Calendar;
import java.util.Observable;

import Core.Data;

public class DataGenerator extends Observable implements Runnable {

  private DataEyeTracker data;
  private boolean stop = false;

  public void stop() {
    this.stop = true;
  }

  public Object getObject() {
    return data;
  }

  @Override
  public void run() {
    stop = false;
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    long initialTime = calendar.getTimeInMillis();
    double timeStamp = 0;
    double pupilLeft = 0;

    while (!stop) {
              System.out.println("data generator running");
      pupilLeft = Math.random();
      timeStamp = (System.currentTimeMillis() - initialTime) * .001;
      createAndNotify(timeStamp, Math.random(), pupilLeft);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
      }
    }
  }
  
  //methods to generate 9 other parameters

  private void createAndNotify(double timestampsystem, double s, double pupilLeft) {
                  System.out.println("notifying ...");
                  System.out.println(timestampsystem + " " + s + " " + pupilLeft);

    data = new DataEyeTracker(timestampsystem, s, pupilLeft);
    setChanged();
    notifyObservers();
  }

}
