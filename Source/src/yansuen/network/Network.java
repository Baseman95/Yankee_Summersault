package yansuen.network;


import code.game.Application;
import code.network.CommandList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import yansuen.network.commands.BroadcastCommand;

/**
 *
 * @author Link162534
 */
public class Network {

    private final Application app;

    private int id = -1;
    private Socket socket;
    private final InetAddress address;
    private final int port;

    private final ArrayList<Integer> clients = new ArrayList<>();
    private PrintWriter out;
    private ServerListener sl;
    public final ArrayList<Packet> packetHistory = new ArrayList<>();

    public Network(String ip, int port, Application application) throws UnknownHostException {
        this(InetAddress.getByName(ip), port, application);
    }

    public Network(InetAddress ip, int port, Application application) {
        this.address = ip;
        this.app = application;
        this.port = port;
    }

    public void connect() throws IOException {
        this.socket = new Socket(address, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        sl = new ServerListener(socket.getInputStream());
        sl.start();
        Logger.getLogger(Network.class.getName()).log(Level.INFO, "Connected to {0}.", socket.getInetAddress().getHostAddress());
    }

    public void disconnect() throws IOException {
        Logger.getLogger(ServerListener.class.getName()).log(Level.WARNING, "Stopped listening to server!");
        out.close();
        sl.disable();
        socket.close();
    }

    public void sendCommand(int command, String... argument) {
        String text = buildSendString(id, command, argument);
        out.println(text);
        Logger.getLogger(Network.class.getName()).log(Level.FINE, "{0} sent.", text);
    }

    public void sendBroadcastCommand(int command, String... argument) {
        String text = buildSendBroadcastString(id, command, argument);
        out.println(text);
        Logger.getLogger(Network.class.getName()).log(Level.FINE, "{0} broadcasted.", text);
    }

    private class ServerListener extends Thread {

        BufferedReader in;
        boolean run = true;

        public ServerListener(InputStream inputStream) {
            in = new BufferedReader(new InputStreamReader(inputStream));
            setDaemon(true);
        }

        @Override
        public synchronized void run() {
            Logger.getLogger(ServerListener.class.getName()).log(Level.INFO, "Started listening to server.");
            while (run) {
                try {
                    Packet packet = Packet.createPacket(in.readLine());
                    Logger.getLogger(ServerListener.class.getName()).log(Level.FINE, "Received packet: {0}", packet.toString());
                    packetHistory.add(packet);
                    packet.executeCommand(Network.this);
                } catch (IOException ex) {
                    Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
                    app.onConnectionLost();
                }
            }
        }

        public synchronized void disable() throws IOException {
            run = false;
            in.close();
        }

    }

    public static String buildSendString(int id, int command, String... argument) {
        StringBuilder message = new StringBuilder(String.valueOf(id));
        message.append(Packet.SEPERATOR).append(command).append(Packet.SEPERATOR);
        for (String a : argument) {
            message.append(a.replace(Packet.ARGUMENT_SEPERATOR, "")).append(Packet.ARGUMENT_SEPERATOR);
        }
        return message.toString();
    }

    public static String buildSendBroadcastString(int id, int command, String... argument) {
        StringBuilder message = new StringBuilder(String.valueOf(id));
        message.append(Packet.SEPERATOR).append(CommandList.getCommandId(BroadcastCommand.class));
        message.append(Packet.SEPERATOR).append(command).append(Packet.ARGUMENT_SEPERATOR);
        for (String a : argument) {
            message.append(a.replace(Packet.ARGUMENT_SEPERATOR, "")).append(Packet.ARGUMENT_SEPERATOR);
        }
        return message.toString();
    }

    public Application getApplication() {
        return app;
    }

    public ArrayList<Integer> getClients() {
        return clients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
