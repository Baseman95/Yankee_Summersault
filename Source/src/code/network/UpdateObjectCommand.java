/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.network;

import java.util.Arrays;
import yansuen.network.Network;
import yansuen.network.commands.ClientCommand;

/**
 *
 * @author Link
 */
public class UpdateObjectCommand implements ClientCommand {

    @Override
    public synchronized void execute(String[] argument, Network network) {
        network.getApplication().getWorld().getGameObject(Integer.parseInt(argument[0])).
                networkDeserialize(Arrays.copyOfRange(argument, 1, argument.length));
    }

}
