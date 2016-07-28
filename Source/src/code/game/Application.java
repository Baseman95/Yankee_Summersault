package code.game;

import code.menu.GamePanel;
import code.menu.Screen;
import java.awt.KeyboardFocusManager;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import yansuen.graphics.GraphicsLoop;
import yansuen.key.MasterKeyManager;
import yansuen.logic.LogicLoop;
import yansuen.network.Network;
import yansuen.network.NetworkServer;
import yansuen.network.ServerListener;

/**
 *
 * @author Link162534
 */
public class Application implements ServerListener {

    protected Network network;
    protected boolean started = false;

    protected LogicLoop logicLoop = new LogicLoop(5000000L, 1);
    protected GraphicsLoop graphicsLoop = new GraphicsLoop(33);
    protected MasterKeyManager keyManager = new MasterKeyManager();
    protected World world;
    protected GamePanel gamePanel;
    protected Screen screen;

    public JPanel mainPanel;

    public Application(Screen screen) {
        this.screen = screen;
        // localPlayer.getTools().selectTool(localPlayer.getDrawTool());
    }

    public void start() {
        if (started)
            return;
        started = true;
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyManager.getLocalKeyManager());
        world = new World(keyManager, network);
        gamePanel = new GamePanel(world);
        world.setGamePanel(gamePanel);
        graphicsLoop.setRepaintTarget(screen);
        logicLoop.setLogic(world);
        logicLoop.start();
        graphicsLoop.start();
        mainPanel = (JPanel) screen.getContentPane();
        screen.setContentPane(gamePanel);
    }

    @Deprecated
    public void stop() {
        if (!started)
            return;
        started = false;
        screen.setContentPane(mainPanel);
    }

    /* new Thread(() -> {
            if (e.getSource() == gui.getConnectToServerButton()) {
                connectToIp(gui.getIpTextField().getText());
                SwingUtilities.getWindowAncestor(((Component) e.getSource())).setVisible(false);
            }
            if (e.getSource() == gui.getCreateServerButton()) {
                createServer();
                connectToIp("127.0.0.1");
                SwingUtilities.getWindowAncestor(((Component) e.getSource())).setVisible(false);
            }
        }).start();*/
    public void createServer() {
        try {
            NetworkServer server = new NetworkServer(47624);
            server.open();
            server.addServerListener(this);

        } catch (IOException ex) {
            Logger.getLogger(Application.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (NumberFormatException ex) {
            Logger.getLogger(Application.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connectToIp(String ip) {
        try {
            network = new Network(ip, 47624, this);
            network.connect();
        } catch (UnknownHostException ex) {
        } catch (IOException ex) {
            Logger.getLogger(Application.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onConnectionLost() {
        try {
            network.disconnect();
            network = null;
        } catch (IOException ex) {
            Logger.getLogger(Application.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onConnectedToServer(int id) {
        /*  network.sendBroadcastCommand(Command.getCommandId(NewImageCommand.class),
                                     id + "",
                                     (int) gui.getDrawPanel().getImageDimension().getWidth() + "",
                                     (int) gui.getDrawPanel().getImageDimension().getHeight() + "");*/
    }

    public Network getNetwork() {
        return network;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public LogicLoop getLogicLoop() {
        return logicLoop;
    }

    public void setLogicLoop(LogicLoop ll) {
        this.logicLoop = ll;
    }

    public GraphicsLoop getGraphicsLoop() {
        return graphicsLoop;
    }

    public void setGraphicsLoop(GraphicsLoop gl) {
        this.graphicsLoop = gl;
    }

    public MasterKeyManager getKeyManager() {
        return keyManager;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

}
