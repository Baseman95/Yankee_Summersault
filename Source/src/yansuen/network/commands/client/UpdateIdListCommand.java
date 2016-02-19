package yansuen.network.commands.client;

import yansuen.network.Network;
import yansuen.network.Player;
import yansuen.network.commands.ClientCommand;

/**
 *
 * @author Link162534
 */
public class UpdateIdListCommand implements ClientCommand {

    @Override
    public void execute(String[] argument, Network network) {
        network.getClients().clear();
        for (String a : argument) {
            int id = Integer.parseInt(a);
            network.getClients().add(id);
            if (!network.getApplication().getPlayerList().containsKey(id)) {
                network.getApplication().getPlayerList().put(id, new Player(network.getApplication()));
            }
        }
    }
}
