package Core;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Observable;
import java.util.Random;

class DataGenerator extends Observable implements Runnable {

  private Data data;
  private boolean stop = false;
  private String emotionFromGUI;
  
  public String getEmotion() {
		return emotionFromGUI;
  }

  public void setEmotion(String emotion) {
	this.emotionFromGUI = emotion;
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
      createAndNotify(timeStamp);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
      }
    }
  }
  
  private double getValueBetweenExpRange(SecureRandom rand,String createEmo) {
	  Random r = new Random();
	  int powerOf = 0;
	  if(!createEmo.equals(this.emotionFromGUI)){
		  powerOf = r.nextInt(Constants.RANGE_HIGH-Constants.RANGE_LOW) + Constants.RANGE_LOW;
	  }
	  double randomValue = rand.nextDouble()/Math.pow(10, powerOf);
	  return randomValue;
  }

  private void createAndNotify(double timestampsystem) {
                  System.out.println("notifying ...");
    SecureRandom random = new SecureRandom();
    double agreementRange = getValueBetweenExpRange(random,"Agreement");
    double concentratingRange = getValueBetweenExpRange(random,"Concentrating");
    double disagreementRange = getValueBetweenExpRange(random,"Disagreement");
    double interestedRange = getValueBetweenExpRange(random,"Interested");
    double thinkingRange = getValueBetweenExpRange(random,"Thinking");
	double unsureRange = getValueBetweenExpRange(random,"Unsure");
    data = new Data(timestampsystem, agreementRange, concentratingRange, disagreementRange, interestedRange, thinkingRange, unsureRange);
    System.out.println("Data generator generated:"+data);
    setChanged();
    notifyObservers();
  }

}
