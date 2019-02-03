package HeartRate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;

class DataGenerator extends Observable implements Runnable {

  private Data data;
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

    while (!stop) {
      timeStamp = (System.currentTimeMillis() - initialTime) * .001;
      createAndNotify(timeStamp, generateValues(60, 190));
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
      }
    }
  }

  private ArrayList<Integer> generateValues(int startRange, int endRange) {
    return new ArrayList<>();
  }

  private void createAndNotify(double timestampsystem, ArrayList<Integer> values) {
    data = new Data(timestampsystem, values);
    setChanged();
    notifyObservers();
  }

}
