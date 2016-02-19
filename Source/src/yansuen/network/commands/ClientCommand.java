package yansuen.network.commands;

import yansuen.network.Network;

/**
 *
 * @author Link162534
 */
public interface ClientCommand extends Command{

    void execute(String argument[], Network network);

}
