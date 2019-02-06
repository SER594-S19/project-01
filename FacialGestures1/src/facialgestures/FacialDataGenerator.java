package facialgestures;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.stream.DoubleStream;
import Core.DataGenerator;

class FacialDataGenerator extends DataGenerator implements Runnable {

  private FacialData data;
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
              System.out.println("data generator running");
      timeStamp = (System.currentTimeMillis() - initialTime) * .001;
      createAndNotify(timeStamp, Math.random());
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
      }
    }
  }

  private void createAndNotify(double timestampsystem, double s) {
                  System.out.println("notifying ...");                 
                  getSecureRandomNumber(0.0,1.0);
   // data = new Data(timestampsystem, s);
    setChanged();
    notifyObservers();
  }
  
  
  private double getSecureRandomNumber(double min , double max) {
	  double randomValue = 0;
	  try {
    	  SecureRandom instanceStrong = SecureRandom.getInstanceStrong();
		DoubleStream doubles = instanceStrong.doubles(min,max);
		randomValue = doubles.findAny().getAsDouble();
		System.out.println("Random Num: " +randomValue);
    	} catch (NoSuchAlgorithmException e) {
    		
    		e.printStackTrace();
    	}
	  return randomValue;
  }

}
