package Core;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Observable;
import java.util.Random;

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
              System.out.println("data generator running");
      timeStamp = (System.currentTimeMillis() - initialTime) * .001;
      createAndNotify(timeStamp);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
      }
    }
  }
  
  private double getValueBetweenExpRange(SecureRandom rand,int low,int high) {
	  Random r = new Random();
	  int powerOf = 0;
	  if(low != 0 && high != 0 ) {
		  powerOf = r.nextInt(high-low) + low;
	  }
	  double randomValue = rand.nextDouble()/Math.pow(10, powerOf);
	  return randomValue;
  }

  private void createAndNotify(double timestampsystem) {
                  System.out.println("notifying ...");
    SecureRandom random = new SecureRandom();
    double agreementRange = getValueBetweenExpRange(random,Constants.RANGEAGREEMENT0,Constants.RANGEAGREEMENT1);
    double concentratingRange = getValueBetweenExpRange(random,Constants.RANGECONCENTRATING0,Constants.RANGECONCENTRATING1);
    double disagreementRange = getValueBetweenExpRange(random,Constants.RANGEDISAGREEMENT0,Constants.RANGEDISAGREEMENT1);
    double interestedRange = getValueBetweenExpRange(random,Constants.RANGEINTERESTED0,Constants.RANGEINTERESTED1);
    double thinkingRange = getValueBetweenExpRange(random,Constants.RANGETHINKING0,Constants.RANGETHINKING1);
	double unsureRange = getValueBetweenExpRange(random,Constants.RANGEUNSURE0,Constants.RANGEUNSURE1);
    data = new Data(timestampsystem, agreementRange, concentratingRange, disagreementRange, interestedRange, thinkingRange, unsureRange);
    System.out.println("Data generator generated:"+data);
    setChanged();
    notifyObservers();
  }
  

}
