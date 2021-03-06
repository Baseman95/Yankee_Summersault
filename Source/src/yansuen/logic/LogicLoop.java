package yansuen.logic;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Base
 */
public class LogicLoop extends Thread {

    private final long tickLength;
    private final int sleepTime;

    private LogicLooper looper;
    private boolean running = false;
    private boolean paused = false;
    private long tickCount = 0;
    private final int tplLimit = 5;

    public LogicLoop(long tickLength, int sleepTime) {
        super("yasuen.logic.LogicLoop");
        this.tickLength = tickLength;
        this.sleepTime = sleepTime;
        setDaemon(true);
    }

    @Override
    public synchronized void run() {
        long time;
        long newTime;
        long deltaTime = 0;
        int tpl;
        while (running) {
            time = System.nanoTime();
            while (!paused) {
                try {

                    Thread.sleep(sleepTime);

                    newTime = System.nanoTime();
                    deltaTime += newTime - time;
                    time = newTime;
                    tpl = 0;
                    while (deltaTime > tickLength) {
                        deltaTime -= tickLength;
                        looper.onLogicLoop(tickCount);
                        tickCount++;
                        tpl++;
                        if (tpl >= tplLimit) {
                            deltaTime = 0;
                            break;
                        }
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

    public void setLogic(LogicLooper logic) {
        this.looper = logic;
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
