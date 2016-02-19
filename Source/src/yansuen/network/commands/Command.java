package yansuen.network.commands;

import yansuen.network.commands.client.ActionCommand;
import yansuen.network.commands.client.ReceiveTextCommand;
import yansuen.network.commands.client.SetIdCommand;
import yansuen.network.commands.client.UpdateIdListCommand;

/**
 *
 * @author Link162534
 */
public interface Command {

    public static Class[] COMMAND_ARRAY = {SetIdCommand.class,
        UpdateIdListCommand.class,
        BroadcastCommand.class,
        ReceiveTextCommand.class,
        ActionCommand.class};

    public static int getCommandId(Class command) {
        int id;
        for (id = 0; id < COMMAND_ARRAY.length; id++) {
            if (COMMAND_ARRAY[id] == command)
                return id;
        }
        throw new RuntimeException(command + " is not a Command.");
    }

}
