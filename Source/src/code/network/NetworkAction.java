package code.network;

import java.util.Arrays;
import yansuen.network.Network;

/**
 * @author Link
 */
public class NetworkAction {

    public final Network network;
    public final String[] argument;
    public final Executable executable;

    public NetworkAction(Network network, String[] argument, Executable executable) {
        this.network = network;
        this.argument = argument;
        this.executable = executable;
    }

    public static interface Executable {

        void execute();
    }
}
