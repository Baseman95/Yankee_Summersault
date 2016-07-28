package code.network;

import java.util.Arrays;
import yansuen.game.GameObject;
import yansuen.network.Network;
import yansuen.network.commands.ClientCommand;

/**
 * @author Link
 */
public class UpdateObjectCommand implements ClientCommand {

    @Override
    public void execute(String[] argument, Network network) {
        network.getApplication().getWorld().addNetworkAction(
                new NetworkAction(network, argument, () -> {
                              GameObject gameObject = network.getApplication().getWorld().getGameObjectByObjectId(Integer.parseInt(argument[0]));
                              if (gameObject != null)
                                  gameObject.networkDeserialize(Arrays.copyOfRange(argument, 1, argument.length));
                          }));
    }

}
