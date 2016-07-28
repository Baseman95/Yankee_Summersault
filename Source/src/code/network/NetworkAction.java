package code.network;

import java.util.Arrays;
import yansuen.network.Network;

/**
 * @author Link
 */
public class NetworkAction {

    private final Network network;
    private final String[] argument;

    public NetworkAction(Network network, String[] argument) {
        this.network = network;
        this.argument = argument;
    }

    public void execute() {
        network.getApplication().getWorld().getGameObject(Integer.parseInt(argument[0])).
                networkDeserialize(Arrays.copyOfRange(argument, 1, argument.length));
    }

}
