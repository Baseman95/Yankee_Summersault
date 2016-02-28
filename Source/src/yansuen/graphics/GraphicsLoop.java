package yansuen.graphics;

import yansuen.logic.LogicLoop;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Base
 */
public class GraphicsLoop extends Thread {

    private final int sleepTime;

    private Component repaintTarget;
    private boolean running = false;
    private boolean paused = false;

    public GraphicsLoop(int sleepTime) {
        super("yasuen.graphics.GraphicsLoop");
        this.sleepTime = sleepTime;
        setDaemon(true);
    }

    @Override
    public synchronized void run() {
        while (running) {
            while (!paused) {
                try {
                    Thread.sleep(sleepTime);
                    repaintTarget.revalidate();
                    repaintTarget.repaint();
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

    public void setRepaintTarget(Component repaintTarget) {
        this.repaintTarget = repaintTarget;
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
