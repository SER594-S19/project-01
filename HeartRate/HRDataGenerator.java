package HeartRate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import Core.DataGenerator;


public class HRDataGenerator extends DataGenerator implements Runnable  {

  private HeartData data;
  private boolean stop = false;
  private int heartState;
  private double frequency;

  public HRDataGenerator() {
    heartState = 0;
  }

  public void setHeartState(int heartState) {
    this.heartState = heartState;
  }

  public void setFrequency(double frequency) {
    this.frequency = frequency;
  }

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
      
      createAndNotify(timeStamp, generateValues(heartState));
      try {
        Thread.sleep((long)(frequency * 1000));
      } catch (InterruptedException ex) {
      }
    }
  }

  private List<Integer> generateValues(int heartState) {
    int startRange, endRange;
    switch(heartState) {
      case 0: startRange = 60;
              endRange = 100;
              break;
      case 1: startRange = 100;
              endRange = 140;
              break;
      case 2: startRange = 140;
              endRange = 190;
              break;
      default: startRange = 60;
               endRange = 100;
    }

    int randomNum = ThreadLocalRandom.current().nextInt(startRange, endRange + 1);
    List<Integer> values = new ArrayList<>();
    values.add(randomNum);

    return values;
  }

  private void createAndNotify(double timeStamp, List<Integer> values) {
    data = new HeartData(timeStamp, values);
    Gui.getInstance().setTextPane(data.toString());
    setChanged();
    notifyObservers();
  }

}
