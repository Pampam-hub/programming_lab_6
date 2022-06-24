package ru.itmo.lab.handlers;

import ru.itmo.lab.CommandToSend;

import java.util.Arrays;

public class CommandToSendCreator {
    private CommandToSendCreator() {

    }

    public static CommandToSend createCommandToSend(String line) {
        String[] args = line.trim().split(" ");
        CommandToSend commandToSend =
                new CommandToSend(args[0], Arrays.copyOfRange(args, 1, args.length));
        return commandToSend;
    }
}
