package yansuen.network;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import yansuen.network.commands.ClientCommand;
import yansuen.network.commands.Command;
import yansuen.network.commands.ServerCommand;

/**
 *
 * @author Link162534
 */
public class Packet {

    private final int senderId;
    private final Command command;
    private final String[] argument;
    public static final String ARGUMENT_SEPERATOR = "|";
    public static final String SEPERATOR = ":";

    public static Packet createPacket(String in) {
        String split[] = in.split(SEPERATOR, 3);
        if (split.length != 3)
            throw new RuntimeException("Faulty Command:" + in);
        try {
            String[] args = split[2].split(Pattern.quote(ARGUMENT_SEPERATOR));
            return new Packet(Integer.parseInt(split[0]),
                              (Command) Command.COMMAND_ARRAY[Integer.parseInt(split[1])].getConstructor().newInstance(),
                              args);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(Packet.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new RuntimeException("Packet couldn't generate object with \"" + in + "\"");
    }

    public Packet(int senderId, Command command, String[] argument) {
        this.senderId = senderId;
        this.command = command;
        this.argument = argument;
    }

    public void executeCommand(Network network) {
        if (!(command instanceof ClientCommand))
            throw new RuntimeException("Wrong command sent.");
        ((ClientCommand) command).execute(argument, network);
        Logger.getLogger(Packet.class.getName()).log(Level.FINE, "Command executed");
    }

    public void executeCommand(int id, NetworkServer server) {
        if (!(command instanceof ServerCommand))
            throw new RuntimeException("Wrong command sent.");
        ((ServerCommand) command).execute(id, argument, server);
        Logger.getLogger(Packet.class.getName()).log(Level.FINE, "Command executed");
    }

    public int getSenderId() {
        return senderId;
    }

    public Command getCommand() {
        return command;
    }

    public String[] getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        StringBuilder arguments = new StringBuilder();
        for (String a : argument) {
            arguments.append(a).append(Packet.ARGUMENT_SEPERATOR);
        }
        if (arguments.length() > 0)
            arguments.deleteCharAt(arguments.length() - 1);
        return "Packet(" + "" + senderId + ", " + command + SEPERATOR + " " + arguments + ")";
    }

}
