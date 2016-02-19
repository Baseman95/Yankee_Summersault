package yansuen.network.commands.client;

import yansuen.network.Network;
import yansuen.network.commands.ClientCommand;

/**
 *
 * @author Link162534
 */
public class SetIdCommand implements ClientCommand {

    @Override
    public void execute(String[] argument, Network network) {
        int id = Integer.parseInt(argument[0]);
        network.setId(id);
        network.getApplication().getPlayerList().put(id, network.getApplication().getPlayer());
    }

}
