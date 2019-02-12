package Core;

import java.util.Calendar;
import java.util.Observable;

import Model.AffectiveData;

public class DataGenerator extends Observable implements Runnable {

  private Data data;
  private boolean stop = false; 

  public void stop() {
    this.stop = true;
  }

  public Object getObject() {
    return data;
  }
  
  public DataGenerator() {
	  this.data=new Data(0,new double[14],new AffectiveData());
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
      double[] values=new double[14];
      for(int i=0;i<values.length;i++) {
    	double val=Math.random();
        values[i]=val;
       }
      data.setValues(values);
      data.setTimeStamp(timeStamp);
      createAndNotify();
      try {
        Thread.sleep(1000); 
      } catch (InterruptedException ex) {
      }
    }
  }

  private void createAndNotify() {
    System.out.println("notifying ...");
    setChanged();
    notifyObservers();
  }

}
