package EyeTrackerV1;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import Core.Publisher;

/**
 * Put together a dataGenerator and a publisher. 
 * It read from the dataGenerator and publish in a port using the publisher.
 * A GUI control the model and can start, stop or shutdown it.
 * 
 * @author javiergs
 * @version 20190130
 */
public class Model {

  private final ExecutorService executorService;
  private final DataGenerator dataGenerator;
  private final Publisher threadPublisher;

  public Model(DataGenerator device, Publisher publisher) {
    executorService = Executors.newCachedThreadPool(); 
    dataGenerator = device;
    threadPublisher = publisher;
    dataGenerator.addObserver(threadPublisher);
    
  }

  public void shutdown() {
    dataGenerator.stop();
    threadPublisher.stop();
    executorService.shutdown();
    try {
      if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
        executorService.shutdownNow();
      }
    } catch (InterruptedException ie) {
    }
  }
  
/*
  public void startAndStop(Gui val, String status) {
        System.out.println("model start");
        Thread t = new Thread(new DataGenerator(val));
        if (status.equals("Start")) {
            t.start();
            try {
                executorService.submit(threadPublisher);
            }
            catch(Exception e) {
            	e.printStackTrace();
            }
        }
        else {
        	System.out.println("model stop");

            //dataGenerator.stop();
                    t = null;
            threadPublisher.stop();
            System.out.println("model stop");
        }
  }*/
  
  public void start() {
      System.out.println("model start");
  executorService.submit(dataGenerator);
  executorService.submit(threadPublisher);
}

public void stop() {
          System.out.println("model stop");

  dataGenerator.stop();
  threadPublisher.stop();
}
  
}
