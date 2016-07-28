package code.network;

import yansuen.network.Network;
import yansuen.network.commands.ClientCommand;

/**
 * @author Link
 */
public class UpdateObjectCommand implements ClientCommand {

    @Override
    public void execute(String[] argument, Network network) {
        network.getApplication().getWorld().addNetworkAction(new NetworkAction(network, argument));
    }

}
