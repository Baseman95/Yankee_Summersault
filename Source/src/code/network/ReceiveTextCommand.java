package code.network;

import yansuen.network.Network;
import yansuen.network.commands.ClientCommand;

/**
 *
 * @author Link162534
 */
public class ReceiveTextCommand implements ClientCommand {

    @Override
    public void execute(String[] argument, Network network) {
        System.out.println(argument[0]);
    }

}
