package ru.itmo.lab.service.commands.clientcommands;

import ru.itmo.lab.repository.commandresult.CommandResult;
import ru.itmo.lab.repository.commandresult.CommandResultBuilder;
import ru.itmo.lab.repository.Storage;
import ru.itmo.lab.repository.commandresult.CommandStatus;
import ru.itmo.lab.request.Request;
import ru.itmo.lab.service.handlers.XMLWriter;

import java.io.IOException;

public class ExitCommand extends ClientCommand {
    public ExitCommand() {
        super("exit", "exit the program with " +
                "saving", "arguments aren't needed");
    }

    @Override
    public CommandResult execute(Storage storage, Request request) {
        try {
            XMLWriter.writeToXML(storage.readAll());
            return new CommandResultBuilder()
                    .setMessage("Completion the program")
                    .setStatus(CommandStatus.SUCCESSFUL)
                    .setExit().build();
        } catch (IllegalArgumentException | IOException e) {
            return new CommandResultBuilder()
                    .setMessage(e.getMessage())
                    .setStatus(CommandStatus.UNSUCCESSFUL).build();
        }
    }
}
