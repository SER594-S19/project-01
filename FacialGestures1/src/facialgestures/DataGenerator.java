package facialgestures;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;
import java.util.stream.DoubleStream;

class DataGenerator extends Observable implements Runnable {

  private FacialData data;
  private boolean stop = false;
  private ArrayList<Double> facialValues = new ArrayList<>(); 
  public ArrayList<Double> getFacialValues() {
	return facialValues;
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
              System.out.println("data generator running");
      timeStamp = (System.currentTimeMillis() - initialTime) * .001;
      createAndNotify(timeStamp, getFacialValues());
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
      }
    }
  }

  private void createAndNotify(double timestampsystem, ArrayList<Double> facialValues) {
                  System.out.println("notifying ...");                 
                  getSecureRandomNumber(0.0,1.0);
    data = new FacialData(timestampsystem, facialValues);
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

public void setFacialValues(ArrayList<Double> facialValues) {
	// TODO Auto-generated method stub
	this.facialValues = facialValues;
	
	
}

}
