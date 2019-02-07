package EyeTrackerV1;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Observable;

public class DataGenerator extends Observable implements Runnable
{
  private DataEyeTracker data;
  private Gui val;
  private boolean stop = false;
  protected static HashMap<String, Double> eyeParameters = new HashMap<String,Double>() 
  {
	private static final long serialVersionUID = 1L;   
	  {
	put("pupilLeft", 0.0);
	put("pupilRight", 0.0);
	put("gpxValue", (double) 0);
	put("gpyValue", (double) 0);
	put("validityL", (double) 0);
	put("validityR", (double) 0);
	put("fixationValue", (double) 0);
	put("event", (double) 0);
	put("aoi", (double) 0);
  }
	  };


  public void stop() 
  {
    this.stop = true;
  }

  public Object getObject() 
  {
    return data;
  }

  
  @Override
  public void run()
  {
	System.out.println("hello");
    stop = false;
    System.out.println("yes");
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    long initialTime = calendar.getTimeInMillis();
    double timeStamp = 0;

   
    while (!stop) 
    {
              System.out.println("data generator running");
      timeStamp = (System.currentTimeMillis() - initialTime) * .001;
      createAndNotify(timeStamp, Math.random(), generatePupilLeft(), generatePupilright(), generateGpxValue(), generate_GpyValue(), generateValidityL(), generateValidityR(), fixationValue(), event(), aoi());
      try 
      {
        Thread.sleep(1000);
      } 
      catch (InterruptedException ex) 
      {
    	  System.out.println("Exception..");
      }
    }
  }
  
  public void updateParam(String s, Double val) 
  {
	  
	  if (s == "pupilLeft" || s == "pupilRight") 
	  {
		  eyeParameters.put(s, val);
	  }
	  else 
	  {
		  eyeParameters.put(s, Math.floor(val));
	  }
	  
	  System.out.println(eyeParameters);
	  
  }
  //methods to generate 9 other parameters
  public static Double generateGpxValue() 
  {
	  return eyeParameters.get("gpxValue");
	  
  }
  public static Double generate_GpyValue() 
  {
	  return eyeParameters.get("gpyValue");
		  
	  }
  public static double generatePupilLeft()
  {
	  return eyeParameters.get("pupilLeft");
		  
	  }
  public static double generatePupilright() 
  {
	  return eyeParameters.get("pupilRight");
		  
	  }

  public static double generateValidityL()
  {
	return  eyeParameters.get("validityL");
		
	}
  public static double generateValidityR() 
  {
	  return eyeParameters.get("validityR");
	  
  }
  public static double fixationValue() 
  {
	  return eyeParameters.get("fixationValue");
  }
  public static double event()
  {
	  return eyeParameters.get("event");
  }
  public static double aoi() 
  {
	  return eyeParameters.get("aoi");
  }
  
  private void createAndNotify(double timestampsystem, double s, double pupilLeft, 
		  double pupilRight,double gpxValue, 
		  double gpyValue,double d, 
		  double e, double f, double g, double h)
  {
                  System.out.println("notifying ...");
    data = new DataEyeTracker(timestampsystem, s, pupilLeft, pupilRight, gpxValue, gpyValue, d, e, f, g, h);
    setChanged();
    notifyObservers();
  }

}
