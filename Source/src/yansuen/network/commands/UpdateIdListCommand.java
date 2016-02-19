package yansuen.network.commands;

import yansuen.network.Network;

/**
 *
 * @author Link162534
 */
public class UpdateIdListCommand implements ClientCommand {

    @Override
    public void execute(String[] argument, Network network) {
       /* network.getClients().clear();
        for (String a : argument) {
            int id = Integer.parseInt(a);
            network.getClients().add(id);
            if (!network.getApplication().keyManager.getNetworkKeyManagerList().containsKey(id)) {
                network.getApplication().keyManager.getNetworkKeyManagerList().put(id, new Player(network.getApplication()));
            }
        }*/
    }
}
