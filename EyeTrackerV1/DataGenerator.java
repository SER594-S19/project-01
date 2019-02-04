package EyeTrackerV1;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Observable;

import Core.Data;
import EyeTrackerV1.Gui;

public class DataGenerator extends Observable implements Runnable {

/*	
	public DataGenerator(Gui val) {
		this.val = val;
	}
	
	public DataGenerator() {}
	
*/
	
  private DataEyeTracker data;
  private Gui val;
  private boolean stop = false;
  protected static HashMap<String, Integer> eyeParameters = new HashMap<String, Integer>() {{
	put("pupilLeft", 0);
	put("pupilRight", 0);
	put("gpxValue", 0);
	put("gpyValue", 0);
	put("validityL", 0);
	put("validityR", 0);
	put("fixationValue", 0);
	put("event", 0);
	put("aoi", 0);
  }};


  public void stop() {
    this.stop = true;
  }

  public Object getObject() {
    return data;
  }

  
  @Override
  public void run() {
	  System.out.println("hello");
    stop = false;
    /*
    try {
    HashMap<String, Integer> eyeParameters;
    eyeParameters = val.getEyeParameters();
    
    System.out.println(" parameters are" + eyeParameters);
    }
    catch(Exception e) {
    	e.printStackTrace();
    }
    */
    System.out.println("yes");
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    long initialTime = calendar.getTimeInMillis();
    double timeStamp = 0;

   
    while (!stop) {
              System.out.println("data generator running");
      timeStamp = (System.currentTimeMillis() - initialTime) * .001;
      createAndNotify(timeStamp, Math.random(), generatePupilLeft(), generatePupilright(), generateGpxValue(), generate_GpyValue(), generateValidityL(), generateValidityR(), fixationValue(), event(), aoi());
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
      }
    }
  }
  
  public void updateParam(String s, int val) {
	  eyeParameters.put(s, val);
	  System.out.println(eyeParameters);
	  
  }
  //methods to generate 9 other parameters
  public static int generateGpxValue() {
	  return eyeParameters.get("gpxValue");
	  
  }
  public static int generate_GpyValue() {
	  return eyeParameters.get("gpyValue");
		  
	  }
  public static double generatePupilLeft() {
	  return eyeParameters.get("pupilLeft");
		  
	  }
  public static double generatePupilright() {
	  return eyeParameters.get("pupilRight");
		  
	  }

  public static int generateValidityL() {
	return  eyeParameters.get("validityL");
		
	}
  public static int generateValidityR() {
	  return eyeParameters.get("validityR");
	  
  }
  public static int fixationValue() {
	  return eyeParameters.get("fixationValue");
  }
  public static int event() {
	  return eyeParameters.get("event");
  }
  public static int aoi() {
	  return eyeParameters.get("aoi");
  }
  
  private void createAndNotify(double timestampsystem, double s, double pupilLeft, 
		  double pupilRight,int gpxValue, 
		  int gpyValue,int validityL, 
		  int validityR, int fixationValue, int event, int aoi) {
                  System.out.println("notifying ...");
                  //System.out.println(timestampsystem + " " + s + " " + pupilLeft);

    data = new DataEyeTracker(timestampsystem, s, pupilLeft, pupilRight, gpxValue, gpyValue, validityL, validityR, fixationValue, event, aoi);
    setChanged();
    notifyObservers();
  }

}
