/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.network;

import yansuen.network.commands.BroadcastCommand;
import yansuen.network.commands.SetIdCommand;
import yansuen.network.commands.UpdateIdListCommand;

/**
 *
 * @author Link
 */
public class CommandList {

    public static Class[] COMMAND_ARRAY = {SetIdCommand.class,
                                           UpdateIdListCommand.class,
                                           BroadcastCommand.class,
                                           KeyPressedCommand.class,
                                           UpdateObjectCommand.class};

    public static int getCommandId(Class command) {
        int id;
        for (id = 0; id < COMMAND_ARRAY.length; id++) {
            if (COMMAND_ARRAY[id] == command)
                return id;
        }
        throw new RuntimeException(command + " is not a Command.");
    }

}
