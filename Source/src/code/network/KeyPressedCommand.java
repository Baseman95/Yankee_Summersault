package code.network;

import code.LinkTest;
import yansuen.key.NetworkKeyManager;
import yansuen.network.Network;
import yansuen.network.commands.ClientCommand;

/**
 *
 * @author Link162534
 */
public class KeyPressedCommand implements ClientCommand {

    @Override
    public void execute(String[] argument, Network network) {
        LinkTest a = ((LinkTest) network.getApplication());
        NetworkKeyManager knm = a.getKeyManager().getNetworkKeyManager(Integer.parseInt(argument[0]));
        Integer key = Integer.parseInt(argument[2]);
        if (Integer.parseInt(argument[1]) == 1)
            knm.forcePressKey(key);
        else
            knm.forceReleaseKey(key);
        System.out.println("Received(" + argument[0] + "): " + argument[1] + ":" + key);
    }

}
