package Core;

import java.util.Calendar;
import java.util.*;


class DataGenerator extends Observable implements Runnable {

  private Data data;
  private boolean stop = false;
  //double timeStamp = 0;

  public void stop() {
    this.stop = true;
  }

//  protected static HashMap<String, Double> skinP = new HashMap<String, Double>()
//  {
//    {
//      put("voltage", (double) 0);
//      put("conductance", (double) 0);
//
//    }
//  };

  public Object getObject() {
    return data;
  }

  @Override
  public void run() {
    stop = false;

    Calendar calendar = Calendar.getInstance();
    long initialTime = calendar.getTimeInMillis();
    double timeStamp = 0;



    while (!stop) {
              System.out.println("data generator running");
//      double volInput = (double) (skinP.get("agreement")/10);
//      double conInput = (double) (skinP.get("disagreement")/10);
      timeStamp = (System.currentTimeMillis() - initialTime) * .001;
//      double voltage =  (double) ((volInput - 0.1) + new Random().nextDouble()*(volInput - (volInput - 0.1)));
//
//      double conductance =  (double) ((conInput - 0.1) + new Random().nextDouble()*(conInput - (conInput - 0.1)));
//
//      if(voltage<0)
//        voltage=0.0;
//
//      if(conductance<0)
//        conductance=0.0;


      createAndNotify(timeStamp, Math.random(), Math.random());
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
      }
    }
  }

//  public void updateVoltage(double value){
//    //value= Math.random()*20;
//    System.out.println("Updating Voltage! "+ value);
//    createAndNotify(timeStamp, value, data.getConductance());
//
//  }
//
//  public void updateConductance(double value){
//    createAndNotify(timeStamp, data.getVoltage(), value);
//
//  }

  private void createAndNotify(double timestampsystem, double voltage, double conductance) {
                  System.out.println("notifying ...");

    data = new Data(timestampsystem, voltage, conductance);
    setChanged();
    notifyObservers();
  }

}
