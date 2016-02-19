package code.game;

import code.menu.GamePanel;
import code.menu.Screen;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import yansuen.graphics.GraphicsLoop;
import yansuen.key.KeyManager;
import yansuen.logic.LogicLoop;
import yansuen.network.Network;
import yansuen.network.NetworkServer;
import yansuen.network.Player;
import yansuen.network.ServerListener;

/**
 *
 * @author Link162534
 */
public class Application implements ActionListener, ServerListener {

    public Network network;
    protected final ConcurrentHashMap<Integer, Player> playerList = new ConcurrentHashMap<>();
    protected final Player localPlayer;
    protected boolean started = false;

    public LogicLoop ll = new LogicLoop(5000000L, 1);
    public GraphicsLoop gl = new GraphicsLoop(33);
    public KeyManager keyManager = new KeyManager();
    public World world = new World(keyManager);
    public GamePanel gamePanel = new GamePanel(world);
    public Screen screen;

    public Application(Screen screen) {
        localPlayer = new Player(this);
        ll.setLogic(world);
        gl.setRepaintTarget(screen);
        this.screen = screen;
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyManager);
        // localPlayer.getTools().selectTool(localPlayer.getDrawTool());
    }

    public void start() {
        if (started)
            return;
        started = true;
        ll.start();
        gl.start();
        gamePanel.addKeyListener(keyManager);
        screen.setContentPane(gamePanel);
    }

    @Deprecated
    public void stop() {
        if (!started)
            return;
        started = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
    }

    public void createServer() {
        try {
            NetworkServer server = new NetworkServer(47624);
            server.open();
            server.addServerListener(this);
        } catch (IOException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void connectToIp(String ip) {
        try {
            network = new Network(ip, 47624, this);
            network.connect();
        } catch (UnknownHostException ex) {
        } catch (IOException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void onConnectionLost() {
        try {
            network.disconnect();
            network = null;
        } catch (IOException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Network getNetwork() {
        return network;
    }

    @Override
    public void onConnectedToServer(int id) {
        /*  network.sendBroadcastCommand(Command.getCommandId(NewImageCommand.class),
                                     id + "",
                                     (int) gui.getDrawPanel().getImageDimension().getWidth() + "",
                                     (int) gui.getDrawPanel().getImageDimension().getHeight() + "");*/
    }

}
