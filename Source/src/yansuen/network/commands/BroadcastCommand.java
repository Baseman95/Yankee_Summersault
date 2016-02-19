package yansuen.network.commands;

import java.util.Arrays;
import yansuen.network.NetworkServer;
import yansuen.network.commands.ServerCommand;

/**
 *
 * @author Link162534
 */
public class BroadcastCommand implements ServerCommand {

    @Override
    public void execute(int id, String[] argument, NetworkServer server) {
        String args[] = Arrays.copyOfRange(argument, 1, argument.length);
        server.broadcastCommand(id, Integer.parseInt(argument[0]), args);
    }

}
