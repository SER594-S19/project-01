package Core;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Observable;

class DataGenerator extends Observable implements Runnable {

  private Data data;
  private boolean stop = false;



  protected static HashMap<String, Double> faceParameters = new HashMap<>()
  {
    {
      put("agreement", (double) 0);
      put("disagreement", (double) 0);
      put("frustrate", (double) 0);
      put("concentrate", (double) 0);
      put("thinking", (double) 0);
      put("unsure", (double) 0);

    }
  };


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
    long initialTime = calendar.getTimeInMillis();
    double timeStamp;


    while (!stop) {
      System.out.println("data generator running");
      float agreeInput = (float) (faceParameters.get("agreement")/10);
      float disagreeInput = (float) (faceParameters.get("disagreement")/10);
      float frusInput = (float) (faceParameters.get("frustrate")/10);
      float concenInput = (float) (faceParameters.get("concentrate")/10);
      float thinkInput = (float) (faceParameters.get("thinking")/10);
      float unsureInput = (float) (faceParameters.get("unsure")/10);
      timeStamp = (System.currentTimeMillis() - initialTime) * .001;


      createAndNotify(timeStamp, agree, disagree, frustrate, concentrate, thinking, unsure);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
      }

    }
  }


  private void createAndNotify(double timestampsystem, float agreement, float disagreement, float frustrate, float concentrating, float thinking, float unsure) {
    System.out.println("notifying ...");

    data = new Data(timestampsystem, agreement, concentrating, disagreement, frustrate, thinking, unsure);
    data.setData( timestampsystem, agreement, concentrating, disagreement,frustrate, thinking, unsure);

    System.out.println(data.toString());
    setChanged();
    notifyObservers();
  }
}