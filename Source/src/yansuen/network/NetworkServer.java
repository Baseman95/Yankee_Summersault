package yansuen.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import yansuen.network.commands.Command;
import yansuen.network.commands.client.SetIdCommand;
import yansuen.network.commands.client.UpdateIdListCommand;

/**
 *
 * @author Link162534
 */
public class NetworkServer {

    private ServerSocket socket;
    private final int port;
    private ServerListener serverListener;

    private final ArrayList<Client> clients = new ArrayList<Client>()//<editor-fold defaultstate="collapsed" desc="CustomArrayList">
    {

        LinkedList<Integer> holes = new LinkedList<>();

        @Override
        public boolean add(Client e) {
            if (holes.size() == 0)
                return super.add(e);
            int index = holes.pop();
            set(index, e);
            return true;
        }

        @Override
        public Client remove(int index) {
            holes.add(index);
            return set(index, null);
        }

        @Override
        public boolean remove(Object o) {
            int index = indexOf(o);
            holes.add(index);
            set(index, null);
            return true;
        }

        @Override
        public int size() {
            return super.size() - holes.size();
        }

        @Override
        public Iterator<Client> iterator() {
            return new Iterator<Client>() {
                int index = 0;

                @Override
                public boolean hasNext() {
                    return size() > index + 1;
                }

                @Override
                public Client next() {
                    index++;
                    for (Integer hole : holes) {
                        if (index == hole)
                            index++;
                    }
                    return get(index);
                }

            };
        }

    } /*</editor-fold>*/;
    private final ArrayList<Packet> packetHistory = new ArrayList<>();
    private NewClientListener ncl;

    public NetworkServer(int port) {
        this.port = port;
    }

    public void open() throws IOException {
        socket = new ServerSocket(port);
        ncl = new NewClientListener();
        ncl.start();
        ncl.setActive(true);
        listen(true);
    }

    public void listen(boolean listen) {
        clients.stream().forEach((client) -> {
            client.setActive(listen);
        });
        Logger.getLogger(NetworkServer.class.getName()).log(Level.INFO, "{0} listening to clients.", listen ? "Started" : "Stopped");
    }

    public void sendCommand(int index, int command, String... argument) {
        clients.get(index).sendCommand(Network.buildSendString(-2, command, argument));
    }

    public void broadcastAllCommand(int command, String... argument) {
        int[] index = getIndices();
        for (int i = 0; i < index.length; i++) {
            sendCommand(index[i], command, argument);
        }
    }

    public void broadcastCommand(int id, int command, String... argument) {
        int[] index = getIndices();
        for (int i = 0; i < index.length; i++) {
            if (index[i] != id) {
                sendCommand(index[i], command, argument);
            }
        }
    }

    public int[] getIndices() {
        int[] index = new int[clients.size()];
        int i = 0;
        for (Client client : clients) {
            index[i] = clients.indexOf(client);
            i++;
        }
        return index;
    }

    public void addToPacketHistory(Packet packet) {
        packetHistory.add(packet);
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void addServerListener(ServerListener serverListener) {
        this.serverListener = serverListener;
    }

    protected class NewClientListener extends Thread {

        private boolean active = false;
        private boolean run = true;

        public NewClientListener() {
            setDaemon(true);
        }

        public synchronized void setActive(boolean active) {
            this.active = active;
            notifyAll();
            Logger.getLogger(NewClientListener.class.getName()).log(Level.INFO, "{0} listening for new clients.", active ? "Started" : "Stopped");
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
                    Client client = new Client(NetworkServer.this, socket.accept());
                    clients.add(client);
                    int id = clients.indexOf(client);
                    client.setId(id);
                    Logger.getLogger(NewClientListener.class.getName()).log(Level.INFO, "Client {0} connected.", id);
                    sendCommand(id, Command.getCommandId(SetIdCommand.class), Integer.toString(id));
                    int[] index = getIndices();
                    String[] arg = new String[index.length];
                    for (int i = 0; i < index.length; i++) {
                        arg[i] = Integer.toString(index[i]);
                    }
                    broadcastAllCommand(Command.getCommandId(UpdateIdListCommand.class), arg);
                    client.start();
                    client.setActive(true);
                    serverListener.onConnectedToServer(id);
                } catch (IOException ex) {
                    Logger.getLogger(NetworkServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public synchronized void disable() throws IOException {
            active = false;
            Logger.getLogger(NewClientListener.class.getName()).log(Level.INFO, "Listening for new clients disabled.");
            socket.close();
        }

    }
}
