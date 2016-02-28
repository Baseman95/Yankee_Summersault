package yansuen.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Link162534
 */
public class Client extends Thread {

    private boolean run = true;
    private boolean active = false;
    private int id;

    private final Socket socket;
    private final NetworkServer server;
    private final PrintWriter out;
    private final BufferedReader in;

    public Client(NetworkServer server, Socket socket) throws IOException {
        super("yasuen.network.Client");
        this.server = server;
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        setDaemon(true);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void sendCommand(String message) {
        out.println(message);
    }

    public synchronized void setActive(boolean active) {
        this.active = active;
        notifyAll();
    }

    public synchronized void disable() throws IOException {
        Logger.getLogger(Client.class.getName()).log(Level.INFO, "Client {0} disabled.", id);
        run = false;
        out.close();
        in.close();
        socket.close();
        server.getClients().remove(this);
    }

    @Override
    public synchronized void run() {
        while (run) {
            while (!active && run) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(NetworkServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                Packet packet = Packet.createPacket(in.readLine());
                packet.executeCommand(id, server);
                server.addToPacketHistory(packet);
            } catch (IOException ex) {
                Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    disable();
                } catch (IOException ex1) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }

}
