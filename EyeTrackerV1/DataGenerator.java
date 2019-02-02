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
    double pupilLeft = generatePupilLeft(2,8);
    double pupilRight = generatePupilright(2,8);
    int gpxValue = generateGpxValue(0,2880);
    int gpyValue = generate_GpyValue(0,1880);
    int validityL = generateValidityL(0,4);
    int validityR = generateValidityR(0,4);
    int fixationValue = fixationValue(1,100);
    

    while (!stop) {
              System.out.println("data generator running");
      pupilLeft = Math.random();
      timeStamp = (System.currentTimeMillis() - initialTime) * .001;
      createAndNotify(timeStamp, Math.random(), pupilLeft, pupilRight, gpxValue, gpyValue, validityL,validityR,fixationValue);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
      }
    }
  }
  
  //methods to generate 9 other parameters
  public static int generateGpxValue(int min , int max) {
	  return (int)(Math.random() * (max+1-min)) + min;
	  
  }
  public static int generate_GpyValue(int min, int max) {
	  return (int)(Math.random() * (max+1-min)) + min;
		  
	  }
  public static double generatePupilLeft(int min, int max) {
	  return (Math.random() * (max+1-min)) + min;
		  
	  }
  public static double generatePupilright(int min, int max) {
	  return (Math.random() * (max+1-min)) + min;
		  
	  }

  public static int generateValidityL(int min , int max) {
	return  (int)(Math.random() * (max+1-min)) + min;
		
	}
  public static int generateValidityR(int min , int max) {
	  return (int)(Math.random() * (max+1-min)) + min;
	  
  }
  public static int fixationValue(int min, int max) {
	  return (int)(Math.random() * (max+1-min)) + min;
  }

  private void createAndNotify(double timestampsystem, double s, double pupilLeft, 
		  double pupilRight,int gpxValue, 
		  int gpyValue,int validityL, 
		  int validityR, int fixationValue) {
                  System.out.println("notifying ...");
                  System.out.println(timestampsystem + " " + s + " " + pupilLeft);

    data = new DataEyeTracker(timestampsystem, s, pupilLeft);
    setChanged();
    notifyObservers();
  }

}
