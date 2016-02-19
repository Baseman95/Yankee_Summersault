package yansuen.network.commands;

import yansuen.network.Network;

/**
 *
 * @author Link162534
 */
public class SetIdCommand implements ClientCommand {

    @Override
    public void execute(String[] argument, Network network) {
        int id = Integer.parseInt(argument[0]);
        network.setId(id);
        network.getApplication().getKeyManager().getNetworkKeyManager(id);
    }

}
