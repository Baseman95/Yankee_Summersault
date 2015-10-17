package code.logic;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author Base
 */
public class LogicLoop extends Thread {

    public final static long TICK_LENGTH = 50000000L;
    public final static int SLEEP_LENGTH = 10;

    private final ArrayList<LogicInterface> logicList = new ArrayList<>();
    private boolean running = false;
    private boolean paused = false;
    private long tickCount = 0;

    public LogicLoop() {
        //setDaemon(true);
    }

    @Override
    public synchronized void run() {
        long time;
        long newTime;
        long deltaTime = 0;
        while (running) {
            time = System.nanoTime();
            while (!paused) {
                try {

                    Thread.sleep(SLEEP_LENGTH);

                    newTime = System.nanoTime();
                    deltaTime += newTime - time;
                    time = newTime;

                    while (deltaTime > TICK_LENGTH) {
                        deltaTime -= TICK_LENGTH;

                        /*
                         for (int i = 0; i < logicList.size(); i++) {
                         LogicInterface li = logicList.get(i);
                         li.doLogic(tickCount);
                         }
                         */
                        /*
                         logicList.stream().forEach((li) -> {
                         li.doLogic(tickCount);
                         });
                         */
                        for (LogicInterface li : logicList) {
                            li.doLogic(tickCount);
                        }
                        tickCount++;
                    }

                } catch (InterruptedException ex) {
                    Logger.getLogger(LogicLoop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                if (running)
                    wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(LogicLoop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void addLogic(LogicInterface li) {
        logicList.add(li);
    }

    public void removeLogic(LogicInterface li) {
        logicList.remove(li);
    }

    public Stream<LogicInterface> stream() {
        return logicList.stream();
    }

    @Override
    public synchronized void start() {
        running = true;
        super.start();
    }

    public synchronized void exit() {
        setPaused(true);
        running = false;
        notifyAll();
    }

    public synchronized void setPaused(boolean paused) {
        this.paused = paused;
        notifyAll();
    }

}
