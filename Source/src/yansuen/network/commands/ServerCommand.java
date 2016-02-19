package yansuen.network.commands;

import yansuen.network.NetworkServer;

/**
 *
 * @author Link162534
 */
public interface ServerCommand extends Command {

    void execute(int id, String argument[], NetworkServer server);

}
