package HeartRate;

import Core.Publisher;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Put together a dataGenerator and a publisher.
 * It read from the dataGenerator and publish in a port using the publisher.
 * A GUI control the model and can start, stop or shutdown it.
 *
 * @author nikitabahl
 * @version 20190202
 */
public class Model {

    private final ExecutorService executorService;
    private final HRDataGenerator dataGenerator;
    private final Publisher threadPublisher;
    private boolean serverState;

    public Model(HRDataGenerator device, Publisher publisher) {
        executorService = Executors.newCachedThreadPool();
        dataGenerator = device;
        threadPublisher = publisher;
        dataGenerator.addObserver(threadPublisher);
        serverState = false;
    }

    public void setHeartState(int heartState) {
        dataGenerator.setHeartState(heartState);
    }

    public boolean getServerState() {
        return serverState;
    }

    public void setServerState(boolean serverState) {
        this.serverState = serverState;
    }

    public void setFrequency(double frequency) {
        dataGenerator.setFrequency(frequency);
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

    public void start() {
    		this.serverState=true;
        executorService.submit(dataGenerator);
        executorService.submit(threadPublisher);
    }

    public void stop() {
    		this.serverState=false;
        dataGenerator.stop();
        threadPublisher.stop();
    }

}
