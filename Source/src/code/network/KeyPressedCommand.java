package code.network;

import code.LinkTest;
import java.util.logging.Level;
import java.util.logging.Logger;
import yansuen.key.NetworkKeyManager;
import yansuen.network.Network;
import yansuen.network.commands.ClientCommand;

/**
 *
 * @author Link162534
 */
public class KeyPressedCommand implements ClientCommand {

    {
        Logger.getLogger(KeyPressedCommand.class.getName()).setLevel(Level.ALL);
    }

    @Override
    public void execute(String[] argument, Network network) {
        LinkTest a = ((LinkTest) network.getApplication());
        NetworkKeyManager knm = a.getKeyManager().getNetworkKeyManager(Integer.parseInt(argument[0]));
        Integer key = Integer.parseInt(argument[2]);
        if (Integer.parseInt(argument[1]) == 1)
            knm.forcePressKey(key);
        else
            knm.forceReleaseKey(key);
        Logger.getLogger(KeyPressedCommand.class.getName()).log(Level.FINER, "Received({0}): {1}:{2}", new Object[]{argument[0], argument[1], key});
    }

}
