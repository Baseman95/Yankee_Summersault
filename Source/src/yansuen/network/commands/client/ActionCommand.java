package yansuen.network.commands.client;

import java.util.Arrays;
import yansuen.network.Network;
import yansuen.network.commands.ClientCommand;

/**
 *
 * @author Link162534
 */
public class ActionCommand implements ClientCommand {

    @Override
    public void execute(String[] argument, Network network) {
        int senderId = Integer.parseInt(argument[0]);
        
    }

}
